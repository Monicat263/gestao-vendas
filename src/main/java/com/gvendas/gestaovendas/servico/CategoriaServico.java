package com.gvendas.gestaovendas.servico;

import com.gvendas.gestaovendas.entidades.Categoria;
import com.gvendas.gestaovendas.repositorio.CategoriaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServico {
    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    public List<Categoria> listarTodas(){
        return categoriaRepositorio.findAll();
    }
    public Optional<Categoria> bucsaPorId(Long codigo){
        return categoriaRepositorio.findById(codigo);
    }


}
