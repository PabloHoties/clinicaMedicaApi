package br.com.cotiinformatica.domain.interfaces;

import java.util.List;
import java.util.UUID;

import br.com.cotiinformatica.domain.dtos.CriarMedicoRequestDto;
import br.com.cotiinformatica.domain.dtos.CriarMedicoResponseDto;

public interface MedicoDomainService {

	CriarMedicoResponseDto criarMedico(CriarMedicoRequestDto dto);
	
	List<CriarMedicoResponseDto> consultarMedicos();
	
	CriarMedicoResponseDto obterMedico(UUID id);
}
