package br.com.fiap.checkpoint02.dto.curso;

public record CadastroCursoDto(
        String nome,
        String descricao,
        Long professorId
) {
}
