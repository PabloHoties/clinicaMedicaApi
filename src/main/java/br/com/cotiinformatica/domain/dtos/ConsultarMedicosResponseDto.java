package br.com.cotiinformatica.domain.dtos;

import java.util.List;
import java.util.UUID;

import br.com.cotiinformatica.domain.entities.Atendimento;
import lombok.Data;

@Data
public class ConsultarMedicosResponseDto {

	private UUID id;
	private String nome;
	private String crm;
	private String especializacao;
	private List<Atendimento> atendimentos;
}
