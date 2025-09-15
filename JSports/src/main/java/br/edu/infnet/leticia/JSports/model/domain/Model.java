package br.edu.infnet.leticia.JSports.model.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Model {

	@Schema(hidden = true)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Schema(hidden = true)
	@CreationTimestamp
	private LocalDateTime dataCriacao;
	@Schema(hidden = true)
	@UpdateTimestamp
	private LocalDateTime dataAtualizacao;

	public Model() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDataCriacao() {
		return dataCriacao != null ? dataCriacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) : null;
	}

	public void setDataCriacao() {
		this.dataCriacao = LocalDateTime.now();
	}

	public String getDataAtualizacao() {
		return dataAtualizacao != null ? dataAtualizacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))
				: null;
	}

	public void setDataAtualizacao() {
		this.dataAtualizacao = LocalDateTime.now();
	}
}