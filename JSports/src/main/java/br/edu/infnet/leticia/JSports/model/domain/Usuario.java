package br.edu.infnet.leticia.JSports.model.domain;

import java.util.List;

import br.edu.infnet.leticia.JSports.dto.UsuarioDTO;
import br.edu.infnet.leticia.JSports.enums.TipoUsuario;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Usuario extends Model {

	private String nome;
	private String email;
	private String senha;
	private String telefone;
	@Enumerated(EnumType.STRING)
	private TipoUsuario tipoUsuario;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_id")
	private Endereco endereco;
	@OneToMany(mappedBy = "usuario")
	private List<Pedido> pedidos;
	@OneToMany(mappedBy = "usuario")
	private List<ItemEsportivo> itens;
	private String cpf;
	private String cnpj;

	public Usuario() {
		this.setDataAtualizacao();
		this.setDataCriacao();
	}

	public Usuario(String nome, String email, String senha, String telefone, TipoUsuario tipoUsuario) {

		setNome(nome);
		setEmail(email);
		setSenha(senha);
		setTelefone(telefone);
		setTipoUsuario(tipoUsuario);
		this.setDataAtualizacao();
	}

	public Usuario(String nome, String senha, String telefone, Endereco endereco, TipoUsuario tipoUsuario) {
		this.setNome(nome);
		this.setSenha(senha);
		this.setTelefone(telefone);
		this.setEndereco(endereco);
		this.setTipoUsuario(tipoUsuario);
		this.setDataAtualizacao();
		this.setDataCriacao();
	}

	public Usuario(UsuarioDTO dto) {
		this.setId(dto.getId());
		this.setNome(dto.getNome());
		this.setEmail(dto.getEmail());
		this.setSenha(dto.getSenha());
		this.setTelefone(dto.getTelefone());
		this.setTipoUsuario(dto.getTipoUsuario());
		this.setDataCriacao();
		this.setDataAtualizacao();

		if (tipoUsuario == TipoUsuario.CLIENTE) {
			this.setCpf(dto.getCpf());
		} else {
			this.setCnpj(dto.getCnpj());
		}

		if (dto.getEndereco() != null) {
			this.setEndereco(new Endereco(dto.getEndereco()));
		}
	}

	public Endereco getEndereco() {
		return this.endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public UsuarioDTO toDTO() {
		UsuarioDTO dto = new UsuarioDTO();
		dto.setId(this.getId());
		dto.setNome(this.getNome());
		dto.setSenha(this.getSenha());
		dto.setEmail(this.getEmail());
		dto.setTelefone(this.getTelefone());
		dto.setTipoUsuario(this.getTipoUsuario());

		if (tipoUsuario == TipoUsuario.CLIENTE) {
			dto.setCpf(this.getCpf());
		} else {
			dto.setCnpj(this.getCnpj());
		}

		if (this.getEndereco() != null) {
			dto.setEndereco(this.getEndereco().toDTO());
		}

		return dto;
	}

}
