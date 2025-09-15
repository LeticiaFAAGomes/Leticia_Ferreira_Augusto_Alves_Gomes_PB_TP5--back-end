package br.edu.infnet.leticia.JSports.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Carrinho {

	private List<ItemCarrinho> itens = new ArrayList<>();

	public void adicionarItem(ItemCarrinho item) {
		itens.add(item);
	}

	public void removerPorIndice(int indice) {
		if (indice >= 0 && indice < itens.size()) {
			itens.remove(indice);
		}
	}

	public List<ItemCarrinho> listar() {
		return itens;
	}

	public BigDecimal calcularTotal() {
		return itens.stream().map(ItemCarrinho::getSubtotal).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public void limpar() {
		itens.clear();
	}

	public boolean estaVazio() {
		return itens.isEmpty();
	}
}
