package br.com.fiap.checkpoint02.dto.curso;

import br.com.fiap.checkpoint02.model.Curso;

import java.util.Optional;

public record DetalhesCursoDto(
        Long id,
        String nome,
        String descricao,
        Long professorId) {

    public DetalhesCursoDto(br.com.fiap.checkpoint02.model.Curso curso) {
        this(
                curso.getId(),
                curso.getNome(),
                curso.getDescricao(),
                curso.getProfessor().getId());
    }
}

