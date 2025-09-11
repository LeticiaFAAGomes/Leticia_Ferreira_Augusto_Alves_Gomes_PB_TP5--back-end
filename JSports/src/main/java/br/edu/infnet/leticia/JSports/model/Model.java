package br.edu.infnet.leticia.JSports.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Model {

    private Long id;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
    private String email;
    private String senha;

    public Model() {}

    public Model(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDataCriacao() {
        return dataCriacao != null 
            ? dataCriacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) 
            : null;
    }

    public void setDataCriacao() {
        this.dataCriacao = LocalDateTime.now();
    }

    public String getDataAtualizacao() {
        return dataAtualizacao != null
            ? dataAtualizacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))
            : null;
    }

    public void setDataAtualizacao() {
        this.dataAtualizacao = LocalDateTime.now();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}