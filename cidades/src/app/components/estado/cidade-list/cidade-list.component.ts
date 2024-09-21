import { Component, OnInit } from '@angular/core';
import { Cidade } from '../../../models/cidade.model';
import { CidadeService } from '../../../services/cidade.service';
import { NgFor } from '@angular/common';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatTableModule } from '@angular/material/table';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-cidade-list',
  standalone: true,
  imports: [NgFor, MatToolbarModule, MatIconModule, MatButtonModule, MatTableModule, RouterModule],
  templateUrl: 'cidade-list.component.html',
  styleUrls: ['cidade-list.component.css']
})
export class CidadeListComponent implements OnInit {
  cidades: Cidade[] = [];
  displayedColumns: string[] = ['id', 'nome', 'populacao', 'estado', 'acao'];

  constructor(private cidadeService: CidadeService) { }

  ngOnInit(): void {
    this.loadCidades();
  }

  deleteCidade(id: number): void {
    if (confirm('Tem certeza que deseja deletar esta cidade?')) {
      this.cidadeService.delete(id).subscribe(
        () => {
          this.loadCidades();
        },
        error => {
          console.error('Erro ao deletar cidade:', error);
        }
      );
    }
  }

  loadCidades() {
    this.cidadeService.findAll().subscribe(data => {
      this.cidades = data.sort((a, b) => a.nome.localeCompare(b.nome));
    });
  }
}
