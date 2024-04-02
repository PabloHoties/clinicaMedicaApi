package br.com.cotiinformatica;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import br.com.cotiinformatica.domain.dtos.CriarPacienteRequestDto;
import br.com.cotiinformatica.domain.dtos.CriarPacienteResponseDto;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PacientesTest {

	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	static String cpf;
	
	@Test
	@Order(1)
	public void criarPacienteComSucessoTest() throws Exception {
		
		Faker faker = new Faker(new Locale("pt-BR"));
		
		CriarPacienteRequestDto dto = new CriarPacienteRequestDto();
		dto.setNome(faker.name().fullName());
		dto.setCpf(faker.number().digits(11));
		dto.setSexo(faker.regexify("[a-zA-z]{5,10}"));
		dto.setDataNascimento(faker.date().birthday());
		
		MvcResult result = mockMvc.perform
				(post("/api/pacientes/criar")
				.contentType("application/json")
				.content(objectMapper
							.writeValueAsString(dto)))
				.andExpectAll(status().isCreated())
				.andReturn();
		
		String content = result.getResponse()
				.getContentAsString(StandardCharsets.UTF_8);
		CriarPacienteResponseDto response = objectMapper.readValue(content, CriarPacienteResponseDto.class);
		
		assertNotNull(response.getId());
		assertEquals(response.getNome(), dto.getNome());
		assertEquals(response.getCpf(), dto.getCpf());
		assertEquals(response.getSexo(), dto.getSexo());
		assertEquals(response.getDataNascimento(), dto.getDataNascimento());
		
		cpf = dto.getCpf();
	}
	
	@Test
	@Order(2)
	public void criarPacienteComCpfInvalidoTest() throws Exception {
		
		Faker faker = new Faker(new Locale("pt-Br"));
		
		CriarPacienteRequestDto dto = new CriarPacienteRequestDto();
		dto.setNome(faker.name().fullName());
		dto.setCpf(cpf);
		dto.setSexo(faker.regexify("[a-zA-z]{5,10}"));
		dto.setDataNascimento(faker.date().birthday());
		
		MvcResult result = mockMvc.perform
				(post("/api/pacientes/criar")
				.contentType("application/json")
				.content(objectMapper
							.writeValueAsString(dto)))
				.andExpectAll(status().isBadRequest())
				.andReturn();
		
		String content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		assertTrue(content.contains("O CPF informado já está cadastrado."));
	}
	
	@Test
	@Order(3)
	public void criarPacienteComDadosInvalidosTest() throws Exception {
		
		CriarPacienteRequestDto dto = new CriarPacienteRequestDto();
		dto.setNome("");
		dto.setCpf("");
		dto.setSexo("");
		dto.setDataNascimento(null);
		
		MvcResult result = mockMvc.perform
				(post("/api/pacientes/criar")
				.contentType("application/json")
				.content(objectMapper
						.writeValueAsString(dto)))
				.andExpectAll(status().isBadRequest())
				.andReturn();
		
		String content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		assertTrue(content.contains("cpf: Por favor, informe o CPF contendo apenas os 11 números."));
		assertTrue(content.contains("cpf: Por favor, informe o CPF do paciente."));
		assertTrue(content.contains("dataNascimento: Por favor, informe uma data."));
		assertTrue(content.contains("nome: Por favor, informe o nome do paciente."));
		assertTrue(content.contains("nome: Por favor, informe um nome de 8 a 150 caracteres."));
		assertTrue(content.contains("sexo: Por favor, informe o sexo do paciente."));
		assertTrue(content.contains("sexo: Por favor, informe um sexo de 5 a 10 caracteres."));
	}
	
	@Test
	public void consultarPacientesTest() {
		fail("Teste não implementado.");
	}
	
	@Test
	public void obterPacienteTest() {
		fail("Teste não implementado.");
	}
}
