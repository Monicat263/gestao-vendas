package com.gvendas.gestaovendas.controlador;

import com.gvendas.gestaovendas.dto.categoria.CategoriaRequestDTO;
import com.gvendas.gestaovendas.dto.categoria.CategoriaResponseDTO;
import com.gvendas.gestaovendas.entidades.Categoria;
import com.gvendas.gestaovendas.servico.CategoriaServico;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/categoria")
public class CategoriaControlador {
    @Autowired
    private CategoriaServico categoriaServico;

    @GetMapping
    public List<CategoriaResponseDTO> listarTodas(){
        return categoriaServico.listarTodas().stream()
                .map(categoria -> CategoriaResponseDTO.converterParaCategoriaDTO(categoria))
                .collect(Collectors.toList());
    }

   @GetMapping("/{codigo}")
    public ResponseEntity<CategoriaResponseDTO> buscarPorCodigo(@PathVariable Long codigo){
       Optional<Categoria> categoria = categoriaServico.buscaPorCodigo(codigo);
       return categoria.isPresent() ? ResponseEntity.ok(CategoriaResponseDTO.converterParaCategoriaDTO(categoria.get()))
               : ResponseEntity.notFound().build();}
    @PostMapping
    public ResponseEntity<CategoriaResponseDTO>salvarCategoria(@Valid @RequestBody CategoriaRequestDTO categoriaDto){
        Categoria categoriaSalva = categoriaServico.salvarCategoria(categoriaDto.converterParaEntidade());
        return ResponseEntity.status(HttpStatus.CREATED).body(CategoriaResponseDTO.converterParaCategoriaDTO(categoriaSalva));
    }
    @PutMapping("/{codigo}")
    public ResponseEntity<CategoriaResponseDTO>atualizarCategoria(@Valid @PathVariable Long codigo,
                                                                @RequestBody CategoriaRequestDTO categoriaDTO){
        Categoria categoriaAtualizada = categoriaServico.atualizarCategoria(codigo,
                categoriaDTO.converterParaEntidade(codigo));
        return ResponseEntity.ok(CategoriaResponseDTO.converterParaCategoriaDTO(categoriaAtualizada));

    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long codigo){
        categoriaServico.deletar(codigo);

    }

    public void setCategoriaServico(CategoriaServico categoriaServico) {
    }
}
