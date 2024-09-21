import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CidadeService } from '../../../services/cidade.service';
import { EstadoService } from '../../../services/estado.service';
import { ActivatedRoute, Router } from '@angular/router';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { NgIf, NgFor } from '@angular/common';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';  // <- Importando MatSelectModule
import { Estado } from '../../../models/estado.model';
import { Cidade } from '../../../models/cidade.model';

@Component({
  selector: 'app-cidade-edit',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    MatFormFieldModule,
    MatButtonModule,
    NgIf,
    NgFor,
    MatInputModule,
    MatSelectModule 
  ],
  templateUrl: 'cidade-edit.component.html',
  styleUrl: 'cidade-edit.component.css'
})
export class CidadeEditComponent implements OnInit {
  formGroup: FormGroup;
  estados: Estado[] = [];
  cidadeId!: number;

  constructor(
    private formBuilder: FormBuilder,
    private cidadeService: CidadeService,
    private estadoService: EstadoService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.formGroup = this.formBuilder.group({
      nome: ['', Validators.required],
      populacao: ['', [Validators.required, Validators.min(1)]],
      estadoId: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.cidadeId = this.route.snapshot.params['id'];
    this.loadEstados();
    this.loadCidade();
  }

  loadEstados() {
    this.estadoService.findAll().subscribe((data: Estado[]) => {
      this.estados = data;
    });
  }

  loadCidade() {
    this.cidadeService.findById(this.cidadeId).subscribe((cidade: Cidade) => {
      this.formGroup.patchValue({
        nome: cidade.nome,
        populacao: cidade.populacao,
        estadoId: cidade.idEstado
      });
    });
  }

  onSubmit() {
    if (this.formGroup.valid) {
      const cidadeAtualizada: Cidade = this.formGroup.value;
      cidadeAtualizada.id = this.cidadeId;

      this.cidadeService.update(cidadeAtualizada).subscribe({
        next: () => {
          this.router.navigateByUrl('/cidades');
        },
        error: (err) => {
          console.log('Erro ao atualizar cidade', + JSON.stringify(err));
        }
      });
    }
  }
}
