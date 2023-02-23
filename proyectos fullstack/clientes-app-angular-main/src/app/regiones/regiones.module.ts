import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TablaRegionComponent } from './tabla-region/tabla-region.component';



@NgModule({
  declarations: [
    TablaRegionComponent
  ],
  exports:[
    TablaRegionComponent
  ],
  imports: [
    CommonModule
  ]
})
export class RegionesModule { }
