package br.edu.infnet.leticia.JSports.model;

import java.math.BigDecimal;

import br.edu.infnet.leticia.JSports.enums.ItemCategoria;

public class ItemCarrinho {

	private Long idProduto;
	private String nome;
	private BigDecimal precoUnitario;
	private int quantidade;
	private ItemCategoria categoria;

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(BigDecimal precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public ItemCategoria getCategoria() {
		return categoria;
	}

	public void setCategoria(ItemCategoria categoria) {
		this.categoria = categoria;
	}

	public BigDecimal getSubtotal() {
		return precoUnitario.multiply(BigDecimal.valueOf(quantidade));
	}

}
