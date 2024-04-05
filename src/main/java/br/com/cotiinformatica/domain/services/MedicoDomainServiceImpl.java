package br.com.cotiinformatica.domain.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotiinformatica.domain.dtos.ConsultarMedicosResponseDto;
import br.com.cotiinformatica.domain.dtos.CriarMedicoRequestDto;
import br.com.cotiinformatica.domain.dtos.CriarMedicoResponseDto;
import br.com.cotiinformatica.domain.entities.Medico;
import br.com.cotiinformatica.domain.interfaces.MedicoDomainService;
import br.com.cotiinformatica.infrastructure.repositories.MedicoRepository;

@Service
public class MedicoDomainServiceImpl implements MedicoDomainService {

	@Autowired
	private MedicoRepository medicoRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CriarMedicoResponseDto criarMedico(CriarMedicoRequestDto dto) {

		if (medicoRepository.findByCrm(dto.getCrm()) != null)
			throw new IllegalArgumentException("O CRM informado já está cadastrado.");

		Medico medico = modelMapper.map(dto, Medico.class);
		medico.setId(UUID.randomUUID());

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

}
