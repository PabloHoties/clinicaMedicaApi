package br.com.cotiinformatica.domain.interfaces;

import br.com.cotiinformatica.domain.dtos.CriarPacienteRequestDto;
import br.com.cotiinformatica.domain.dtos.CriarPacienteResponseDto;

public interface PacienteDomainService {

	CriarPacienteResponseDto criarPaciente (CriarPacienteRequestDto dto);
}
