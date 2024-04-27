import br.com.fiap.checkpoint02.dto.curso.AtualizacaoCursoDto;
import br.com.fiap.checkpoint02.dto.curso.CadastroCursoDto;
import br.com.fiap.checkpoint02.dto.curso.DetalhesCursoDto;
import br.com.fiap.checkpoint02.model.Curso;
import br.com.fiap.checkpoint02.repository.CursoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public ResponseEntity<Page<DetalhesCursoDto>> listarCursos(Pageable paginacao) {
        var paginaCursos = cursoRepository.findAll(paginacao).map(DetalhesCursoDto::new);
        return ResponseEntity.ok(paginaCursos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesCursoDto> buscarCursoPorId(@PathVariable Long id) throws EntityNotFoundException {
        var curso = cursoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return ResponseEntity.ok(new DetalhesCursoDto(curso));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesCursoDto> cadastrarCurso(@RequestBody CadastroCursoDto cursoDto, UriComponentsBuilder uri) {
        var curso = new Curso(cursoDto);
        cursoRepository.save(curso);
        var url = uri.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(url).body(new DetalhesCursoDto(curso));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DetalhesCursoDto> atualizarCurso(@PathVariable Long id, @RequestBody AtualizacaoCursoDto cursoDto) throws EntityNotFoundException {
        var curso = cursoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        curso.atualizarDados(cursoDto);
        return ResponseEntity.ok(new DetalhesCursoDto(curso));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> removerCurso(@PathVariable Long id) throws EntityNotFoundException {
        var curso = cursoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        cursoRepository.delete(curso);
        return ResponseEntity.noContent().build();
    }
}
