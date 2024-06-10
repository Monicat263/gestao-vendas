package com.gvendas.gestaovendas.dto.venda;

import java.time.LocalDate;
import java.util.List;

public class VendaResponseDTO {

    private Long codigo;

    private LocalDate data;

    private List<ItemVendaResponseDTO> itemVendaResponseDTOS;

    public VendaResponseDTO(Long codigo, LocalDate data, List<ItemVendaResponseDTO> itemVendaResponseDTOS) {
        this.codigo = codigo;
        this.data = data;
        this.itemVendaResponseDTOS = itemVendaResponseDTOS;
    }


    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public List<ItemVendaResponseDTO> getItemVendaResponseDTOS() {
        return itemVendaResponseDTOS;
    }

    public void setItemVendaResponseDTOS(List<ItemVendaResponseDTO> itemVendaResponseDTOS) {
        this.itemVendaResponseDTOS = itemVendaResponseDTOS;
    }
}
