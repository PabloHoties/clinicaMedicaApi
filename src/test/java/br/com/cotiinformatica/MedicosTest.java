package br.com.cotiinformatica;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

import br.com.cotiinformatica.domain.dtos.CriarMedicoRequestDto;
import br.com.cotiinformatica.domain.dtos.CriarMedicoResponseDto;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MedicosTest {

	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	static String crm;
	
	@Test
	@Order(1)
	public void criarMedicoComSucessoTest() throws Exception {
		
		Faker faker = new Faker(new Locale("pt-BR"));
		
		CriarMedicoRequestDto dto = new CriarMedicoRequestDto();
		dto.setNome(faker.name().fullName());
		dto.setCrm(faker.number().digits(7));
		dto.setEspecializacao(faker.lorem().characters(7, 25));
		
		MvcResult result = mockMvc.perform
				(post("/api/medicos/criar")
				.contentType("application/json")
				.content(objectMapper
						.writeValueAsString(dto)))
				.andExpectAll(status().isCreated())
				.andReturn();
		
		String content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		CriarMedicoResponseDto response = objectMapper.readValue(content, CriarMedicoResponseDto.class);
		
		assertNotNull(response.getId());
		assertEquals(response.getNome(), dto.getNome());
		assertEquals(response.getCrm(), dto.getCrm());
		assertEquals(response.getEspecializacao(), dto.getEspecializacao());
		
		crm = dto.getCrm();
	}
	
	@Test
	@Order(2)
	public void criarMedicoComCrmInvalidoTest() throws Exception {
		
		Faker faker = new Faker(new Locale("pt-BR"));
		
		CriarMedicoRequestDto dto = new CriarMedicoRequestDto();
		dto.setNome(faker.name().fullName());
		dto.setCrm(crm);
		dto.setEspecializacao(faker.lorem().characters(7, 25));
		
		MvcResult result = mockMvc.perform
				(post("/api/medicos/criar")
				.contentType("application/json")
				.content(objectMapper
						.writeValueAsString(dto)))
				.andExpectAll(status().isBadRequest())
				.andReturn();
		
		String content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		assertTrue(content.contains("O CRM informado já está cadastrado."));
	}
	
	@Test
	@Order(3)
	public void criarMedicoComDadosInvalidosTest() throws Exception {
		
		CriarMedicoRequestDto dto = new CriarMedicoRequestDto();
		dto.setNome("");
		dto.setCrm("");
		dto.setEspecializacao("");
		
		MvcResult result = mockMvc.perform
				(post("/api/medicos/criar")
				.contentType("application/json")
				.content(objectMapper
						.writeValueAsString(dto)))
				.andExpectAll(status().isBadRequest())
				.andReturn();
		
		String content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		assertTrue(content.contains("crm: Por favor, informe o CRM do médico."));
		assertTrue(content.contains("crm: Por favor, informe um CRM contendo apenas 7 dígitos."));
		assertTrue(content.contains("especializacao: Por favor, informe a especialização do médico."));
		assertTrue(content.contains("especializacao: Por favor, informe uma especialização de 7 a 25 caracteres."));
		assertTrue(content.contains("nome: Por favor, informe o nome do Médico."));
		assertTrue(content.contains("nome: Por favor, informe um nome de 8 a 150 caracteres."));
	}
	
	@Test
	public void consultarMedicosTest() {
		fail("Teste não implementado.");
	}
	
	@Test
	public void obterMedicoTest() {
		fail("Teste não implementado.");
	}
}
