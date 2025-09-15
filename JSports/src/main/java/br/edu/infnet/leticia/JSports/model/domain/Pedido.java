package br.edu.infnet.leticia.JSports.model.domain;

import java.math.BigDecimal;
import java.util.List;

import br.edu.infnet.leticia.JSports.dto.PedidoDTO;
import br.edu.infnet.leticia.JSports.enums.StatusPedido;
import jakarta.persistence.*;

@Entity
public class Pedido extends Model {

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "pedido_id")
	private List<ItemEsportivo> carrinho;

	@Enumerated(EnumType.STRING)
	private StatusPedido status;

	private BigDecimal total;

	private String comentario;

	private int classificacao;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "pedido_id")
	private List<ItemPedido> itens;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pagamento_id")
	private Pagamento pagamento;

	public Pedido() {
	}

    public Pedido(List<ItemPedido> itens, StatusPedido status, Pagamento pagamento, Usuario user) {
        this.itens = itens;
        this.status = status;
        this.pagamento = pagamento;
        this.usuario = user;
        this.total = pagamento.getValor();
        this.setDataCriacao();
        this.setDataAtualizacao();
    }

	public Pedido(PedidoDTO dto) {
		this.setId(dto.getId());
		this.setStatus(dto.getStatus());
		this.setNumeroRastreamento(dto.getNumeroRastreamento());
		this.setTotal(dto.getTotal());
		this.setDataCriacao();
		this.setDataAtualizacao();
	}

	public List<ItemEsportivo> getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(List<ItemEsportivo> carrinho) {
		this.carrinho = carrinho;
	}
	
    public List<ItemPedido> getItens() {
        return itens;
    }
    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

	public StatusPedido getStatus() {
		return status;
	}

	public void setStatus(StatusPedido status) {
		setDataAtualizacao();
		this.status = status;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public int getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(int classificacao) {
		this.classificacao = classificacao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	private String numeroRastreamento;

	public String getNumeroRastreamento() {
		return numeroRastreamento;
	}

	public void setNumeroRastreamento(String numeroRastreamento) {
		this.numeroRastreamento = numeroRastreamento;
	}

	public PedidoDTO toDTO() {
		PedidoDTO dto = new PedidoDTO();
		dto.setId(this.getId());
		dto.setStatus(StatusPedido.valueOf(this.getStatus().name()));
		dto.setNumeroRastreamento(this.getNumeroRastreamento());
		dto.setTotal(this.getTotal());
		dto.setUsuarioId(this.getUsuario() != null ? this.getUsuario().getId() : null);
		return dto;
	}

}
