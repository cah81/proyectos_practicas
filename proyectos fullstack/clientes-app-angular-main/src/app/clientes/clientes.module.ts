import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DetalleComponent } from './detalle/detalle.component';
import { ContenidoComponent } from './contenido/contenido.component';
import { FormComponent } from './form/form.component';
import { AppRoutingModule } from '../app-routing/app-routing.module';
import { FormsModule } from '@angular/forms';



@NgModule({
  declarations: [
    DetalleComponent,
    ContenidoComponent,
    FormComponent
  ],
  exports:[
    DetalleComponent,
    ContenidoComponent,
    FormComponent
  ],
  imports: [
    CommonModule,
    AppRoutingModule,
    FormsModule
  ]
})
export class ClientesModule { }
