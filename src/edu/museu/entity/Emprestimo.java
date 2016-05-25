package edu.museu.entity;

import java.util.Date;

public class Emprestimo {
	private Date data;
	private long obra_id;
	private long emprestimo_id;
	private long local_emprestimo_id;
	private String locatario;
	private String nomedaObra;
	private String Destinatario;
	private String devolvido;
	public String getDevolvido() {
		return devolvido;
	}


	public void setDevolvido(String devolvido) {
		this.devolvido = devolvido;
	}


	public void setEmprestimo_id(long emprestimo_id) {
		this.emprestimo_id = emprestimo_id;
	}


	private Date dataSaida;
	private Date previsaoDevolucao;	
	private Date dataDevolucao;	
	    

	public Emprestimo() {
		super();
	}


	public Date getData() {
		return data;
	}


	public long getObra_id() {
		return obra_id;
	}


	public long getEmprestimo_id() {
		return emprestimo_id;
	}
	
	public String getLocatario() {
		return locatario;
	}


	public long getLocal_emprestimo_id() {
		return local_emprestimo_id;
	}


	public String getNomedaObra() {
		return nomedaObra;
	}


	public String getDestinatario() {
		return Destinatario;
	}


	public Date getDataSaida() {
		return dataSaida;
	}


	public Date getPrevisaoDevolucao() {
		return previsaoDevolucao;
	}


	public Date getDataDevolucao() {
		return dataDevolucao;
	}


	public void setData(Date data) {
		this.data = data;
	}
	
	public void setLocatario(String locatario) {
		this.locatario = locatario;
	}


	public void setObra_id(long l) {
		this.obra_id = l;
	}


	public void setEmprestimo_id(int emprestimo_id) {
		this.emprestimo_id = emprestimo_id;
	}


	public void setLocal_emprestimo_id(long a) {
		this.local_emprestimo_id = a;
	}


	public void setNomedaObra(String nomedaObra) {
		this.nomedaObra = nomedaObra;
	}


	public void setDestinatario(String destinatario) {
		Destinatario = destinatario;
	}


	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}


	public void setPrevisaoDevolucao(Date previsaoDevolucao) {
		this.previsaoDevolucao = previsaoDevolucao;
	}


	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}


	
	
	
}
