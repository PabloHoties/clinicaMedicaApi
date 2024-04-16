package br.com.cotiinformatica.domain.dtos;

import java.util.Date;
import java.util.UUID;

import lombok.Data;

@Data
public class AutenticarPacienteResponseDto {

	private UUID id;
	private String nome;
	private String cpf;
	private String senha;
	private String sexo;
	private Date dataNascimento;
	private String token;
	private Date dataHoraAcesso;
}
