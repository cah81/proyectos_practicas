import { Component, OnInit } from '@angular/core';
import { ClienteService } from 'src/app/clientes/cliente.service';
import { Region } from 'src/app/clientes/interface/region';

@Component({
  selector: 'app-tabla-region',
  templateUrl: './tabla-region.component.html',
  styleUrls: ['./tabla-region.component.css']
})
export class TablaRegionComponent implements OnInit {

  regiones:Region[]=[];

  constructor(private servicio:ClienteService) { }

  ngOnInit(): void {

    this.servicio.getRegiones().subscribe(
      (resp) =>{
        this.regiones= resp
      },
      (err)=>{
        console.log(err);
      }
    );

  }

}
