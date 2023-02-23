import { HttpEventType } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import swal from 'sweetalert2';
import { ClienteService } from '../cliente.service';
import { Cliente } from '../interface/cliente';

@Component({
  selector: 'app-detalle',
  templateUrl: './detalle.component.html',
  styleUrls: ['./detalle.component.css']
})
export class DetalleComponent implements OnInit {

  cliente!:Cliente;
  fotoSeleccionada!:File;

  constructor(private servicio:ClienteService,
    private activatedRoute:ActivatedRoute) { }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(
      params=>{
        let id:number = +params.get('id')!;
        if(id){
          this.servicio.getClienteId(id).subscribe(
            resp => this.cliente = resp
          );
        }
      }
    );
  }
  seleccionarImagen(event:any){
    this.fotoSeleccionada=event.target.files[0];
    console.log(this.fotoSeleccionada);
  }

  subirImagen():void{
    if(!this.fotoSeleccionada){
      swal('Error','Debe seleccionar una imagen','error');
    }else{
      this.servicio.subirImagen(this.fotoSeleccionada,this.cliente.id).subscribe( event=>{
        if(event.type===HttpEventType.Response){
          let response:any = event.body;
          this.cliente = response.cliente as Cliente;
          swal('La imagen se ha subido correctamente',response.mensaje,'success');
        }

      }

      );
    }
  }

}
