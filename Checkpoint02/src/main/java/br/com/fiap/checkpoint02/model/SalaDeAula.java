package br.com.fiap.checkpoint02.model;

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
@Table(name = "CP2_sala_de_aula")
@EntityListeners(AuditingEntityListener.class)
public class SalaDeAula {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sala_de_aula")
    private Long id;

    @Column(name = "numero", nullable = false, length = 10)
    private String numero;

    @Column(name = "capacidade", nullable = false)
    private int capacidade;

    @ManyToMany(mappedBy = "salasDeAula")
    private List<Curso> cursos;
}