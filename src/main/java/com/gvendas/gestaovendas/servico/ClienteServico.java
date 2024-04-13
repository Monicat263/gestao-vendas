package com.gvendas.gestaovendas.servico;

import com.gvendas.gestaovendas.entidades.Cliente;
import com.gvendas.gestaovendas.repositorio.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServico {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    public List<Cliente> listarTodosClientes(){
        return clienteRepositorio.findAll();
    }

    public Optional<Cliente> bucarClientPorCodigo(Long codigo){
        return clienteRepositorio.findById(codigo);
    }

}
