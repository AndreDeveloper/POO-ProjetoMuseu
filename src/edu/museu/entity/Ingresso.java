package edu.museu.entity;

public class Ingresso {
	private long ingressoId;
	private String nome;
	private double valor;
	private double ValorPagar;
	private double Desconto;
	private boolean MeiaEntrada;
	private boolean gratuito;
	private int qtdade;
	public long getIngressoId() {
		return ingressoId;
	}
	public void setIngressoId(long ingressoId) {
		this.ingressoId = ingressoId;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public double getValorPagar() {
		return ValorPagar;
	}
	public void setValorPagar(double valorPagar) {
		ValorPagar = valorPagar;
	}
	public double getDesconto() {
		return Desconto;
	}
	public void setDesconto(double desconto) {
		Desconto = desconto;
	}
	public boolean isMeiaEntrada() {
		return MeiaEntrada;
	}
	public void setMeiaEntrada(boolean meiaEntrada) {
		MeiaEntrada = meiaEntrada;
	}
	public boolean isGratuito() {
		return gratuito;
	}
	public void setGratuito(boolean gratuito) {
		this.gratuito = gratuito;
	}
	public int getQtdade() {
		return qtdade;
	}
	public void setQtdade(int qtdade) {
		this.qtdade = qtdade;
	}
	
	

	
}
