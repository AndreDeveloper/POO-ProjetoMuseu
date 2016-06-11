package edu.museu.entity;

public class ObraExposicaoAlteracao {
	private long exposicao_id;
	private long obra_id;
	private boolean acao;
	
	
	public boolean isAcao() {
		return acao;
	}
	public void setAcao(boolean acao) {
		this.acao = acao;
	}
	public long getExposicao_id() {
		return exposicao_id;
	}
	public void setExposicao_id(long exposicao_id) {
		this.exposicao_id = exposicao_id;
	}
	public long getObra_id() {
		return obra_id;
	}
	public void setObra_id(long obra_id) {
		this.obra_id = obra_id;
	}
	
}
