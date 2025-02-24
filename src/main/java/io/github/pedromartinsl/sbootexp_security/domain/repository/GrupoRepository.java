package io.github.pedromartinsl.sbootexp_security.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.pedromartinsl.sbootexp_security.domain.entity.Grupo;

public interface GrupoRepository extends JpaRepository<Grupo, String> {

}
