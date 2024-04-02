package br.com.cotiinformatica.domain.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CriarMedicoRequestDto {

	@Size(min = 8, max = 150, message = "Por favor, informe um nome de 8 a 150 caracteres.")
	@NotEmpty(message = "Por favor, informe o nome do Médico.")
	private String nome;
	
	@Pattern(regexp = "^[0-9]{7}$", message = "Por favor, informe um CRM contendo apenas 7 dígitos.")
	@NotEmpty(message = "Por favor, informe o CRM do médico.")
	private String crm;
	
	@Size(min = 7, max = 25, message = "Por favor, informe uma especialização de 7 a 25 caracteres.")
	@NotEmpty(message = "Por favor, informe a especialização do médico.")
	private String especializacao;
}
