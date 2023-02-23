package com.examejpa.examenjpa;

import com.examejpa.examenjpa.cabecerafra.application.FacturaService;
import com.examejpa.examenjpa.cabecerafra.infraestructure.repository.CabeceraFraRepository;
import com.examejpa.examenjpa.cliente.application.ClienteService;
import com.examejpa.examenjpa.cabecerafra.domain.CabeceraFra;
import com.examejpa.examenjpa.cliente.domain.Cliente;
import com.examejpa.examenjpa.lineafra.domain.LineasFra;
import com.examejpa.examenjpa.lineafra.infraestructure.repository.LineasFraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ExamenJpaApplication implements CommandLineRunner {
	@Autowired
	CabeceraFraRepository cabeceraFraRepository;

	@Autowired
	ClienteService clienteService;

	@Autowired
	LineasFraRepository serviceFraRepository;




	public static void main(String[] args) {
		SpringApplication.run(ExamenJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	Cliente cliente = new Cliente();
	cliente.setName("carlos");
	clienteService.save(cliente);



	CabeceraFra cabeceraFra  = new CabeceraFra(cliente);

	List<LineasFra> listLineFra = new ArrayList<>();
	LineasFra lineasFra = new LineasFra("pera",12.5,10);
	LineasFra lineasFra1 = new LineasFra("manzana",6,2);
	listLineFra.add(lineasFra);
	listLineFra.add(lineasFra1);
	cabeceraFra.setItems(listLineFra);
	cabeceraFraRepository.save(cabeceraFra);







	}
}
