package com.formacionspring.apirest.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.formacionspring.apirest.entity.Cliente;
import com.formacionspring.apirest.entity.Region;
import com.formacionspring.apirest.service.ClienteService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200/")
public class ClienteRestController {

	@Autowired
	private ClienteService servicio;
	
	//metodo para mostrar todos los clientes
	@GetMapping({"/clientes","/"})
	public List<Cliente> index(){
		return servicio.mostrarTodos();
	}
	//metodo para mostrar un cliente por id
	/*@GetMapping("/clientes/{id}")
	public Cliente show(@PathVariable long id) {
		return servicio.mostrarPorId(id);
	}*/
	@GetMapping("/clientes/{id}")
	public ResponseEntity<?> show(@PathVariable long id) {
		
		Cliente cliente = null;
		Map<String,Object>  response = new HashMap<>();

		try {
			
			cliente = servicio.mostrarPorId(id);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar en base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(cliente == null) {
			response.put("mensaje", "El cliente con ID: "+id+" no existe en la base de datos");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Cliente>(cliente,HttpStatus.OK);
	}
	
	
	//metodo para crear un nuevo cliente
	/*@PostMapping("/clientes")
	public Cliente create(@RequestBody Cliente cliente) {
		return servicio.guardar(cliente);
	}*/
	@PostMapping("/clientes")
	public ResponseEntity<?> create(@RequestBody Cliente cliente) {
		Cliente clienteNew = null;
		Map<String,Object>  response = new HashMap<>();
		
		try {
			
			clienteNew =  servicio.guardar(cliente);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar en base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje","El cliente ha sido creado con éxito");
		response.put("cliente", clienteNew);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		
		
	}
	
	/*@PutMapping("/clientes/{id}")
	public Cliente update(@RequestBody Cliente cliente
			,@PathVariable Long id) {
		//buscar al registro por id y guardo el objeto en clienteUpdate
		Cliente clienteUpdate = servicio.mostrarPorId(id);
		//reemplazo los datos de clienteUpdate por el modelo recibido
		//en @RequesBody
		clienteUpdate.setNombre(cliente.getNombre());
		clienteUpdate.setApellido(cliente.getApellido());
		clienteUpdate.setEmail(cliente.getEmail());
		clienteUpdate.setTelefono(cliente.getTelefono());
		clienteUpdate.setCreateAt(cliente.getCreateAt());
		
		//guardo y retorno los datos actualizados
		return servicio.guardar(clienteUpdate);
			
	}*/
	
	@PutMapping("/clientes/{id}")
	public ResponseEntity<?> update(@RequestBody Cliente cliente
			,@PathVariable Long id) {
		
		Cliente clienteUpdate =  servicio.mostrarPorId(id);
		Map<String,Object>  response = new HashMap<>();
		
		
		if(clienteUpdate == null) {
			response.put("mensaje","No existe el registro con id:"+id);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		try {
			
			clienteUpdate.setNombre(cliente.getNombre());
			clienteUpdate.setApellido(cliente.getApellido());
			clienteUpdate.setEmail(cliente.getEmail());
			clienteUpdate.setTelefono(cliente.getTelefono());
			clienteUpdate.setCreateAt(cliente.getCreateAt());
			
			if(cliente.getImagen()!=null) {
				String nombreFotoAnterior = cliente.getImagen();
				//verificamos que el cliente tenga registrado una imagen
				if(nombreFotoAnterior != null && nombreFotoAnterior.length()>0) {
					//preparamos la ruta a la imagen guardada
					Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
					File archivoFotoAnterior = rutaFotoAnterior.toFile();
					//verificamos que el archivo existe y se pueda leer
					if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
						//borramos la imagen
						archivoFotoAnterior.delete();
					}
				}
				clienteUpdate.setImagen(cliente.getImagen());
			}
			
			//guardo y retorno los datos actualizados
			servicio.guardar(clienteUpdate);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar en base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje","El cliente ha sido actualizado con éxito");
		response.put("cliente", clienteUpdate);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		
	}
	
	
	
	//Borra un registro de cliente por id
	/*@DeleteMapping("/clientes/{id}")
	public void delete(@PathVariable Long id) {
		servicio.borrar(id);
	}*/
	//esto lo hago pa divertirme pa divertirme
	/*@DeleteMapping("/clientes/{id}")
	public Cliente delete(@PathVariable Long id) {
		Cliente clienteBorrado = servicio.mostrarPorId(id);
		servicio.borrar(id);
		return clienteBorrado;
	}*/
	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Cliente clienteBorrado = servicio.mostrarPorId(id);
		Map<String,Object>  response = new HashMap<>();
		
		if(clienteBorrado == null) {
			response.put("mensaje","No existe el registro con id:"+id);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		try {
			
			if(clienteBorrado.getImagen()!=null) {
				String nombreFotoAnterior = clienteBorrado.getImagen();
				//verificamos que el cliente tenga registrado una imagen
				if(nombreFotoAnterior != null && nombreFotoAnterior.length()>0) {
					//preparamos la ruta a la imagen guardada
					Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
					File archivoFotoAnterior = rutaFotoAnterior.toFile();
					//verificamos que el archivo existe y se pueda leer
					if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
						//borramos la imagen
						archivoFotoAnterior.delete();
					}
				}
				
			}
			
			servicio.borrar(id);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar en base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje","El cliente ha sido eliminado con éxito");
		response.put("cliente", clienteBorrado);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		
		
	}
	
	@PostMapping("/clientes/uploads")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo,
			@RequestParam("id") Long id){
		
		Map<String,Object>  response = new HashMap<>();
		//buscar el cliente por el id recibido
		Cliente cliente = servicio.mostrarPorId(id);
		
		//preguntamos si el archivo es distinto de vacio
		if( !archivo.isEmpty() ) {
			//guardamos el nombre del archivo en esta variable
			String nombreArchivo =  UUID.randomUUID().toString()+"_"+archivo.getOriginalFilename().replace(" ", "");
			
			//guardamos la ruta completa uploads/nombredelaimagen lo guardamos en
			//una variable de tipo path que es de java.io
			
			Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
		
			try {
				//copiamos el archivo fisico a la ruta que definimos en Path
				Files.copy(archivo.getInputStream(), rutaArchivo );
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen del cliente");
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			
			String nombreFotoAnterior = cliente.getImagen();
			//verificamos que el cliente tenga registrado una imagen
			if(nombreFotoAnterior != null && nombreFotoAnterior.length()>0) {
				//preparamos la ruta a la imagen guardada
				Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
				File archivoFotoAnterior = rutaFotoAnterior.toFile();
				//verificamos que el archivo existe y se pueda leer
				if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
					//borramos la imagen
					archivoFotoAnterior.delete();
				}
			}
			
		
			//guardamos el nombre de la imagen
			cliente.setImagen(nombreArchivo);
			//registramos en base de datos
			servicio.guardar(cliente);
			
			response.put("cliente", cliente);
			response.put("mensaje","Imagen subida correctamente :"+nombreArchivo);
		
		}
		
	
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	
	
	@GetMapping("/uploads/img/{nombreImagen:.+}")
	public ResponseEntity<Resource> verImagen(@PathVariable String nombreImagen){
	
		Path rutaArchivo = Paths.get("uploads").resolve(nombreImagen).toAbsolutePath();
		
		Resource recurso = null;
		
		try {
			//codigo para acceder al archivo por url
			recurso = new UrlResource(rutaArchivo.toUri());
			
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		}
		
		if(!recurso.exists() && !recurso.isReadable()) {
			throw new RuntimeException("no se puede cargar a la imagen: "+nombreImagen);
		}
		
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=\""+recurso.getFilename()+"\"");
		
		
		
		return new ResponseEntity<Resource>(recurso,cabecera,HttpStatus.OK);
	}
	
	//metodo paraf mostrar todas la regiones
	@GetMapping("/regiones")
	public List<Region> showRegions(){
		return servicio.mostrarRegiones();
	}
	
}
