package br.edu.infnet.leticia.JSports.model.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.edu.infnet.leticia.JSports.enums.StatusPagamento;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import br.edu.infnet.leticia.JSports.dto.PagamentoDTO;
import br.edu.infnet.leticia.JSports.enums.FormaPagamento;

@Entity
public class Pagamento extends Model {

	private BigDecimal valor;
	@Enumerated(EnumType.STRING)
	private FormaPagamento formaPagamento;
	@Enumerated(EnumType.STRING)
	private StatusPagamento statusPagamento;
	private LocalDateTime dataPagamento;

	public Pagamento() {
	}

	public Pagamento(FormaPagamento formaPagamento, BigDecimal valor) {

		this.setStatusPagamento(StatusPagamento.PENDENTE);
		this.setFormaPagamento(formaPagamento);
		this.setValor(valor);
		this.setDataPagamento();
	}

	public StatusPagamento getStatusPagamento() {
		return this.statusPagamento;
	}

	public void setStatusPagamento(StatusPagamento statusPagamento) {
		this.statusPagamento = statusPagamento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public LocalDateTime getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento() {
		this.dataPagamento = LocalDateTime.now();
	}

	public PagamentoDTO toDTO() {
		PagamentoDTO dto = new PagamentoDTO();
		dto.setId(this.getId());
		dto.setValor(this.getValor());
		dto.setFormaPagamento(this.getFormaPagamento().name());
		dto.setStatusPagamento(this.getStatusPagamento().name());
		dto.setDataPagamento(this.getDataPagamento());
		return dto;
	}

	public Pagamento(PagamentoDTO dto) {
		this.setId(dto.getId());
		this.setValor(dto.getValor());
		this.setFormaPagamento(FormaPagamento.valueOf(dto.getFormaPagamento()));
		this.setStatusPagamento(StatusPagamento.valueOf(dto.getStatusPagamento()));
		this.dataPagamento = dto.getDataPagamento();
	}

}
