package com.gvendas.gestaovendas.dto.categoria;

import com.gvendas.gestaovendas.entidades.Categoria;

public class CategoriaResponseDTO {

    private Long codigo;
    private String nome;

    // construtor


    public CategoriaResponseDTO(Long codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    // objeto método

    public static CategoriaResponseDTO converterParaDTO(Categoria categoria){
        return new CategoriaResponseDTO(categoria.getCodigo(), categoria.getNome());
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
