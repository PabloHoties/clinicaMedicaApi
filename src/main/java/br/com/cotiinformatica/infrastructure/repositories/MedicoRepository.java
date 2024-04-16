package br.com.cotiinformatica.infrastructure.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.cotiinformatica.domain.entities.Medico;

public interface MedicoRepository extends JpaRepository<Medico, UUID> {

	@Query("select me from Medico me where me.crm = :pCrm")
	Medico findByCrm(@Param("pCrm") String crm);
	
	@Query("select me from Medico me where me.crm = :pCrm and me.senha = :pSenha")
	Medico findByCrmAndSenha(@Param("pCrm") String crm, @Param("pSenha") String senha);
}
