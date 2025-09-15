package br.edu.infnet.leticia.JSports.dto;

import java.math.BigDecimal;

public class ItemEsportivoDTO {
	private Long id;
	private String nome;
	private BigDecimal preco;
	private int quantidade;
	private String categoria;
	private Long usuarioId;

	public ItemEsportivoDTO() {
	}

	public ItemEsportivoDTO(Long id, String nome, BigDecimal preco, int quantidade, String categoria, Long usuarioId) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
		this.categoria = categoria;
		this.usuarioId = usuarioId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

}
