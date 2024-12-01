import { Component, Output, EventEmitter } from '@angular/core';
import { ReservaService } from '../services/reserva.service';
import { Reserva } from '../models/reserva';

@Component({
  selector: 'app-modal-confirmacion-reserva',
  templateUrl: './modal-confirmacion-reserva.component.html',
  styleUrl: './modal-confirmacion-reserva.component.css'
})
export class ModalConfirmacionReservaComponent {
  isVisible: boolean = false;
  cantidadAsistentes: number = 0;
  cubiculo: any;

  asistentes: { codigo: string, nombre: string }[] = [];
  codigoAsistentes: { codigo: number }[] = [];
  terminosAceptados: boolean = false;
  compromisoAceptado: boolean = false;

  reserva: Reserva;

  @Output() onConfirm: EventEmitter<void> = new EventEmitter();
  @Output() onClose: EventEmitter<void> = new EventEmitter();

  constructor(private reservaService: ReservaService) { }

  open() {
    this.isVisible = true;
  }

  setCapacidad(capacidad: number) {
    this.cantidadAsistentes = Number(capacidad) - 1;
    console.log("En el modal: ", this.cantidadAsistentes)
    this.asistentes = Array.from({ length: this.cantidadAsistentes }, () => ({ codigo: '', nombre: '' }));
  }

  close() {
    this.isVisible = false;
    this.onClose.emit();
    console.log(this.cantidadAsistentes);
  }

  confirm() {
    console.log("Datos Peticion")
    console.log(this.asistentes)
    console.log(this.cubiculo)

    this.codigoAsistentes = this.asistentes.map(asistente => ({
      codigo: Number(asistente.codigo)  // Aseguramos que el código sea un número
    }));

    console.log("Códigos de asistentes (formato JSON):", this.codigoAsistentes);

    this.reservaService.confirmarReserva(
      this.cubiculo.idCubiculo,
      2,
      this.cubiculo.fechaHoraInicio,
      this.cubiculo.fechaHoraFin,
      this.codigoAsistentes
    ).subscribe(Response => {
      this.reserva = Response;
      console.log(Reserva)
    }, error => {
      console.error("Error al confirmar reserva:", error);
    })
    this.onConfirm.emit();
    this.close();
  }

  setCubiculo(cubiculo: any) {
    this.cubiculo = cubiculo;
  }

  isFormValid(): boolean {
    const camposLlenos = this.asistentes.every(asistente => asistente.codigo !== '' && asistente.nombre !== '');
    return camposLlenos && this.terminosAceptados && this.compromisoAceptado;
  }

  formatHora(cubiculoHora: Date): string {
    const fecha = new Date(cubiculoHora);  // Convierte el string a Date
    return fecha.toLocaleTimeString('en-US', { hour: '2-digit', minute: '2-digit' });
  }
}
