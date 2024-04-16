package br.com.cotiinformatica.domain.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AutenticarMedicoRequestDto {

	@Pattern(regexp = "^\\d{7}$", message = "Por favor, informe um CRM de 7 digítos válido.")
	@NotEmpty(message = "Por favor, informe o CRM do médico.")
	private String crm;
	
	@Size(min = 8, message = "Por favor, informe uma senha com pelo menos 8 caracteres.")
	@NotEmpty(message = "Por favor, informe a senha do médico.")
	private String senha;
}
