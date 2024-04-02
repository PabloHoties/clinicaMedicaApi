package br.com.cotiinformatica.domain.dtos;

import java.util.Date;
import java.util.UUID;

import lombok.Data;

@Data
public class CriarMedicoResponseDto {

	private UUID id;
	private String nome;
	private String crm;
	private String especializacao;
	private Date dataHoraCadastro;
}
