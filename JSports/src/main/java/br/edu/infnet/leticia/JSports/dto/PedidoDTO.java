package br.edu.infnet.leticia.JSports.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import br.edu.infnet.leticia.JSports.enums.StatusPedido;

public class PedidoDTO {

	private Long id;
	private Long usuarioId;
	private BigDecimal total;
	private StatusPedido status;
	private String numeroRastreamento;
	private LocalDateTime dataCriacao;
	private List<ItemCarrinhoDTO> itens;
	private PagamentoDTO pagamento;

	public PedidoDTO() {
	}

	public PedidoDTO(Long id, Long usuarioId, BigDecimal total, StatusPedido status, String numeroRastreamento,
			LocalDateTime dataCriacao, List<ItemCarrinhoDTO> itens, PagamentoDTO pagamento) {
		this.id = id;
		this.usuarioId = usuarioId;
		this.total = total;
		this.status = status;
		this.numeroRastreamento = numeroRastreamento;
		this.dataCriacao = dataCriacao;
		this.itens = itens;
		this.pagamento = pagamento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public StatusPedido getStatus() {
		return this.status;
	}

	public void setStatus(StatusPedido status) {
		this.status = status;
	}

	public String getNumeroRastreamento() {
		return numeroRastreamento;
	}

	public void setNumeroRastreamento(String numeroRastreamento) {
		this.numeroRastreamento = numeroRastreamento;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public List<ItemCarrinhoDTO> getItens() {
		return itens;
	}

	public void setItens(List<ItemCarrinhoDTO> itens) {
		this.itens = itens;
	}

	public PagamentoDTO getPagamento() {
		return pagamento;
	}

	public void setPagamento(PagamentoDTO pagamento) {
		this.pagamento = pagamento;
	}

}
