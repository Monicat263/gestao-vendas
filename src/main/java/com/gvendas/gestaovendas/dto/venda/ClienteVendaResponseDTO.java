package com.gvendas.gestaovendas.dto.venda;

import java.util.List;

public class ClienteVendaResponseDTO {

    private String nome;
    private List<ClienteVendaResponseDTO> vendaResponseDTOS;

    public ClienteVendaResponseDTO(String nome, List<ClienteVendaResponseDTO> vendaResponseDTOS) {
        this.nome = nome;
        this.vendaResponseDTOS = vendaResponseDTOS;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<ClienteVendaResponseDTO> getVendaResponseDTOS() {
        return vendaResponseDTOS;
    }

    public void setVendaResponseDTOS(List<ClienteVendaResponseDTO> vendaResponseDTOS) {
        this.vendaResponseDTOS = vendaResponseDTOS;
    }
}
