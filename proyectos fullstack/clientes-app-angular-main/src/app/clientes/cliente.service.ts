import { HttpClient, HttpEvent, HttpHandler, HttpHeaders, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { Cliente } from './interface/cliente';
import { Region } from './interface/region';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  urlBase:string="http://localhost:8087/api";

  constructor(private http:HttpClient) { }
//el metodo get para recuperar todos los clientes
  getCliente():Observable<Cliente[]>{
    return this.http.get(`${this.urlBase}/clientes`).pipe(
      map( (response) => response as Cliente[] )
    );
  }

//metodo post para insertar clientes
create(cliente:Cliente):Observable<Cliente>{
  return this.http.post<Cliente>(`${this.urlBase}/clientes`,cliente);
}

//buscar cliente por id
getClienteId(id:number):Observable<Cliente>{
  return this.http.get<Cliente>(`${this.urlBase}/clientes/${id}`);
}
//actualizar cliente
update(cliente:Cliente):Observable<Cliente>{
 return this.http.put<Cliente>(`${this.urlBase}/clientes/${cliente.id}`,cliente);
}
//eliminar cliente
delete(id:number):Observable<Cliente>{
 return this.http.delete<Cliente>(`${this.urlBase}/clientes/${id}`);
}

//mostrar region
getRegiones():Observable<Region[]>{
  return this.http.get<Region[]>(`${this.urlBase}/regiones`);
}

//subir imagen
subirImagen(archivo:File,id:any):Observable<HttpEvent<any>>{
  let formData = new FormData();
  formData.append("archivo",archivo);
  formData.append("id",id);

  let httpHeaders= new HttpHeaders();
  const req = new HttpRequest('POST',`${this.urlBase}/clientes/uploads`,formData,{headers:httpHeaders});
  return this.http.request(req).pipe(
    resp=>resp
  );
}


}
