import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalConfirmacionReservaComponent } from './modal-confirmacion-reserva.component';

describe('ModalConfirmacionReservaComponent', () => {
  let component: ModalConfirmacionReservaComponent;
  let fixture: ComponentFixture<ModalConfirmacionReservaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ModalConfirmacionReservaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModalConfirmacionReservaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
