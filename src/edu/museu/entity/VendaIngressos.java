package edu.museu.entity;

public class VendaIngressos {
	private long ingressoId;
	private String NomeObra;
	private double ValorObra;
	private double ValorPagar;
	private double Desconto;
	private boolean MeiaEntrada;
	private boolean gratuito;
	



	public long getIngressoId() {
		return ingressoId;
	}




	public void setIngressoId(long ingressoId) {
		this.ingressoId = ingressoId;
	}




	public String getNomeObra() {
		return NomeObra;
	}




	public void setNomeObra(String nomeObra) {
		NomeObra = nomeObra;
	}




	public double getValorObra() {
		return ValorObra;
	}




	public void setValorObra(double valorObra) {
		ValorObra = valorObra;
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




	public VendaIngressos()
	{
		
	}
	
}
