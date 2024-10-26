package br.com.autocheck.model.vo;

public class Agendamento {

	private String dataHora;
	private boolean disponivel;

	public Agendamento(String dataHora, boolean disponivel) {
		this.dataHora = dataHora;
		this.disponivel = disponivel;
	}

	public String getDataHora() {
		return dataHora;
	}

	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
	}

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}

	@Override
	public String toString() {
		return "Agendamento [dataHora=" + dataHora + ", disponivel=" + disponivel + "]";
	}

}
