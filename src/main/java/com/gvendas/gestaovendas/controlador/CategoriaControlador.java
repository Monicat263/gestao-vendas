package com.gvendas.gestaovendas.controlador;

import com.gvendas.gestaovendas.dto.categoria.CategoriaRequestDTO;
import com.gvendas.gestaovendas.dto.categoria.Clietne;
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
    public List<Clietne> listarTodas(){
        return categoriaServico.listarTodas().stream()
                .map(categoria -> Clietne.converterParaDTO(categoria))
                .collect(Collectors.toList());
    }

   @GetMapping("/{codigo}")
    public ResponseEntity<Clietne> buscarPorCodigo(@PathVariable Long codigo){
       Optional<Categoria> categoria = categoriaServico.buscaPorCodigo(codigo);
       return categoria.isPresent() ? ResponseEntity.ok(Clietne.converterParaDTO(categoria.get()))
               : ResponseEntity.notFound().build();}
    @PostMapping
    public ResponseEntity<Clietne>salvarCategoria(@Valid @RequestBody CategoriaRequestDTO categoriaDto){
        Categoria categoriaSalva = categoriaServico.salvarCategoria(categoriaDto.converterParaEntidade());
        return ResponseEntity.status(HttpStatus.CREATED).body(Clietne.converterParaDTO(categoriaSalva));
    }
    @PutMapping("/{codigo}")
    public ResponseEntity<Clietne>atualizarCategoria(@Valid @PathVariable Long codigo,
                                                     @RequestBody CategoriaRequestDTO categoriaDTO){
        Categoria categoriaAtualizada = categoriaServico.atualizarCategoria(codigo,
                categoriaDTO.converterParaEntidade(codigo));
        return ResponseEntity.ok(Clietne.converterParaDTO(categoriaAtualizada));

    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long codigo){
        categoriaServico.deletar(codigo);

    }

    public void setCategoriaServico(CategoriaServico categoriaServico) {
    }
}
