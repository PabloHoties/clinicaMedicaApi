package br.com.cotiinformatica.domain.services;

import java.util.Date;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotiinformatica.domain.dtos.CriarPacienteRequestDto;
import br.com.cotiinformatica.domain.dtos.CriarPacienteResponseDto;
import br.com.cotiinformatica.domain.entities.Paciente;
import br.com.cotiinformatica.domain.interfaces.PacienteDomainService;
import br.com.cotiinformatica.infrastructure.repositories.PacienteRepository;

@Service
public class PacienteDomainServiceImpl implements PacienteDomainService {

	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CriarPacienteResponseDto criarPaciente(CriarPacienteRequestDto dto) {
		
		if(pacienteRepository.findByCpf(dto.getCpf()) != null)
			throw new IllegalArgumentException("O CPF informado já está cadastrado.");
		
		Paciente paciente = modelMapper.map(dto, Paciente.class);
		
		paciente.setId(UUID.randomUUID());
		
		pacienteRepository.save(paciente);
		
		CriarPacienteResponseDto response = modelMapper.map(paciente, CriarPacienteResponseDto.class);
		response.setDataHoraCadastro(new Date());
		
		return response;
	}

}
