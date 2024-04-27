package br.com.fiap.checkpoint02.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "CP2_estudante")
@EntityListeners(AuditingEntityListener.class)
public class Estudante {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estudante")
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "telefone", nullable = false, length = 20)
    private String telefone;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;
}