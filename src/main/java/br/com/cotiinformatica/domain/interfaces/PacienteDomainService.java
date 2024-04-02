package br.com.cotiinformatica.domain.interfaces;

import java.util.List;
import java.util.UUID;

import br.com.cotiinformatica.domain.dtos.ConsultarPacientesResponseDto;
import br.com.cotiinformatica.domain.dtos.CriarPacienteRequestDto;
import br.com.cotiinformatica.domain.dtos.CriarPacienteResponseDto;

public interface PacienteDomainService {

	CriarPacienteResponseDto criarPaciente(CriarPacienteRequestDto dto);

	List<ConsultarPacientesResponseDto> consultarPacientes();

	ConsultarPacientesResponseDto obterPaciente(UUID id);

}
