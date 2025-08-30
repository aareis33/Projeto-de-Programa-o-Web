package com.exemplo.turmas.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "turmas")
public class Turma {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Column(nullable = false, length = 100)
  private String nome;

  @NotBlank
  @Column(nullable = false, length = 50)
  private String periodo; // Manhã / Tarde / Noite

  @NotBlank
  @Column(nullable = false, length = 100)
  private String curso;

  public Turma() {}

  public Turma(Long id, String nome, String periodo, String curso) {
    this.id = id;
    this.nome = nome;
    this.periodo = periodo;
    this.curso = curso;
  }

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public String getNome() { return nome; }
  public void setNome(String nome) { this.nome = nome; }
  public String getPeriodo() { return periodo; }
  public void setPeriodo(String periodo) { this.periodo = periodo; }
  public String getCurso() { return curso; }
  public void setCurso(String curso) { this.curso = curso; }
}
