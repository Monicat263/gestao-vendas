package com.gvendas.gestaovendas.dto.produto;

import com.gvendas.gestaovendas.entidades.Categoria;
import com.gvendas.gestaovendas.entidades.Produto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

public class ProdutoRequestDTO {

    @NotBlank(message = "Descrição")
    @Length(min = 3, max = 100,message = "Descrição")
    private String descricao;

    @NotNull(message = "Quantidade")
    private Integer quantidade;

    @NotNull(message = "Preço custo")
    private BigDecimal precoCusto;

    @NotNull(message = "Preço venda")
    private BigDecimal precoVenda;

    @Length(max = 500,message = "Observação")
    private String observacao;

    public Produto converterParaEntidade(Long codigoCategoria){
        return new Produto(descricao,quantidade,precoCusto,precoVenda,observacao, new Categoria(codigoCategoria));
    }
        // sobrecarga de métodos, ou seja, método com mesmo nome, mas com parametros diferentes
    public Produto converterParaEntidade(Long codigoCategoria, Long codigoProduto){
        return new Produto(codigoProduto,descricao,quantidade,precoCusto,precoVenda,observacao, new Categoria(codigoCategoria));
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
}
