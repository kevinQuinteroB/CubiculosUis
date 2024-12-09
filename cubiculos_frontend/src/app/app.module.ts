import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';
import { provideHttpClient, withFetch } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PaginaInicialComponent } from './pagina-inicial/pagina-inicial.component';
import { BuscadorComponent } from './buscador/buscador.component';
import { ModalConfirmacionReservaComponent } from './modal-confirmacion-reserva/modal-confirmacion-reserva.component';
import { FormsModule } from '@angular/forms';
import { ModalVerReservasComponent } from './modal-ver-reservas/modal-ver-reservas.component';

@NgModule({
  declarations: [
    AppComponent,
    PaginaInicialComponent,
    BuscadorComponent,
    ModalConfirmacionReservaComponent,
    ModalVerReservasComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [
    provideClientHydration(),
    provideHttpClient(withFetch())
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
