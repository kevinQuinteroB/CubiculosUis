import { Component, Output, EventEmitter } from '@angular/core';
import { ReservaConsulta } from '../models/reserva-consulta';

@Component({
  selector: 'app-modal-ver-reservas',
  templateUrl: './modal-ver-reservas.component.html',
  styleUrl: './modal-ver-reservas.component.css'
})
export class ModalVerReservasComponent {
  isVisible: boolean = false;
  reservas: ReservaConsulta[];

  @Output() onConfirm: EventEmitter<void> = new EventEmitter();
  @Output() onClose: EventEmitter<void> = new EventEmitter();

  constructor() { }

  open() {
    this.isVisible = true;
  }

  setReservas(reservas: ReservaConsulta[]){
    this.reservas = reservas;
  }

  close() {
    this.isVisible = false;
    this.onClose.emit();
  }
}
