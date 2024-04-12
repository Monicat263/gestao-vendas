package com.gvendas.gestaovendas.servico;

import com.gvendas.gestaovendas.entidades.Categoria;
import com.gvendas.gestaovendas.excecao.RegraNegocioException;
import com.gvendas.gestaovendas.repositorio.CategoriaRepositorio;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
    public Optional<Categoria> buscaPorCodigo(Long codigo){
        return categoriaRepositorio.findById(codigo);
    }

    public Categoria salvarCategoria(Categoria categoria){
        validarCategoriaDuplicada(categoria);
        return categoriaRepositorio.save(categoria);
    }

    public Categoria atualizarCategoria(Long codigo, Categoria categoria){
        Categoria categoriaSalvar = validarCategoriaExiste(codigo);
        validarCategoriaDuplicada(categoria);
        BeanUtils.copyProperties(categoria,categoriaSalvar,"codigo");
        return categoriaRepositorio.save(categoriaSalvar);

    }

    public void deletar(Long codigo){
        Categoria categoria = categoriaRepositorio.findById(codigo)
                .orElseThrow(() -> new RegraNegocioException("Categoria com código " + codigo + " não existe."));
        categoriaRepositorio.delete(categoria);
    }

    private Categoria validarCategoriaExiste(Long codigo){
        Optional<Categoria>categoria = buscaPorCodigo(codigo);
        if (categoria.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        }
        else{
            return categoria.get();
        }

    }
    private void validarCategoriaDuplicada(Categoria categoria){
       Categoria categoriaEncontrada = categoriaRepositorio.findByNome(categoria.getNome());
        if (categoriaEncontrada != null && !categoriaEncontrada.getCodigo().equals(categoria.getCodigo()))
        {
           throw new RegraNegocioException(
                   String.format("A categoria %s já esta cadastrada",categoria.getNome().toUpperCase()));

       }

    }


}
