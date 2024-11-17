import { Component, Output, EventEmitter} from '@angular/core';

@Component({
  selector: 'app-modal-confirmacion-reserva',
  templateUrl: './modal-confirmacion-reserva.component.html',
  styleUrl: './modal-confirmacion-reserva.component.css'
})
export class ModalConfirmacionReservaComponent {
  isVisible: boolean = false;
  cantidadAsistentes: number = 0;

  @Output() onConfirm: EventEmitter<void> = new EventEmitter();
  @Output() onClose: EventEmitter<void> = new EventEmitter();

  open() {
    this.isVisible = true;
  }

  setCapacidad(capacidad: number) {
    this.cantidadAsistentes = capacidad;
  }

  close() {
    this.isVisible = false;
    this.onClose.emit();
    console.log(this.cantidadAsistentes);
  }
  
  confirm() {
    this.onConfirm.emit();
    this.close();
  }
}
