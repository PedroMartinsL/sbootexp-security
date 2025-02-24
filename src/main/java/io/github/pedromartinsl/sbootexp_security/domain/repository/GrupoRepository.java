package io.github.pedromartinsl.sbootexp_security.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.pedromartinsl.sbootexp_security.domain.entity.Grupo;

public interface GrupoRepository extends JpaRepository<Grupo, String> {

    Optional<Grupo> findByNome(String nome);
}
