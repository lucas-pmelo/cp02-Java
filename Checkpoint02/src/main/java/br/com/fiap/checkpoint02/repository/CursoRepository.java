package br.com.fiap.checkpoint02.repository;

import br.com.fiap.checkpoint02.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}