package br.com.cotiinformatica.infrastructure.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cotiinformatica.domain.entities.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, UUID> {

}
