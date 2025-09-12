package br.edu.infnet.leticia.JSports.model.domain;

import br.edu.infnet.leticia.JSports.enums.TipoUsuario;
import br.edu.infnet.leticia.JSports.model.Model;

public class Usuario extends Model {
	
	String nome;
	String senha;
	String telefone;
	Endereco endereco;
	TipoUsuario tipoUsuario;
	
	public Usuario() {}
	
	public Usuario(String nome, String senha, String telefone, Endereco endereco, TipoUsuario tipoUsuario) {
		super(nome, senha);
		this.telefone = telefone;
		this.endereco = endereco;
		this.tipoUsuario = tipoUsuario;
	}
}
