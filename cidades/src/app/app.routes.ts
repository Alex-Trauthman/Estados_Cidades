import { Routes } from '@angular/router';
import { EstadoListComponent } from './components/estado/estado-list/estado-list.component';
import { EstadoFormComponent } from './components/estado/estado-form/estado-form.component';
import { EstadoEditComponent } from './components/estado/estado-edit/estado-edit.component';
import { CidadeListComponent } from './components/estado/cidade-list/cidade-list.component';
import { CidadeFormComponent } from './components/estado/cidade-form/cidade-form.component';
import { CidadeEditComponent } from './components/estado/cidade-edit/cidade-form.component';

export const routes: Routes = [
    {path: 'estados',component: EstadoListComponent, title: 'Lista de Estados'},
    {path: 'estados/new',component: EstadoFormComponent, title: 'Novo Estado'},
    {path: 'estados/edit/:id',component: EstadoEditComponent, title: 'Editar Estado'},
    { path: 'cidades', component: CidadeListComponent, title: 'Lista de Cidades' },
    { path: 'cidades/new', component: CidadeFormComponent, title: 'Nova Cidade' },
    { path: 'cidades/edit/:id', component: CidadeEditComponent, title: 'Editar Cidade' }
];
