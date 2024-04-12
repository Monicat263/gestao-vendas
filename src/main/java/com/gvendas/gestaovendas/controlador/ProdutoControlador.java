package com.gvendas.gestaovendas.controlador;

import com.gvendas.gestaovendas.dto.produto.ProdutoRequestDTO;
import com.gvendas.gestaovendas.dto.produto.ProdutoResponseDTO;
import com.gvendas.gestaovendas.entidades.Produto;
import com.gvendas.gestaovendas.servico.ProdutoServico;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categoria/{codigoCategoria}/produto")
public class ProdutoControlador {

    @Autowired
    private ProdutoServico produtoServico;

    @GetMapping
    public List<ProdutoResponseDTO> listarProdutos(@PathVariable Long codigoCategoria) {
        return produtoServico.listarProdutos(codigoCategoria).stream()
                .map(produto -> ProdutoResponseDTO.converterParaProdutoDTO(produto)).collect(Collectors.toList());
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<ProdutoResponseDTO> buscarPorCodigoProduto(@PathVariable Long codigoCategoria,
                                                                     @PathVariable Long codigo) {

        Optional<Produto> produto = produtoServico.buscarPorCodigoProduto(codigo, codigoCategoria);
        // usei operador ternario(?) para representar o if else
        return produto.isPresent() ? ResponseEntity.ok(ProdutoResponseDTO.converterParaProdutoDTO(produto.get()))
                : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> salvarProduto(@PathVariable Long codigoCategoria,
                                                            @Valid @RequestBody ProdutoRequestDTO produtoDTO){


        Produto produtoSalvo = produtoServico.salvarProduto(codigoCategoria,
                produtoDTO.converterParaEntidade(codigoCategoria));
        return ResponseEntity.status(HttpStatus.CREATED).body(ProdutoResponseDTO.converterParaProdutoDTO(produtoSalvo));
    }

    @PutMapping("/{codigoProduto}")
    public ResponseEntity<ProdutoResponseDTO> atualizarProduto(@PathVariable Long codigoCategoria,
                                                    @PathVariable Long codigoProduto,
                                                    @Valid @RequestBody ProdutoRequestDTO produto){
        Produto produtoAtualizado = produtoServico.atualizarProduto(codigoCategoria, codigoProduto,
                produto.converterParaEntidade(codigoCategoria, codigoProduto));
        return ResponseEntity.ok(ProdutoResponseDTO.converterParaProdutoDTO(produtoAtualizado));
    }

    @DeleteMapping("/{codigoProduto}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long codigoCategoria, @PathVariable Long codigoProduto){
        produtoServico.deletar(codigoCategoria, codigoProduto);
    }
}

