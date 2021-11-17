package br.com.adan.oficinamecanica.modelo;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Servico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne(fetch = FetchType.EAGER)
	private Cliente cliente;
	@ManyToOne(fetch = FetchType.EAGER)
	private Recepcionista recepcionista;
	@ManyToOne(fetch = FetchType.EAGER)
	private Mecanico mecanico;
	private Date dataRequisicao;
	private Date dataConclusao;
	@Column(columnDefinition = "text")
	private String requisicao;
	private Boolean terminado;

	public Servico() {	}

	public Servico(Integer id, Cliente cliente, Recepcionista recepcionista, Mecanico mecanico, Date dataRequisicao,
			Date dataConclusao, String requisicao, Boolean terminado) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.recepcionista = recepcionista;
		this.mecanico = mecanico;
		this.dataRequisicao = dataRequisicao;
		this.dataConclusao = dataConclusao;
		this.requisicao = requisicao;
		this.terminado = terminado;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Recepcionista getRecepcionista() {
		return recepcionista;
	}

	public void setRecepcionista(Recepcionista recepcionista) {
		this.recepcionista = recepcionista;
	}

	public Mecanico getMecanico() {
		return mecanico;
	}

	public void setMecanico(Mecanico mecanico) {
		this.mecanico = mecanico;
	}

	public Date getDataRequisicao() {
		return dataRequisicao;
	}

	public void setDataRequisicao(Date dataRequisicao) {
		this.dataRequisicao = dataRequisicao;
	}

	public Date getDataConclusao() {
		return dataConclusao;
	}

	public void setDataConclusao(Date dataConclusao) {
		this.dataConclusao = dataConclusao;
	}

	public String getRequisicao() {
		return requisicao;
	}

	public void setRequisicao(String requisicao) {
		this.requisicao = requisicao;
	}

	public Boolean getTerminado() {
		return terminado;
	}

	public void setTerminado(Boolean terminado) {
		this.terminado = terminado;
	}

}
