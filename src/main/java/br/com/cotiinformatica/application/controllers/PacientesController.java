package br.com.cotiinformatica.application.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.domain.dtos.CriarPacienteRequestDto;
import br.com.cotiinformatica.domain.dtos.CriarPacienteResponseDto;
import br.com.cotiinformatica.domain.interfaces.PacienteDomainService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/pacientes")
public class PacientesController {

	@Autowired
	private PacienteDomainService pacienteDomainService;
	
	@PostMapping("criar")
	public ResponseEntity<CriarPacienteResponseDto> criar(@RequestBody @Valid CriarPacienteRequestDto dto) {
		
		CriarPacienteResponseDto response = pacienteDomainService.criarPaciente(dto);
		
		return ResponseEntity.status(201).body(response);
	}
	
	@GetMapping("consultar")
	public void consultar( ) {
		// TODO
	}
	
	@GetMapping("obter/{id}")
	public void obter(@PathVariable("id") UUID id) {
		
	}
}
