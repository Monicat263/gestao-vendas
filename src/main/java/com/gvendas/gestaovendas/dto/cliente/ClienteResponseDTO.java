package com.gvendas.gestaovendas.dto.cliente;

import com.gvendas.gestaovendas.entidades.Cliente;

public class ClienteResponseDTO {


    private Long codigo;

    private String nome;

    private String telefone;

    private Boolean ativo;

    private String logradouro;


    private Integer numero;

    private String complemento;


    private String bairro;


    private String cep;


    private String cidade;


    private String estado;

    public ClienteResponseDTO(Long codigo, String nome, String telefone, Boolean ativo, String logradouro,
                              Integer numero,String complemento, String bairro, String cep,
                              String cidade, String estado) {
        this.codigo = codigo;
        this.nome = nome;
        this.telefone = telefone;
        this.ativo = ativo;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
    }

    public static ClienteResponseDTO converterParaClienteDTO(Cliente cliente){
            return new ClienteResponseDTO(cliente.getCodigo(), cliente.getNome(), cliente.getTelefone(),
                    cliente.getAtivo(), cliente.getLogradouro(),cliente.getNumero(),cliente.getComplemento(),
                    cliente.getBairro(),cliente.getCep(),cliente.getCidade(),cliente.getEstado());

    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
