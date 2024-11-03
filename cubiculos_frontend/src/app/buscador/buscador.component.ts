import { Component } from '@angular/core';

@Component({
  selector: 'app-buscador',
  templateUrl: './buscador.component.html',
  styleUrl: './buscador.component.css'
})
export class BuscadorComponent {
  horas: string[] = [];
  capacidad: number[] = [];
  fechas: string[] = [];

  constructor() {
    this.generarHoras();
    this.generarFechas();
    this.generarCapacidad();
  }

  generarCapacidad(){
    for(let i = 1; i <= 6; i++){
      const capacidad = i;
      this.capacidad.push(capacidad);
    }
  }

  generarHoras() {
    for (let i = 8; i <= 18; i++) {
      const hora = i <= 12 ? `${i}:00 AM` : `${i - 12}:00 PM`;
      this.horas.push(hora);
    }
  }
  generarFechas() {
    const hoy = new Date();
    for (let i = 0; i <= 5; i++) {
      const fecha = new Date(hoy);
      fecha.setDate(hoy.getDate() + i);
      this.fechas.push(fecha.toLocaleDateString()); // Ajusta el formato si es necesario
    }
  }
}
