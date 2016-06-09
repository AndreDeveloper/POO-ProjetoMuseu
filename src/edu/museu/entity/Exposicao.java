package edu.museu.entity;

import java.util.Date;

public class Exposicao {
	private long id;
	private String nome;
	private double valor;
	private Date dataInicio;
	private Date dataFim;
	public long getId() {
		return id;
	}
	public void setId(long exposicao_id) {
		this.id = exposicao_id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String exposicao_nome) {
		this.nome = exposicao_nome;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataFim() {
		return dataFim;
	}
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	
	
	
	
}
