package br.edu.infnet.leticia.JSports.model.domain;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ItemPedido extends Model {

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private ItemEsportivo produto;

    private int quantidade;

    private BigDecimal precoUnitario;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    public ItemPedido() {}

    public ItemPedido(ItemEsportivo produto, int quantidade, BigDecimal precoUnitario) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    public ItemEsportivo getProduto() {
        return produto;
    }

    public void setProduto(ItemEsportivo produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
