import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import swal from 'sweetalert2';
import { ClienteService } from '../cliente.service';
import { Cliente } from '../interface/cliente';
import { Region } from '../interface/region';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {
  cliente:Cliente = new Cliente();
  regiones:Region[]=[];

  constructor(private servicio:ClienteService,
     private activateRoute:ActivatedRoute,private router:Router) { }

  ngOnInit(): void {
    this.servicio.getRegiones().subscribe(
      resp => this.regiones = resp
    );
    this.activateRoute.paramMap.subscribe(
      params=>{
        let id = +params.get('id')!;
        if(id){
          this.servicio.getClienteId(id).subscribe(
            (resp) => this.cliente= resp
          );
        }
      }
    );

  }


  compararRegion(o1:Region,o2:Region):boolean{
    if(o1===undefined && o2===undefined){
      return true;
    }
    return o1 ===null || o2===null || o1===undefined ||
     o2===undefined?false:o1.id===o2.id;
  }

  crear():void{
    console.log(this.cliente);
    this.servicio.create(this.cliente).subscribe(
      resp=>{
        swal('Nuevo cliente',`${this.cliente.nombre} creado con éxito`,'success');
        this.router.navigate(['/clientes']);
      },
      err=>{
        console.log('Codigo de error backend',err.status);
      }
    );
  }

  actualizar():void{
    console.log(this.cliente);
    this.servicio.update(this.cliente).subscribe(
      resp=>{
        swal('Cliente actualizado',`${this.cliente.nombre} actualizado con éxito`,'success');
        this.router.navigate(['/clientes']);
      },
      err=>{
        console.log('Codigo de error backend',err.status);
      }
    );
  }

}
