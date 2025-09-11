package br.edu.infnet.leticia.JSports.dto;

import br.edu.infnet.leticia.JSports.enums.TipoUsuario;

public class UsuarioDTO {

    private String nome;
    private String email;
    private String senha;
    private String telefone;
	private TipoUsuario tipoUsuario;

	public UsuarioDTO() {}
	
    public UsuarioDTO(String email, String senha) {
 
        setEmail(email);
        setSenha(senha);
    }
	
    public UsuarioDTO(String nome, String email, String senha, String telefone, TipoUsuario tipoUsuario) {
    	
        setNome(nome);
        setEmail(email);
        setSenha(senha);
        setTelefone(telefone);
        setTipoUsuario(tipoUsuario);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}

