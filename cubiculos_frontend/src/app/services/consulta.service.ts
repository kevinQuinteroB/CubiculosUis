import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Consulta } from '../models/consulta';
import { environment } from '../enviroments/enviroment';

@Injectable({
  providedIn: 'root'
})
export class ConsultaService {

  private apiUrl: string = environment.cubiculosApiUrl
  
  constructor(private http: HttpClient) { }
  
  private getAuthHeaders(): HttpHeaders {
    const token = localStorage.getItem('token');
    return new HttpHeaders({
      Accept: 'application/json',
      Authorization: `Bearer ${token}`,
    });
  }

  consultaPrincipal(fechaHoraInicio: string, fechaHoraFin: string, capacidad: number): Observable<Consulta[]> {
    // Convertimos las fechas a formato que espera el backend (ISO 8601)
    const params = new HttpParams()
      .set('fechaHoraInicio', fechaHoraInicio)
      .set('fechaHoraFin', fechaHoraFin)
      .set('capacidad', capacidad.toString());
      const headers = this.getAuthHeaders();

    return this.http.get<Consulta[]>(`${this.apiUrl}/horario/consultaPrincipal`, { params, headers });
  }

}
