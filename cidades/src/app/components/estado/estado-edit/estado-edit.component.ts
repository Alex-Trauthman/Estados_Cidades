import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { EstadoService } from '../../../services/estado.service';
import { ActivatedRoute, Router } from '@angular/router';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { NgIf } from '@angular/common';
import { MatInputModule } from '@angular/material/input';
import { Estado } from '../../../models/estado.model';

@Component({
  selector: 'app-estado-edit',
  standalone: true,
  imports: [ReactiveFormsModule, MatFormFieldModule, MatButtonModule, NgIf, MatInputModule],
  templateUrl: 'estado-edit.component.html',
  styleUrl: 'estado-edit.component.css'
})
export class EstadoEditComponent implements OnInit {
  formGroup: FormGroup;
  estadoId!: number;

  constructor(
    private formBuilder: FormBuilder,
    private estadoService: EstadoService,
    private router: Router,
    private route: ActivatedRoute 
  ) {
    this.formGroup = this.formBuilder.group({
      nome: ['', Validators.required],
      sigla: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    
    this.estadoId = this.route.snapshot.params['id'];

    this.estadoService.findById(this.estadoId).subscribe((estado: Estado) => {
      this.formGroup.patchValue({
        nome: estado.nome,
        sigla: estado.sigla
      });
    });
  }

  onSubmit() {
    if (this.formGroup.valid) {
      const estadoAtualizado = this.formGroup.value;
      estadoAtualizado.id = this.estadoId; 

      this.estadoService.update(estadoAtualizado).subscribe({
        next: () => {
          this.router.navigateByUrl('/estados');
        },
        error: (err) => {
          console.log('Erro ao atualizar', + JSON.stringify(err));
        }
      });
    }
  }
}
