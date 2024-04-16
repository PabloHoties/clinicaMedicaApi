package br.com.cotiinformatica.domain.interfaces;

import java.util.List;
import java.util.UUID;

import br.com.cotiinformatica.domain.dtos.AutenticarMedicoRequestDto;
import br.com.cotiinformatica.domain.dtos.AutenticarMedicoResponseDto;
import br.com.cotiinformatica.domain.dtos.AutenticarPacienteResponseDto;
import br.com.cotiinformatica.domain.dtos.AutenticarPacienteResquestDto;
import br.com.cotiinformatica.domain.dtos.ConsultarPacientesResponseDto;
import br.com.cotiinformatica.domain.dtos.CriarPacienteRequestDto;
import br.com.cotiinformatica.domain.dtos.CriarPacienteResponseDto;

public interface PacienteDomainService {

	CriarPacienteResponseDto criarPaciente(CriarPacienteRequestDto dto);

	List<ConsultarPacientesResponseDto> consultarPacientes();

	ConsultarPacientesResponseDto obterPaciente(UUID id);

	AutenticarPacienteResponseDto autenticarPaciente(AutenticarPacienteResquestDto dto);
}
