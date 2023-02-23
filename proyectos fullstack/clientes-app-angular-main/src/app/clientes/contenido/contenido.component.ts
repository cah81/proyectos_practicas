import { Component, OnInit } from '@angular/core';
import swal from 'sweetalert2';
import { ClienteService } from '../cliente.service';
import { Cliente } from '../interface/cliente';

@Component({
  selector: 'app-contenido',
  templateUrl: './contenido.component.html',
  styleUrls: ['./contenido.component.css']
})
export class ContenidoComponent implements OnInit {
  clientes:Cliente[]=[];
  imagenSrc:string="";

  constructor(private servicio:ClienteService) { }

  ngOnInit(): void {
    this.imagenSrc='assets/avatar.jpg';

    this.servicio.getCliente().subscribe(
      resp => this.clientes=resp
    );

  }

  delete(cliente:Cliente):void{

    swal({
      title:'Estas seguro?',
      text:`Seguro que desea eliminar al cliente ${cliente.nombre} ${cliente.apellido}`,
      type:'warning',
      showCancelButton:true,
      confirmButtonColor:'#3085d6',
      cancelButtonColor:'#d33',
      confirmButtonText:'Sí, eliminar!',
      cancelButtonText:'No,cancelar!',
      confirmButtonClass:'btn btn-success',
      cancelButtonClass:'btn btn-danger',
      buttonsStyling:false,
      reverseButtons:true
    }).then((result)=>{

      if(result.value){

        this.servicio.delete(cliente.id).subscribe(
          resp =>{
            this.clientes = this.clientes.filter(cli =>cli !== cliente)
            swal('Cliente eliminado',`Cliente ${cliente.nombre} eliminado con éxito`,'success');
          }
        );
      }


    });



  }

}
