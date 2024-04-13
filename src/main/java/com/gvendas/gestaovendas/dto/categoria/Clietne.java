package com.gvendas.gestaovendas.dto.categoria;

import com.gvendas.gestaovendas.entidades.Categoria;

public class Clietne {

    private Long codigo;
    private String nome;

    // construtor


    public Clietne(Long codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    // objeto método

    public static Clietne converterParaDTO(Categoria categoria){
        return new Clietne(categoria.getCodigo(), categoria.getNome());
    }


    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
