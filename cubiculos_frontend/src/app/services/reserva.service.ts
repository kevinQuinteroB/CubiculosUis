import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Reserva } from '../models/reserva';

@Injectable({
  providedIn: 'root'
})
export class ReservaService {

  private apiUrl: string = "http://localhost:8080/reserva";

  constructor(private http: HttpClient) { }

  confirmarReserva(idCubiculo: number, idEstudiante: number, fechaHoraInicio: string, fechaHoraFin: string, asistentes: { codigo: number }[]): Observable<Reserva> {

    const params = new HttpParams()
      .set('idCubiculo', idCubiculo)
      .set("idEstudiante", idEstudiante)
      .set('fechaHoraInicio', fechaHoraInicio)
      .set('fechaHoraFin', fechaHoraFin)
      
    

    // Configuración de cabeceras (si es necesario)
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    // Realiza la solicitud POST y devuelve el observable
    return this.http.post<Reserva>(`${this.apiUrl}/confirmar`, asistentes, { params });
  }
}
