import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing/app-routing.module';
import { AppComponent } from './app.component';
import { ClientesModule } from './clientes/clientes.module';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import {HttpClientModule} from '@angular/common/http';
import { RouterLink, RouterModule } from '@angular/router';
import { RegionesModule } from './regiones/regiones.module';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ClientesModule,
    HttpClientModule,
    RouterModule,
    RegionesModule

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
