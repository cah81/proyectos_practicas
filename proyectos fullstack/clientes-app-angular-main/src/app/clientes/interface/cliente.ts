import { Region } from "./region";

export class Cliente{
  id!:number;
  nombre!:string;
  apellido!:string;
  email!:string;
  telefono!:number;
  createAt!:string;
  imagen!:string;
  region!:Region;
}
