import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PaginaInicialComponent } from './pagina-inicial/pagina-inicial.component';
import { BuscadorComponent } from './buscador/buscador.component';
import { ModalConfirmacionReservaComponent } from './modal-confirmacion-reserva/modal-confirmacion-reserva.component';

@NgModule({
  declarations: [
    AppComponent,
    PaginaInicialComponent,
    BuscadorComponent,
    ModalConfirmacionReservaComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [
    provideClientHydration()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
