import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalVerReservasComponent } from './modal-ver-reservas.component';

describe('ModalVerReservasComponent', () => {
  let component: ModalVerReservasComponent;
  let fixture: ComponentFixture<ModalVerReservasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ModalVerReservasComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModalVerReservasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
