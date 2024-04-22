package com.gvendas.gestaovendas.controlador;

import com.gvendas.gestaovendas.dto.cliente.ClienteRequestDTO;
import com.gvendas.gestaovendas.dto.cliente.ClienteResponseDTO;
import com.gvendas.gestaovendas.entidades.Cliente;
import com.gvendas.gestaovendas.servico.ClienteServico;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cliente")
public class ClienteControlador {

    @Autowired
    private ClienteServico clienteServico;

    @GetMapping
    public List<ClienteResponseDTO> listarTodosClientes(){
        return clienteServico.listarTodosClientes().stream().map(cliente -> ClienteResponseDTO.converterParaClienteDTO(cliente))
                .collect(Collectors.toList());

    }

    @GetMapping("/{codigo}")
    public ResponseEntity<ClienteResponseDTO> bucarClientPorCodigo(@PathVariable Long codigo){
        Optional<Cliente> cliente = clienteServico.bucarClientPorCodigo(codigo);
        return cliente.isPresent() ? ResponseEntity.ok(ClienteResponseDTO.converterParaClienteDTO(cliente.get()))
                : ResponseEntity.notFound().build();
        }
    @PostMapping
    public ResponseEntity<ClienteResponseDTO> salvarCliente(@Valid @RequestBody ClienteRequestDTO clienteRequestDTO){
        Cliente clienteSalvo = clienteServico.salvarCliente(clienteRequestDTO.converterParaEntidade());
        return ResponseEntity.status(HttpStatus.CREATED).body(ClienteResponseDTO.converterParaClienteDTO(clienteSalvo));
        }

}