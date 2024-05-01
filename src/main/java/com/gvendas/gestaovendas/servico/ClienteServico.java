package com.gvendas.gestaovendas.servico;

import com.gvendas.gestaovendas.entidades.Cliente;
import com.gvendas.gestaovendas.excecao.RegraNegocioException;
import com.gvendas.gestaovendas.repositorio.ClienteRepositorio;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

    public Cliente salvarCliente(Cliente cliente){
        validarClienteDuplicado(cliente);
        return clienteRepositorio.save(cliente);
    }

    public void deletarCliente(Long codigo){
        clienteRepositorio.deleteById(codigo);
    }

    private void validarClienteDuplicado (Cliente cliente){
       Cliente clientePorNome =  clienteRepositorio.findByNome(cliente.getNome());
       if(clientePorNome!= null && clientePorNome.getCodigo() != cliente.getCodigo()){

           throw new RegraNegocioException(String.format("O cliente %s já esta cadastrado",
                   cliente.getNome().toUpperCase()));
       }
    }

    public Cliente atualizarCliente(Long codigo, Cliente cliente ){
        Cliente clienteAtualizar = validarClienteExiste(codigo);
        validarClienteDuplicado(cliente);
        BeanUtils.copyProperties(cliente, clienteAtualizar,"codigo");
        return clienteRepositorio.save(clienteAtualizar);

    }
    private Cliente validarClienteExiste(Long codigo){
        Optional<Cliente>cliente = bucarClientPorCodigo(codigo);
        if(cliente.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }
        else {
            return cliente.get();
        }
    }

}
