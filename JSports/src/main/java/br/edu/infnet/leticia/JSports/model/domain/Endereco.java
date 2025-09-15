package br.edu.infnet.leticia.JSports.model.domain;

import br.edu.infnet.leticia.JSports.dto.EnderecoDTO;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;

@Entity
public class Endereco extends Model {

	@NotNull
	private String rua;
	@NotNull
	private String numero;
	@NotNull
	private String bairro;
	private String complemento;
	@NotNull
	private String cidade;
	@NotNull
	private String estado;
	@NotNull
	private String cep;

	public Endereco() {
	}

	public Endereco(String rua, String numero, String bairro, String complemento, String cidade, String estado,
			String cep) {
		this.rua = rua;
		this.numero = numero;
		this.bairro = bairro;
		this.complemento = complemento;
		this.cidade = cidade;
		this.estado = estado;
		this.cep = cep;
	}

	public Endereco(String rua, String numero, String bairro, String cidade, String estado, String cep) {
		this.rua = rua;
		this.numero = numero;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.cep = cep;
	}

	public Endereco(EnderecoDTO dto) {
		this.setRua(dto.getRua());
		this.setNumero(dto.getNumero());
		this.setBairro(dto.getBairro());
		this.setComplemento(dto.getComplemento());
		this.setCidade(dto.getCidade());
		this.setEstado(dto.getEstado());
		this.setCep(dto.getCep());
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
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

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public EnderecoDTO toDTO() {
		EnderecoDTO dto = new EnderecoDTO();
		dto.setRua(this.getRua());
		dto.setNumero(this.getNumero());
		dto.setBairro(this.getBairro());
		dto.setComplemento(this.getComplemento());
		dto.setCidade(this.getCidade());
		dto.setEstado(this.getEstado());
		dto.setCep(this.getCep());
		return dto;
	}

}
