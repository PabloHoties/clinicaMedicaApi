package br.com.cotiinformatica.domain.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotiinformatica.domain.dtos.AutenticarPacienteResponseDto;
import br.com.cotiinformatica.domain.dtos.AutenticarPacienteResquestDto;
import br.com.cotiinformatica.domain.dtos.ConsultarPacientesResponseDto;
import br.com.cotiinformatica.domain.dtos.CriarPacienteRequestDto;
import br.com.cotiinformatica.domain.dtos.CriarPacienteResponseDto;
import br.com.cotiinformatica.domain.entities.Paciente;
import br.com.cotiinformatica.domain.interfaces.PacienteDomainService;
import br.com.cotiinformatica.infrastructure.components.SHA256Component;
import br.com.cotiinformatica.infrastructure.components.TokenComponent;
import br.com.cotiinformatica.infrastructure.repositories.PacienteRepository;

@Service
public class PacienteDomainServiceImpl implements PacienteDomainService {

	@Autowired
	private PacienteRepository pacienteRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private SHA256Component sha256Component;
	
	@Autowired
	private TokenComponent tokenComponent;

	@Override
	public CriarPacienteResponseDto criarPaciente(CriarPacienteRequestDto dto) {

		if (pacienteRepository.findByCpf(dto.getCpf()) != null)
			throw new IllegalArgumentException("O CPF informado já está cadastrado.");

		Paciente paciente = modelMapper.map(dto, Paciente.class);

		paciente.setId(UUID.randomUUID());
		paciente.setSenha(sha256Component.criptografarSHA256(dto.getSenha()));

		pacienteRepository.save(paciente);

		CriarPacienteResponseDto response = modelMapper.map(paciente, CriarPacienteResponseDto.class);
		response.setDataHoraCadastro(new Date());

		return response;
	}

	@Override
	public List<ConsultarPacientesResponseDto> consultarPacientes() {

		List<Paciente> pacientes = pacienteRepository.findAll();

		List<ConsultarPacientesResponseDto> response = modelMapper.map(pacientes,
				new TypeToken<List<ConsultarPacientesResponseDto>>() {
				}.getType());

		return response;
	}

	@Override
	public ConsultarPacientesResponseDto obterPaciente(UUID id) {

		Optional<Paciente> paciente = pacienteRepository.findById(id);

		if (paciente.isPresent()) {

			ConsultarPacientesResponseDto response = modelMapper.map(paciente, ConsultarPacientesResponseDto.class);
			return response;
		} else {
			return null;
		}
	}

	@Override
	public AutenticarPacienteResponseDto autenticarPaciente(AutenticarPacienteResquestDto dto) {
		
		Paciente paciente = pacienteRepository.findByCpfAndSenha(dto.getCpf(), sha256Component.criptografarSHA256(dto.getSenha()));
		
		if (paciente != null) {
			AutenticarPacienteResponseDto response = modelMapper.map(paciente, AutenticarPacienteResponseDto.class);
			response.setDataHoraAcesso(new Date());
			
			try {
				response.setToken(tokenComponent.generateToken(paciente.getCpf()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return response;
		} else {
			throw new IllegalAccessError("Acesso negado. Paciente não encontrado.");
		}
	}

}
