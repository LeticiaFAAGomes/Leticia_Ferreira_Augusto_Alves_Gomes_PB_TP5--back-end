package br.edu.infnet.leticia.JSports.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PagamentoDTO {
	private Long id;
	private String formaPagamento;
	private BigDecimal valor;
	private String statusPagamento;
	private LocalDateTime dataPagamento;

	public PagamentoDTO() {
	}

	public PagamentoDTO(Long id, String formaPagamento, BigDecimal valor, String statusPagamento,
			LocalDateTime dataPagamento) {
		this.id = id;
		this.formaPagamento = formaPagamento;
		this.valor = valor;
		this.statusPagamento = statusPagamento;
		this.dataPagamento = dataPagamento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getStatusPagamento() {
		return statusPagamento;
	}

	public void setStatusPagamento(String statusPagamento) {
		this.statusPagamento = statusPagamento;
	}

	public LocalDateTime getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDateTime dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

}
