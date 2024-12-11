import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Reserva } from '../models/reserva';
import { ReservaConsulta } from '../models/reserva-consulta';
import { environment } from '../enviroments/enviroment';

@Injectable({
  providedIn: 'root'
})
export class ReservaService {

  private apiUrl: string = environment.cubiculosApiUrl;

  constructor(private http: HttpClient) { }
  
  private getAuthHeaders(): HttpHeaders {
    const token = localStorage.getItem('token');
    return new HttpHeaders({
      Accept: 'application/json',
      Authorization: `Bearer ${token}`,
    });
  }

  listarReservas(idEstudiante: number): Observable<ReservaConsulta[]>{
    const params = new HttpParams()
      .set('idEstudiante', idEstudiante)
      const headers = this.getAuthHeaders();
    return this.http.get<ReservaConsulta[]>(`${this.apiUrl}/reserva/listarReservaEstudiante`,{ params, headers })
  }



  confirmarReserva(idCubiculo: number, idEstudiante: number, fechaHoraInicio: string, fechaHoraFin: string, asistentes: { codigo: number }[]): Observable<Reserva> {

    const params = new HttpParams()
      .set('idCubiculo', idCubiculo)
      .set("idEstudiante", idEstudiante)
      .set('fechaHoraInicio', fechaHoraInicio)
      .set('fechaHoraFin', fechaHoraFin)
      
    // Configuración de cabeceras (si es necesario)
    const headers = this.getAuthHeaders();
    // Realiza la solicitud POST y devuelve el observable
    return this.http.post<Reserva>(`${this.apiUrl}/reserva/confirmar`, asistentes, { params, headers });
  }
}
