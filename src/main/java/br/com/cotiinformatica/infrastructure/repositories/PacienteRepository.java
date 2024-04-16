package br.com.cotiinformatica.infrastructure.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.cotiinformatica.domain.entities.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, UUID> {

	@Query("select pa from Paciente pa where pa.cpf = :pCpf")
	Paciente findByCpf(@Param("pCpf") String cpf);
	
	@Query("select pa from Paciente pa where pa.cpf = :pCpf and pa.senha = :pSenha")
	Paciente findByCpfAndSenha(@Param("pCpf") String cpf, @Param("pSenha") String senha);
}
