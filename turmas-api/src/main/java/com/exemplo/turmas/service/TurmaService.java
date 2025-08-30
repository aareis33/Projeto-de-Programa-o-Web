package com.exemplo.turmas.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exemplo.turmas.dto.TurmaDto;
import com.exemplo.turmas.entity.Turma;
import com.exemplo.turmas.exception.BadRequestException;
import com.exemplo.turmas.exception.NoContentException;
import com.exemplo.turmas.exception.NotFoundException;
import com.exemplo.turmas.repository.TurmaRepository;

@Service
public class TurmaService {

  private final TurmaRepository repo;

  public TurmaService(TurmaRepository repo) {
    this.repo = repo;
  }

  private TurmaDto toDto(Turma t) {
    return new TurmaDto(t.getId(), t.getNome(), t.getPeriodo(), t.getCurso());
  }

  private Turma toEntity(TurmaDto dto) {
    return new Turma(dto.getId(), dto.getNome(), dto.getPeriodo(), dto.getCurso());
  }

  @Transactional
  public TurmaDto criar(TurmaDto dto) {
    if (dto.getNome() == null || dto.getNome().isBlank())
      throw new BadRequestException("nome é obrigatório");
    if (dto.getPeriodo() == null || dto.getPeriodo().isBlank())
      throw new BadRequestException("periodo é obrigatório");
    if (dto.getCurso() == null || dto.getCurso().isBlank())
      throw new BadRequestException("curso é obrigatório");

    Turma salvo = repo.save(toEntity(dto));
    return toDto(salvo);
  }

  @Transactional(readOnly = true)
  public TurmaDto buscarPorId(Long id) {
    Turma t = repo.findById(id).orElseThrow(() -> new NotFoundException("Turma não encontrada para id=" + id));
    return toDto(t);
  }

  @Transactional(readOnly = true)
  public List<TurmaDto> buscarTodos() {
    List<Turma> all = repo.findAll();
    if (all.isEmpty()) throw new NoContentException("Nenhuma turma cadastrada");
    return all.stream().map(this::toDto).collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public TurmaDto buscarPorNome(String nome) {
    Turma t = repo.findByNomeIgnoreCase(nome).orElseThrow(() -> new NotFoundException("Nenhuma turma com nome=" + nome));
    return toDto(t);
  }

  @Transactional(readOnly = true)
  public List<TurmaDto> buscarPorPeriodo(String periodo) {
    List<Turma> list = repo.findAllByPeriodoIgnoreCase(periodo);
    if (list.isEmpty()) throw new NotFoundException("Nenhuma turma com periodo=" + periodo);
    return list.stream().map(this::toDto).collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public List<TurmaDto> buscarPorCurso(String curso) {
    List<Turma> list = repo.findAllByCursoIgnoreCase(curso);
    if (list.isEmpty()) throw new NotFoundException("Nenhuma turma com curso=" + curso);
    return list.stream().map(this::toDto).collect(Collectors.toList());
  }

  @Transactional
  public void deletarPorId(Long id) {
    if (!repo.existsById(id)) throw new NotFoundException("Turma não encontrada para id=" + id);
    repo.deleteById(id);
  }
}
