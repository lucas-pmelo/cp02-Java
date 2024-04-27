package br.com.fiap.checkpoint02.model;

import br.com.fiap.checkpoint02.dto.curso.AtualizacaoCursoDto;
import br.com.fiap.checkpoint02.dto.curso.CadastroCursoDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "CP2_curso")
@EntityListeners(AuditingEntityListener.class)
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "curso")
    @SequenceGenerator(name = "curso", sequenceName = "cp2_curso_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "descricao", nullable = false, length = 100)
    private String descricao;

    @OneToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @OneToMany(mappedBy = "curso")
    private List<Estudante> estudantes;

    @ManyToMany(mappedBy = "cursos")
    private List<SalaDeAula> salasDeAula;

    public Curso(CadastroCursoDto dto) {
        this.nome = dto.nome();
        this.descricao = dto.descricao();
    }

    public void atualizarDados(AtualizacaoCursoDto dto) {
        if (dto.nome() != null) this.nome = dto.nome();
        if (dto.descricao() != null) this.descricao = dto.descricao();
        if (dto.professorId() != null) this.professor.setId(dto.professorId());
    }
}