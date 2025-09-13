package br.edu.infnet.leticia.JSports.model.domain;

import java.math.BigDecimal;

import br.edu.infnet.leticia.JSports.enums.ItemCategoria;
import br.edu.infnet.leticia.JSports.enums.ItemSubcategoria;
import br.edu.infnet.leticia.JSports.model.Model;

public class ItemEsportivo extends Model {

	String nome;
	int quantidade;
	ItemCategoria categoria;
	ItemSubcategoria subcategoria;
	BigDecimal preco;

	public ItemEsportivo(String nome, int quantidade, BigDecimal preco, ItemCategoria categoria, ItemSubcategoria subcategoria) {
		this.setNome(nome);
		this.setQuantidade(quantidade);
		this.setCategoria(categoria);
		this.setSubcategoria(subcategoria);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public ItemSubcategoria getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(ItemSubcategoria subcategoria) {
		this.subcategoria = subcategoria;
	}

}
