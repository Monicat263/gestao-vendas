package com.gvendas.gestaovendas.entidades;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name ="cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private Long codigo;

    @Column(name = "nome")
    private String nome;

    @Column(name ="telefone")
    private String telefone;

    @Column(name ="ativo")
    private Boolean ativo;


    @Column(name = "logradouro")
    private String logradouro;

    @Column(name = "numero")
    private Integer numero;

    @Column(name = "complemento")
    private String complemento;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "cep")
    private String cep;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "estado")
    private String estado;

    public Cliente() {

    }
    // Construtor sem pasasr código
    public Cliente( String nome, String telefone, Boolean ativo, String logradouro,
                   Integer numero, String complemento, String bairro, String cep, String cidade, String estado) {
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
     // Construtor passando código
    public Cliente( Long codigo, String nome, String telefone, Boolean ativo, String logradouro,
                    Integer numero, String complemento, String bairro, String cep, String cidade, String estado) {
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
        this.codigo = codigo;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(codigo, cliente.codigo) && Objects.equals(nome, cliente.nome) && Objects.equals(telefone, cliente.telefone) && Objects.equals(ativo, cliente.ativo) && Objects.equals(logradouro, cliente.logradouro) && Objects.equals(numero, cliente.numero) && Objects.equals(complemento, cliente.complemento) && Objects.equals(bairro, cliente.bairro) && Objects.equals(cep, cliente.cep) && Objects.equals(cidade, cliente.cidade) && Objects.equals(estado, cliente.estado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, nome, telefone, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado);
    }
}
