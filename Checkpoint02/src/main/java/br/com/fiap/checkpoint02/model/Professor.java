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
@Table(name = "CP2_professor")
@EntityListeners(AuditingEntityListener.class)
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "professor")
    @SequenceGenerator(name = "professor", sequenceName = "cp2_professor_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "telefone", nullable = false, length = 20)
    private String telefone;

    @Column(name = "especializacao", nullable = false, length = 100)
    private String especializacao;

    @OneToOne(mappedBy = "professor")
    private Curso curso;
}