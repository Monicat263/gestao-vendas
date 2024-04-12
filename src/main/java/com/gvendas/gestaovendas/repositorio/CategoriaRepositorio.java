package com.gvendas.gestaovendas.repositorio;

import com.gvendas.gestaovendas.entidades.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepositorio extends JpaRepository<Categoria, Long> {

    // recuperar dados do banco de dados com base em  um critério de pesquisa
    Categoria findByNome(String nome);
}
