package br.com.autocheck.model.vo;

public class Agendamento {

	private String dataHora;
	private boolean disponibilidade;

	public Agendamento(String dataHora, boolean disponibilidade) {
		this.dataHora = dataHora;
		this.disponibilidade = disponibilidade;
	}

	public String getDataHora() {
		return dataHora;
	}

	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
	}

	public boolean isDisponibilidade() {
		return disponibilidade;
	}

	public void setDisponibilidade(boolean disponibilidade) {
		this.disponibilidade = disponibilidade;
	}

	@Override
	public String toString() {
		return "Agendamento [dataHora=" + dataHora + ", disponibilidade=" + disponibilidade + "]";
	}

}
