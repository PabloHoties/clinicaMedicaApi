package br.com.cotiinformatica.domain.dtos;

import java.util.Date;
import java.util.UUID;

import lombok.Data;

@Data
public class AutenticarMedicoResponseDto {

	private UUID id;
	private String nome;
	private String crm;
	private String senha;
	private String especializacao;
	private String token;
	private Date dataHoraAcesso;
}
