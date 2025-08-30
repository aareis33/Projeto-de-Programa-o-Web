package com.exemplo.turmas.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.exemplo.turmas.dto.TurmaDto;
import com.exemplo.turmas.exception.NoContentException;
import com.exemplo.turmas.service.TurmaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/turmas")
@Validated
public class TurmaController {

  private final TurmaService service;

  public TurmaController(TurmaService service) {
    this.service = service;
  }

  // POST /turmas -> 201
  @PostMapping
  public ResponseEntity<TurmaDto> criar(@Valid @RequestBody TurmaDto dto) {
    TurmaDto salvo = service.criar(dto);
    return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
  }

  // GET /turmas/{id} -> 200
  @GetMapping("/{id}")
  public ResponseEntity<TurmaDto> buscarPorId(@PathVariable("id") Long id) {
    return ResponseEntity.ok(service.buscarPorId(id));
  }

  // GET /turmas -> 200 ou 204
  @GetMapping
  public ResponseEntity<List<TurmaDto>> buscarTodos() {
    try {
      List<TurmaDto> list = service.buscarTodos();
      return ResponseEntity.ok(list);
    } catch (NoContentException e) {
      return ResponseEntity.noContent().build();
    }
  }

  // GET /turmas/nome/{nome} -> 200 ou 404
  @GetMapping("/nome/{nome}")
  public ResponseEntity<TurmaDto> buscarPorNome(@PathVariable("nome") String nome) {
    return ResponseEntity.ok(service.buscarPorNome(nome));
  }

  // GET /turmas/periodo/{periodo} -> 200 ou 404
  @GetMapping("/periodo/{periodo}")
  public ResponseEntity<List<TurmaDto>> buscarPorPeriodo(@PathVariable("periodo") String periodo) {
    return ResponseEntity.ok(service.buscarPorPeriodo(periodo));
  }

  // GET /turmas/curso/{curso} -> 200 ou 404
  @GetMapping("/curso/{curso}")
  public ResponseEntity<List<TurmaDto>> buscarPorCurso(@PathVariable("curso") String curso) {
    return ResponseEntity.ok(service.buscarPorCurso(curso));
  }

  // DELETE /turmas/{id} -> 204 ou 404
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
    service.deletarPorId(id);
    return ResponseEntity.noContent().build();
  }
}
