package com.gvendas.gestaovendas.servico;

import com.gvendas.gestaovendas.dto.venda.ClienteVendaResponseDTO;
import com.gvendas.gestaovendas.dto.venda.ItemVendaResponseDTO;
import com.gvendas.gestaovendas.dto.venda.VendaResponseDTO;
import com.gvendas.gestaovendas.entidades.Cliente;
import com.gvendas.gestaovendas.entidades.ItemVenda;
import com.gvendas.gestaovendas.entidades.Venda;
import com.gvendas.gestaovendas.repositorio.ItemVendaRepositorio;
import com.gvendas.gestaovendas.repositorio.VendaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendaServico {

    private VendaRepositorio vendaRepositorio;
    private ClienteServico clienteServico;
    private ItemVendaRepositorio itemVendaRepositorio;

    // nesse caso como temos mais de um parametro que utiliza o @Autowired então é melhor passar no contrutor
    @Autowired
    public VendaServico(VendaRepositorio vendaRepositorio, ClienteServico clienteServico,ItemVendaRepositorio itemVendaRepositorio) {
        this.vendaRepositorio = vendaRepositorio;
        this.clienteServico = clienteServico;
        this.itemVendaRepositorio = itemVendaRepositorio;
    }
// Talves eu tenha que voltar aqui vendasResponseDTOList
    public ClienteVendaResponseDTO listaVendaPorCliente(Long codigoCliente){
        Cliente cliente = validarClienteVendaExiste(codigoCliente);
        List<VendaResponseDTO> vendaResponseDTOList = vendaRepositorio.findByClienteCodigo(codigoCliente).stream()
                .map(this::criandoVendaResponseDTO).collect(Collectors.toList());
        return new ClienteVendaResponseDTO(cliente.getNome(), vendaResponseDTOList );

    }

    private Cliente validarClienteVendaExiste(Long codigoCliente) {
        Optional<Cliente> cliente = clienteServico.bucarClientPorCodigo(codigoCliente);
        if (cliente.isEmpty()){
            throw new RuntimeException(String.format("O cliente %s informado não existe no cadastro.", codigoCliente));
        }
        return cliente.get();

    }

    private VendaResponseDTO criandoVendaResponseDTO(Venda venda){
        List<ItemVendaResponseDTO> itensVendaRespondeDTO = itemVendaRepositorio.findByVendaCodigo(venda.getCodigo()).stream()
                .map(this::criandoItensVendasResponseDTO).collect(Collectors.toList());
        return new VendaResponseDTO(venda.getCodigo(),venda.getData(), itensVendaRespondeDTO);

    }

    private ItemVendaResponseDTO criandoItensVendasResponseDTO( ItemVenda itemVenda){
        return new ItemVendaResponseDTO(itemVenda.getCodigo(), itemVenda.getQuantidade(), itemVenda.getPrecoVendido(),
                itemVenda.getProduto().getCodigo(), itemVenda.getProduto().getDescricao());
    }
}
