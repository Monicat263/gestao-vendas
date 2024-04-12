package com.gvendas.gestaovendas.servico;


import com.gvendas.gestaovendas.entidades.Produto;
import com.gvendas.gestaovendas.excecao.RegraNegocioException;
import com.gvendas.gestaovendas.repositorio.ProdutoRepositorio;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoServico {

    @Autowired
    private ProdutoRepositorio produtoRepositorio;

    // cria o metódo listar todos os produtos

    // injetar Categoria Service pq já tenho o metodo pronto agora é só usar aqui
    @Autowired
    private CategoriaServico categoriaServico;

    public List<Produto> listarProdutos(Long codigoCategoria){
        return produtoRepositorio.findByCategoriaCodigo(codigoCategoria);
    }

    public Optional<Produto> buscarPorCodigoProduto(Long codigo,Long codigoCategoria){
        return produtoRepositorio.buscarPorCodigoProduto(codigo,codigoCategoria);

    }

    public Produto salvarProduto(Long codigoCategoria, Produto produto){
        validarCategoriaDoProdutoExiste(produto.getCategoria().getCodigo());
        validarProdutoDuplicado(produto);
        return produtoRepositorio.save(produto);

    }

    public Produto atualizarProduto(Long codigoCategoria, Long codigoProduto, Produto produto) {
        Produto produtoSalvar = validarProdutoExiste(codigoProduto, codigoCategoria);
        validarCategoriaDoProdutoExiste(codigoCategoria);
        validarProdutoDuplicado(produto);
        BeanUtils.copyProperties(produto, produtoSalvar, "codigo");
        return produtoRepositorio.save(produtoSalvar);
    }
    private Produto validarProdutoExiste (Long codigoProduto, Long codigoCategoria){
            Optional<Produto> produto = buscarPorCodigoProduto(codigoProduto, codigoCategoria);
            if (produto.isEmpty()) {
                throw new EmptyResultDataAccessException(1);
            }
            return produto.get();
    }

    private void validarProdutoDuplicado (Produto produto){
        Optional<Produto> produtoPorDescricao = produtoRepositorio.findByCategoriaCodigoAndDescricao(produto.getCategoria().getCodigo(), produto.getDescricao());
        if (produtoPorDescricao.isPresent() && produtoPorDescricao.get().getCodigo()!= produto.getCodigo()) {
                throw new RegraNegocioException(String.format("O produto %s já esta cadastrado", produto.getDescricao()));
            }
    }

    public void deletar(Long codigoCategoria, Long codigoProduto){
        Produto produto = validarProdutoExiste(codigoProduto,codigoCategoria);
        produtoRepositorio.delete(produto);
    }
    private void validarCategoriaDoProdutoExiste (Long codigoCategoria){
            if (codigoCategoria == null) {
                throw new RegraNegocioException("A categoria não pode ser nula");
            }
            if (categoriaServico.buscaPorCodigo(codigoCategoria).isEmpty()) {
                throw new RegraNegocioException(String.format("A categoria %s informada não existe no cadastro"
                        , codigoCategoria));
            }
    }


}

