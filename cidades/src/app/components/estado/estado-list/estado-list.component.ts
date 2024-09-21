import { Component, OnInit } from '@angular/core';
import { Estado } from '../../../models/estado.model';
import { EstadoService } from '../../../services/estado.service';
import { NgFor } from '@angular/common';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatTableModule } from '@angular/material/table';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-estado-list',
  standalone: true,
  imports: [NgFor, MatToolbarModule, MatIconModule, MatButtonModule, MatTableModule, RouterModule],
  templateUrl: './estado-list.component.html',
  styleUrl: './estado-list.component.css'
  
})
export class EstadoListComponent implements OnInit {
  estados: Estado[] = [];
  displayedColumns: string[] = ['id', 'nome', 'sigla','populacao','capital', 'acao'];

  constructor(private estadoService: EstadoService) {

  }

  ngOnInit(): void {
    
    this.loadEstados();
  }
  deleteEstado(id: number): void {
    if (confirm('Tem certeza que deseja deletar este estado?')) {
      this.estadoService.delete(id).subscribe(
        () => {
          this.loadEstados(); 
        },
        error => {
          console.error('Erro ao deletar estado:', error);
        }
      );
    }
  }
  loadEstados() {
    this.estadoService.findAll().subscribe(data => {
      this.estados = data.sort((a, b) => a.nome.localeCompare(b.nome));
    });
  }

}
