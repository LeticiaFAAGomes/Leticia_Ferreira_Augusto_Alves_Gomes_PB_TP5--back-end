package br.edu.infnet.leticia.JSports.model.domain;

import java.math.BigDecimal;

import br.edu.infnet.leticia.JSports.dto.ItemEsportivoDTO;
import br.edu.infnet.leticia.JSports.enums.ItemCategoria;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ItemEsportivo extends Model {

	String nome;
	int quantidade;
	@Enumerated(EnumType.STRING)
	ItemCategoria categoria;
	BigDecimal preco;
	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;
	@ManyToOne
	@JoinColumn(name = "pedido_id")
	private Pedido pedido;

	public ItemEsportivo() {
	}

	public ItemEsportivo(String nome, int quantidade, BigDecimal preco, ItemCategoria categoria) {
		this.setNome(nome);
		this.setQuantidade(quantidade);
		this.setCategoria(categoria);
		this.setPreco(preco);
		this.setDataCriacao();
		this.setDataAtualizacao();
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
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

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario vendedor) {
		this.usuario = vendedor;
	}

	public ItemEsportivoDTO toDTO() {
		ItemEsportivoDTO dto = new ItemEsportivoDTO();
		dto.setId(this.getId());
		dto.setNome(this.getNome());
		dto.setPreco(this.getPreco());
		dto.setQuantidade(this.getQuantidade());
		dto.setCategoria(this.getCategoria().name());
		dto.setUsuarioId(this.getUsuario() != null ? this.getUsuario().getId() : null);
		return dto;
	}

	public ItemEsportivo(ItemEsportivoDTO dto) {
		this.setId(dto.getId());
		this.setNome(dto.getNome());
		this.setPreco(dto.getPreco());
		this.setQuantidade(dto.getQuantidade());
		this.setCategoria(ItemCategoria.valueOf(dto.getCategoria()));
		this.setDataCriacao();
		this.setDataAtualizacao();
	}

	@Override
	public String toString() {
		return "ItemEsportivo [nome=" + nome + ", quantidade=" + quantidade + ", categoria=" + categoria + ", preco="
				+ preco + "]\n";
	}

}
