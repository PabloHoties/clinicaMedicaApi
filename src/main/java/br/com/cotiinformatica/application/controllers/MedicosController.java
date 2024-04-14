package br.com.cotiinformatica.application.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.domain.dtos.ConsultarMedicosResponseDto;
import br.com.cotiinformatica.domain.dtos.CriarMedicoRequestDto;
import br.com.cotiinformatica.domain.dtos.CriarMedicoResponseDto;
import br.com.cotiinformatica.domain.interfaces.MedicoDomainService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/medicos")
public class MedicosController {

	@Autowired
	private MedicoDomainService medicoDomainService;

	@PostMapping("criar")
	public ResponseEntity<CriarMedicoResponseDto> criar(@RequestBody @Valid CriarMedicoRequestDto dto) {

		CriarMedicoResponseDto response = medicoDomainService.criarMedico(dto);

		return ResponseEntity.status(201).body(response);
	}

	@GetMapping("consultar")
	public List<ConsultarMedicosResponseDto> consultar() {
		return medicoDomainService.consultarMedicos();
	}

	@GetMapping("obter/{id}")
	public ConsultarMedicosResponseDto obter(@PathVariable("id") UUID id) {
		return medicoDomainService.obterMedico(id);
	}
	
	@PostMapping("autenticar")
	public void autenticar() {
		// TODO
	}
}
