package com.gvendas.gestaovendas.dto.produto;

import com.gvendas.gestaovendas.dto.categoria.Clietne;
import com.gvendas.gestaovendas.entidades.Produto;

import java.math.BigDecimal;

public class ProdutoResponseDTO {

    private Long codigo;

    private String descricao;

    private Integer quantidade;

    private BigDecimal precoCusto;

    private BigDecimal precoVenda;

    private String observacao;

    private Clietne categoria;

    public ProdutoResponseDTO(Long codigo, String descricao, Integer quantidade, BigDecimal precoCusto,
                              BigDecimal precoVenda, String observacao, Clietne categoria) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
        this.observacao = observacao;
        this.categoria = categoria;
    }

    // método estática

    public static ProdutoResponseDTO converterParaProdutoDTO(Produto produto){
        return new ProdutoResponseDTO(produto.getCodigo(),produto.getDescricao(),produto.getQuantidade(),
                produto.getPrecoCusto(),produto.getPrecoVenda(),produto.getObservacao(),
                Clietne.converterParaDTO(produto.getCategoria()));
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(BigDecimal precoCusto) {
        this.precoCusto = precoCusto;
    }

    public BigDecimal getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(BigDecimal precoVenda) {
        this.precoVenda = precoVenda;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Clietne getCategoria() {
        return categoria;
    }

    public void setCategoria(Clietne categoria) {
        this.categoria = categoria;
    }
}
