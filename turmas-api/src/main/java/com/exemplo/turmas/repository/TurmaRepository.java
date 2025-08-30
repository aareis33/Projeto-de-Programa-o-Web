package com.exemplo.turmas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exemplo.turmas.entity.Turma;

public interface TurmaRepository extends JpaRepository<Turma, Long> {
  Optional<Turma> findByNomeIgnoreCase(String nome);
  List<Turma> findAllByPeriodoIgnoreCase(String periodo);
  List<Turma> findAllByCursoIgnoreCase(String curso);
}
