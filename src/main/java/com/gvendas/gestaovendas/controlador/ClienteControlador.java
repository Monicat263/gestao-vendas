package com.gvendas.gestaovendas.controlador;

import com.gvendas.gestaovendas.entidades.Cliente;
import com.gvendas.gestaovendas.servico.ClienteServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
public class ClienteControlador {

    @Autowired
    private ClienteServico clienteServico;

    @GetMapping
    public List<Cliente> listarTodosClientes(){
        return clienteServico.listarTodosClientes();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Cliente> bucarClientPorCodigo(@PathVariable Long codigo){
        Optional<Cliente> cliente = clienteServico.bucarClientPorCodigo(codigo);
        return cliente.isPresent() ? ResponseEntity.ok(cliente.get())
                : ResponseEntity.notFound().build();}


}
