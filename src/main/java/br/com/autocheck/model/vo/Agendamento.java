package br.com.autocheck.model.vo;

import java.time.LocalDateTime;

public class Agendamento {
    private LocalDateTime dataHora;
    private boolean disponivel;

    public Agendamento() {

    }

    public Agendamento(LocalDateTime dataHora, boolean disponivel) {
        this.dataHora = dataHora;
        this.disponivel = disponivel;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
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
