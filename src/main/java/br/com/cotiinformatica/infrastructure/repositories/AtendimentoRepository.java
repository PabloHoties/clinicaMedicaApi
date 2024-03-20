package br.com.cotiinformatica.infrastructure.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cotiinformatica.domain.entities.Atendimento;

public interface AtendimentoRepository extends JpaRepository<Atendimento, UUID> {

}
