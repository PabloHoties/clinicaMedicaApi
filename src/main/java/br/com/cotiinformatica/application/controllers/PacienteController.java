package br.com.cotiinformatica.application.controllers;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/pacientes")
public class PacienteController {

	@PostMapping("criar")
	public void criar() {
		// TODO
	}
	
	@GetMapping("consultar")
	public void consultar( ) {
		// TODO
	}
	
	@GetMapping("obter/{id}")
	public void obter(@PathVariable("id") UUID id) {
		
	}
}
