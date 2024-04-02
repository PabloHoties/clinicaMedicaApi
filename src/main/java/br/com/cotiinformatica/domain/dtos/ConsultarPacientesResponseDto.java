package br.com.cotiinformatica.domain.dtos;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import br.com.cotiinformatica.domain.entities.Atendimento;
import lombok.Data;

@Data
public class ConsultarPacientesResponseDto {

	private UUID id;
	private String nome;
	private String cpf;
	private String sexo;
	private Date dataNascimento;
	private List<Atendimento> atendimentos;
}
