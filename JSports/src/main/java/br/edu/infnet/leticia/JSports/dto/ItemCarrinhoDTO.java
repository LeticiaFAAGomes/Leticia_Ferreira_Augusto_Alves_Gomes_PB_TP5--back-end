package br.edu.infnet.leticia.JSports.dto;

public class ItemCarrinhoDTO {
	private Long idProduto;
	private int quantidade;

	public ItemCarrinhoDTO() {
	}

	public ItemCarrinhoDTO(Long idProduto, int quantidade) {
		this.idProduto = idProduto;
		this.quantidade = quantidade;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

}
