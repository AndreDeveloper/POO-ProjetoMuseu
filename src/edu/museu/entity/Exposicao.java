package edu.museu.entity;

import java.util.Date;

public class Exposicao {
	private long exposicao_id;
	private String exposicao_nome;
	private double valor;
	private Date dataInicio;
	private Date dataFim;
	public long getExposicao_id() {
		return exposicao_id;
	}
	public void setExposicao_id(long exposicao_id) {
		this.exposicao_id = exposicao_id;
	}
	public String getExposicao_nome() {
		return exposicao_nome;
	}
	public void setExposicao_nome(String exposicao_nome) {
		this.exposicao_nome = exposicao_nome;
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
