package br.com.cotiinformatica.domain.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotiinformatica.domain.dtos.AutenticarMedicoRequestDto;
import br.com.cotiinformatica.domain.dtos.AutenticarMedicoResponseDto;
import br.com.cotiinformatica.domain.dtos.ConsultarMedicosResponseDto;
import br.com.cotiinformatica.domain.dtos.CriarMedicoRequestDto;
import br.com.cotiinformatica.domain.dtos.CriarMedicoResponseDto;
import br.com.cotiinformatica.domain.entities.Medico;
import br.com.cotiinformatica.domain.interfaces.MedicoDomainService;
import br.com.cotiinformatica.infrastructure.components.SHA256Component;
import br.com.cotiinformatica.infrastructure.components.TokenComponent;
import br.com.cotiinformatica.infrastructure.repositories.MedicoRepository;

@Service
public class MedicoDomainServiceImpl implements MedicoDomainService {

	@Autowired
	private MedicoRepository medicoRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private SHA256Component sha256Component;

	@Autowired
	private TokenComponent tokenComponent;

	@Override
	public CriarMedicoResponseDto criarMedico(CriarMedicoRequestDto dto) throws Exception {

		if (medicoRepository.findByCrm(dto.getCrm()) != null)
			throw new IllegalArgumentException("O CRM informado já está cadastrado.");

		Medico medico = modelMapper.map(dto, Medico.class);
		medico.setId(UUID.randomUUID());
		medico.setSenha(sha256Component.criptografarSHA256(dto.getSenha()));
		medico.setFoto(dto.getFoto().getBytes());

		medicoRepository.save(medico);

		CriarMedicoResponseDto response = modelMapper.map(medico, CriarMedicoResponseDto.class);
		response.setDataHoraCadastro(new Date());

		return response;
	}

	@Override
	public List<ConsultarMedicosResponseDto> consultarMedicos() {

		List<Medico> medicos = medicoRepository.findAll();

		List<ConsultarMedicosResponseDto> response = modelMapper.map(medicos,
				new TypeToken<List<ConsultarMedicosResponseDto>>() {
				}.getType());

		return response;
	}

	@Override
	public ConsultarMedicosResponseDto obterMedico(UUID id) {

		Optional<Medico> medico = medicoRepository.findById(id);

		if (medico.isPresent()) {
			ConsultarMedicosResponseDto response = modelMapper.map(medico, ConsultarMedicosResponseDto.class);
			return response;
		} else {
			return null;
		}
	}

	@Override
	public AutenticarMedicoResponseDto autenticarMedico(AutenticarMedicoRequestDto dto) {

		Medico medico = medicoRepository.findByCrmAndSenha(dto.getCrm(), sha256Component.criptografarSHA256(dto.getSenha()));

		if (medico != null) {
			AutenticarMedicoResponseDto response = modelMapper.map(medico, AutenticarMedicoResponseDto.class);
			response.setDataHoraAcesso(new Date());

			try {
				response.setToken(tokenComponent.generateToken(medico.getCrm()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return response;
		} else {
			throw new IllegalAccessError("Acesso negado. Médico não encontrado.");
		}
	}

}
