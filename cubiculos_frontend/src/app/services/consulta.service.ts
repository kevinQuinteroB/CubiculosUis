import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Consulta } from '../models/consulta';

@Injectable({
  providedIn: 'root'
})
export class ConsultaService {

  private apiUrl: string = "http://localhost:8080/horario";
  
  constructor(private http: HttpClient) { }

  consultaPrincipal(fechaHoraInicio: string, fechaHoraFin: string, capacidad: number): Observable<Consulta[]> {
    // Convertimos las fechas a formato que espera el backend (ISO 8601)
    const params = new HttpParams()
      .set('fechaHoraInicio', fechaHoraInicio)
      .set('fechaHoraFin', fechaHoraFin)
      .set('capacidad', capacidad.toString());

    return this.http.get<Consulta[]>(`${this.apiUrl}/consultaPrincipal`, { params });
  }

}
