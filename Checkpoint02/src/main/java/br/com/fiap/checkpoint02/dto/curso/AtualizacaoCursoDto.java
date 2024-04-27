package br.com.fiap.checkpoint02.dto.curso;

public record AtualizacaoCursoDto(
        String nome,
        String descricao,
        Long professorId
) {
}
