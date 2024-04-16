package br.com.cotiinformatica.domain.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AutenticarPacienteResquestDto {

	@Pattern(regexp = "^[0-9]{11}$", message = "Por favor, informe o CPF contendo apenas os 11 números.")
	@NotEmpty(message = "Por favor, informe o CPF do paciente.")
	private String cpf;
	
	@Size(min = 8, message = "Por favor, informe uma senha com pelo menos 8 caracteres.")
	@NotEmpty(message = "Por favor, informe a senha do paciente.")
	private String senha;
}
