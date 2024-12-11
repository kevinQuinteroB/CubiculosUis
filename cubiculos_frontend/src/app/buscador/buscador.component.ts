import { Component, ViewChild, OnChanges, SimpleChanges, viewChild } from '@angular/core';
import { ModalConfirmacionReservaComponent } from '../modal-confirmacion-reserva/modal-confirmacion-reserva.component';
import { ConsultaService } from '../services/consulta.service';
import { query } from 'express';
import { Consulta } from '../models/consulta';
import { ModalVerReservasComponent } from '../modal-ver-reservas/modal-ver-reservas.component';
import { ReservaService } from '../services/reserva.service';
import { ReservaConsulta } from '../models/reserva-consulta';

@Component({
  selector: 'app-buscador',
  templateUrl: './buscador.component.html',
  styleUrls: ['./buscador.component.css']
})
export class BuscadorComponent implements OnChanges {

  @ViewChild(ModalConfirmacionReservaComponent) ModalConfirmacionReservaComponent!: ModalConfirmacionReservaComponent;
  @ViewChild(ModalVerReservasComponent) ModalVerReservasComponent!: ModalVerReservasComponent;


  horas: string[] = [];
  capacidad: number[] = [];
  fechas: string[] = [];
  horasFinal: string[] = [];

  horaInicio: string = '';
  horaFin: string = '';
  fecha: string = '';

  capacidadSeleccionada: number = 0;
  consulta: Consulta[];
  reservas: ReservaConsulta[];

  cubiculoSeleccionado: any;

  constructor(private consultaService:ConsultaService, private reservaService: ReservaService) {
    this.generarFechas();
    this.generarCapacidad();
  }

  ngOnInit(): void {
    this.fecha = ''
    this.generarHoras();
    console.log("Pag iniciada");
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['horaInicio']) {
      this.horaFin = '';  // Reinicia horaFin cuando horaInicio cambie
      this.actualizarHorasFin();  // Llama a la función que actualiza las horas de fin
    }
  }

  generarCapacidad() {
    this.capacidad= [];
    for (let i = 1; i <= 6; i++) {
      this.capacidad.push(i);
    }
  }

  generarHoras() {
    const hoy = new Date();
    if(this.fecha == hoy.toLocaleDateString()){
      this.horas = [];
      const horas = hoy.getHours();
      const minutis = hoy.getMinutes();
      if(minutis <= 40){
        for (let i = horas; i <= 18; i++) {
          const hora = `${i.toString().padStart(2, '0')}:00`;
          this.horas.push(hora);
        }
      }else{
        for (let i = horas + 1; i <= 18; i++) {
          const hora = `${i.toString().padStart(2, '0')}:00`;
          this.horas.push(hora);
        }
      }
      
    }else{
      this.horas = [];
      for (let i = 6; i <= 18; i++) {
        const hora = `${i.toString().padStart(2, '0')}:00`;
        this.horas.push(hora);
      }
    }
  }

  generarFechas() {
    const hoy = new Date();
    for (let i = 0; i <= 5; i++) {
      const fecha = new Date(hoy);
      fecha.setDate(hoy.getDate() + i);
      this.fechas.push(fecha.toLocaleDateString());
    }
  }

  // Función para actualizar las horas de fin en función de la hora de inicio
  actualizarHorasFin() {
    setTimeout(() => {
      // Limpiar la hora de fin
      this.horaFin = '';
  
      if (!this.horaInicio) {
        this.horasFinal = [];
        return;
      }
  
      const indexInicio = this.horas.indexOf(this.horaInicio);
      if (indexInicio !== -1) {
        // Si la hora de inicio es antes de las 17:00 (como 16:00), mostramos las siguientes horas disponibles
        if (indexInicio < this.horas.indexOf('17:00')) {
          this.horasFinal = [this.horas[indexInicio + 1], this.horas[indexInicio + 2]].filter(Boolean);
        } 
        // Si la hora de inicio es 17:00, mostramos 18:00 y 19:00
        else if (indexInicio === this.horas.indexOf('17:00')) {
          this.horasFinal = ['18:00', '19:00']; // Mostrar 18:00 y 19:00
        }
        // Si la hora de inicio es 18:00, solo mostramos 19:00
        else if (indexInicio === this.horas.indexOf('18:00')) {
          this.horasFinal = ['19:00']; // Solo la opción de 19:00
        } 
        else {
          this.horasFinal = [];
        }
  
        // Seleccionamos automáticamente la primera opción de hora fin
        this.horaFin = this.horasFinal[0] || ''; // Si no hay opciones, dejamos vacía la selección
      }
    });
  }

  openModal(cubiculo: any) {
    this.cubiculoSeleccionado = cubiculo;
    this.ModalConfirmacionReservaComponent.open();  // Abre el modal
    console.log("Capacidad de asistentes: ",this.capacidadSeleccionada)
    this.ModalConfirmacionReservaComponent.setCapacidad(this.capacidadSeleccionada);
    this.ModalConfirmacionReservaComponent.setCubiculo(this.cubiculoSeleccionado)
  }

  openVerReservas(){
    this.reservaService.listarReservas(57).subscribe( respuesta =>{
      this.reservas = respuesta
      this.ModalVerReservasComponent.setReservas(this.reservas);
      this.ModalVerReservasComponent.open();
    })
    
  }

  buscarCubiculos() {
    const fechaFormateada = formatDate(this.fecha);

    const horaInicio24h = this.horaInicio;
    const horaFin24h = this.horaFin;

    const fechaYHoraInicio = `${fechaFormateada}T${horaInicio24h}:00`;
    const fechaYHoraFin = `${fechaFormateada}T${horaFin24h}:00`;
    this.consultaService.consultaPrincipal(fechaYHoraInicio,fechaYHoraFin,this.capacidadSeleccionada).subscribe(query =>{
      this.consulta = query;
      console.log(this.consulta);
    })

    console.log(fechaYHoraInicio);
    console.log(fechaYHoraFin);
    console.log(this.capacidadSeleccionada);
  }

  onFechaChange() {

    this.generarHoras()

    this.horaInicio = '';
    this.horaFin = '';
    this.horasFinal = [];
  }

  formatHora(cubiculoHora: Date): string {
    const fecha = new Date(cubiculoHora);  // Convierte el string a Date
    return fecha.toLocaleTimeString('en-US', { hour: '2-digit', minute: '2-digit' });
  }
}


function formatDate(dateStr: string): string {
  const [day, month, year] = dateStr.split('/'); // Dividir la fecha en partes
  const date = new Date(`${year}-${month}-${day}`); // Crear un objeto Date
  const formattedDate = date.toISOString().split('T')[0]; // Formatear a 'yyyy-mm-dd'
  return formattedDate;
}


