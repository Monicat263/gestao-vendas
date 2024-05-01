package com.gvendas.gestaovendas.dto.cliente;

import com.gvendas.gestaovendas.entidades.Cliente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public class ClienteRequestDTO {

    @NotBlank(message = "Nome")
    @Length(min = 3, max =50, message = "Nome")
    private String nome;
    @NotBlank(message = "Telefone")
    @Pattern(regexp = "\\([\\d]{2}\\)[\\d]{5}[- .][\\d]{4}",message = "Telefone")
    private String telefone;
    @NotNull(message = "Ativo")
    private Boolean ativo;
    @NotBlank(message = "Logradouro")
    @Length(min = 3, max = 30, message = "Logradouro")
    private String logradouro;

    @NotNull(message = "Numero")
    private Integer numero;
    @Length( max = 30, message = "Complemento")
    private String complemento;

    @NotBlank(message = "Bairro")
    @Length(min = 3, max = 30, message = "Bairro")
    private String bairro;
    @NotBlank(message = "Cep")
    @Pattern(regexp = "[\\d]{5}-[\\d]{3}",message = "Cep")
    private String cep;
    @NotBlank(message = "Cidade")
    @Length(min = 3, max = 30, message = "Cidade")
    private String cidade;
    @NotBlank(message = "Estado")
    @Length( max = 2, message = "Estado")
    private String estado;

    public ClienteRequestDTO(String nome, String telefone, Boolean ativo, String logradouro, Integer numero,
                             String complemento, String bairro, String cep, String cidade, String estado) {
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

    public Cliente converterParaEntidade(){
        return new Cliente(nome,telefone,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado);
    }

    public Cliente converterParaEntidade(Long codigo){
        return new Cliente(codigo,nome,telefone,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado);
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
