import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CidadeService } from '../../../services/cidade.service';
import { EstadoService } from '../../../services/estado.service';
import { Router } from '@angular/router';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { NgIf, NgFor } from '@angular/common';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';  // <- Adicionar isso
import { Estado } from '../../../models/estado.model';
import { Cidade } from '../../../models/cidade.model';

@Component({
  selector: 'app-cidade-form',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    MatFormFieldModule,
    MatButtonModule,
    NgIf,
    NgFor,
    MatInputModule,
    MatSelectModule  // <- Importar MatSelectModule aqui
  ],
  templateUrl: './cidade-form.component.html',
  styleUrl: './cidade-form.component.css'
})
export class CidadeFormComponent implements OnInit {
  formGroup: FormGroup;
  estados: Estado[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private cidadeService: CidadeService,
    private estadoService: EstadoService,
    private router: Router
  ) {
    this.formGroup = this.formBuilder.group({
      nome: ['', Validators.required],
      populacao: ['', [Validators.required, Validators.min(1)]],
      estadoId: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.loadEstados();
  }

  loadEstados() {
    this.estadoService.findAll().subscribe((data: Estado[]) => {
      this.estados = data;
    });
  }

  onSubmit() {
    if (this.formGroup.valid) {
      const novaCidade: Cidade = this.formGroup.value;

      this.cidadeService.insert(novaCidade).subscribe({
        next: () => {
          this.router.navigateByUrl('/cidades');
        },
        error: (err) => {
          console.log('Erro ao salvar', + JSON.stringify(err));
        }
      });
    }
  }
}
