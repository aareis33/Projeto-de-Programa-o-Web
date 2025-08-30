package com.exemplo.turmas.dto;

import jakarta.validation.constraints.NotBlank;

public class TurmaDto {
  private Long id;

  @NotBlank(message = "nome é obrigatório")
  private String nome;

  @NotBlank(message = "periodo é obrigatório")
  private String periodo;

  @NotBlank(message = "curso é obrigatório")
  private String curso;

  public TurmaDto() {}

  public TurmaDto(Long id, String nome, String periodo, String curso) {
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
