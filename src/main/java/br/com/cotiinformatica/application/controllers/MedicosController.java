package br.com.cotiinformatica.application.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.domain.dtos.AutenticarMedicoRequestDto;
import br.com.cotiinformatica.domain.dtos.AutenticarMedicoResponseDto;
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

	@PostMapping(value = "criar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<CriarMedicoResponseDto> criar(@ModelAttribute @Valid CriarMedicoRequestDto dto) throws Exception {

		String tipo = dto.getFoto().getContentType();
		if (tipo.equals("image/jpeg") || tipo.equals("image/jpg") || tipo.equals("image/png")) {
			dto.setFotoByte(dto.getFoto().getBytes());
			
			CriarMedicoResponseDto response = medicoDomainService.criarMedico(dto);
			return ResponseEntity.status(201).body(response);
		} else {
			throw new IllegalArgumentException("Erro. A foto deve ser do tipo jpeg, jpg ou png.");
		}

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
	public ResponseEntity<AutenticarMedicoResponseDto> autenticar(@RequestBody @Valid AutenticarMedicoRequestDto dto) {		
		AutenticarMedicoResponseDto response = medicoDomainService.autenticarMedico(dto);
		return ResponseEntity.status(200).body(response);
	}
}
