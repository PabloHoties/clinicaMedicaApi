package br.com.cotiinformatica.domain.dtos;

import java.util.Date;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CriarPacienteRequestDto {

	@Size(min = 8, max = 150, message = "Por favor, informe um nome de 8 a 150 caracteres.")
	@NotEmpty(message = "Por favor, informe o nome do paciente.")
	private String nome;
	
	@Pattern(regexp = "^[0-9]{11}$", message = "Por favor, informe o CPF contendo apenas os 11 números.")
	@NotEmpty(message = "Por favor, informe o CPF do paciente.")
	private String cpf;
	
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
			message = "Por favor, informe uma senha com letras, números e símbolos de no mínimo 8 caracteres.")
	@NotEmpty(message = "Por favor, informe a senha do paciente.")
	private String senha;

	@Size(min = 5, max = 10, message = "Por favor, informe um sexo de 5 a 10 caracteres.")
	@NotEmpty(message = "Por favor, informe o sexo do paciente.")
	private String sexo;

	@NotNull(message = "Por favor, informe uma data.")
	private Date dataNascimento;
}
