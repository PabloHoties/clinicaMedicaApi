package br.com.cotiinformatica.domain.dtos;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$", 
			message = "Por favor, informe uma senha com letras, números e símbolos de no mínimo 8 caracteres.")
	@NotEmpty(message = "Por favor, informe a senha do médico.")
	private String senha;

	@Size(min = 7, max = 25, message = "Por favor, informe uma especialização de 7 a 25 caracteres.")
	@NotEmpty(message = "Por favor, informe a especialização do médico.")
	private String especializacao;
	
	@JsonIgnore
	private byte[] fotoByte;
	
	private MultipartFile foto;
}
