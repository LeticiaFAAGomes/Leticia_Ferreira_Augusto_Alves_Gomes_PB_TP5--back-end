package br.edu.infnet.leticia.JSports.model.domain;

import java.math.BigDecimal;
import java.util.List;

import br.edu.infnet.leticia.JSports.dto.UsuarioDTO;
import br.edu.infnet.leticia.JSports.enums.StatusPedido;
import br.edu.infnet.leticia.JSports.model.Model;

public class Pedido extends Model {
	
	List<ItemEsportivo> carrinho;
	StatusPedido status;
	BigDecimal Total;
	String comentario;
	int classificacao;
	UsuarioDTO usuario;
	
	public Pedido() {}
	
	public Pedido(List<ItemEsportivo> carrinho, StatusPedido status, BigDecimal Total, UsuarioDTO usuario) {
		this.setCarrinho(carrinho);
		this.setStatus(status);
		this.setTotal(Total);
		this.setUsuario(usuario);
		this.setDataCriacao();
		this.setDataAtualizacao();
	}
	
	public List<ItemEsportivo> getCarrinho() {
		return carrinho;
	}
	public void setCarrinho(List<ItemEsportivo> carrinho) {
		this.carrinho = carrinho;
	}
	public StatusPedido getStatus() {
		return status;
	}
	public void setStatus(StatusPedido status) {
		this.status = status;
	}
	public BigDecimal getTotal() {
		return Total;
	}
	public void setTotal(BigDecimal total) {
		Total = total;
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
	public UsuarioDTO getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}
	
}
