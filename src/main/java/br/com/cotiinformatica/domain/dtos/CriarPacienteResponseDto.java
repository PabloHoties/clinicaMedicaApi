package br.com.cotiinformatica.domain.dtos;

import java.util.Date;
import java.util.UUID;

import lombok.Data;

@Data
public class CriarPacienteResponseDto {

	private UUID id;
	private String nome;
	private String cpf;
	private String sexo;
	private Date dataNascimento;
	private Date dataHoraCadastro;
}
