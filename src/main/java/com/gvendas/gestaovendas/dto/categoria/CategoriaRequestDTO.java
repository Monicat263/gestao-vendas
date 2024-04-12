package com.gvendas.gestaovendas.dto.categoria;

import com.gvendas.gestaovendas.entidades.Categoria;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class CategoriaRequestDTO {

    @NotBlank(message = "Nome")
    @Length(min = 3, max = 50,message = "Nome")
    private String nome;

    // método: retorna um objeto do tipo Categoria nome

    public Categoria converterParaEntidade(){
        return new Categoria(nome);

    }

    // método: retorna um objeto do tipo Categoria nome

    public Categoria converterParaEntidade(Long codigo){
        return new Categoria(codigo , nome);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
