import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Estado } from '../models/estado.model';

@Injectable({
  providedIn: 'root'
})
export class EstadoService {
  private baseUrl = 'http://localhost:8080/estados';

  constructor(private httpClient: HttpClient) {
  }

  findAll(): Observable<Estado[]> {
    return this.httpClient.get<Estado[]>(this.baseUrl); 
  }

  findById(id: number): Observable<Estado> {
    return this.httpClient.get<Estado>(`${this.baseUrl}/${id}`); 
  }

  insert(estado: Estado): Observable<Estado> {
    return this.httpClient.post<Estado>(this.baseUrl, estado);
  }

  update(estado: Estado): Observable<Estado> {
    return this.httpClient.put<any>(`${this.baseUrl}/${estado.id}`, estado); 
  }

  delete(id: number): Observable<any>{
    return this.httpClient.delete<any>(`${this.baseUrl}/${id}`); 
  }
  updateCapital(id: number, idCapital: number): Observable<any> {
    return this.httpClient.patch<any>(`${this.baseUrl}/${id}/capital`, { idCapital });
  }

}
