package br.com.cotiinformatica.domain.interfaces;

import java.util.List;
import java.util.UUID;

import br.com.cotiinformatica.domain.dtos.AutenticarMedicoRequestDto;
import br.com.cotiinformatica.domain.dtos.AutenticarMedicoResponseDto;
import br.com.cotiinformatica.domain.dtos.ConsultarMedicosResponseDto;
import br.com.cotiinformatica.domain.dtos.CriarMedicoRequestDto;
import br.com.cotiinformatica.domain.dtos.CriarMedicoResponseDto;

public interface MedicoDomainService {

	CriarMedicoResponseDto criarMedico(CriarMedicoRequestDto dto) throws Exception;
	
	List<ConsultarMedicosResponseDto> consultarMedicos();
	
	ConsultarMedicosResponseDto obterMedico(UUID id);
	
	AutenticarMedicoResponseDto autenticarMedico(AutenticarMedicoRequestDto dto);
}
