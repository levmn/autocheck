package br.com.autocheck.model.vo;

import java.time.LocalDateTime;

public class Orcamento {
    private double valorFinal;
    private double valorPecas;
    private double valorMaoObra;
    private LocalDateTime dataHora;

    public Orcamento() {

    }

    public Orcamento(double valorFinal, double valorPecas, double valorMaoObra, LocalDateTime dataHora) {
        this.valorFinal = valorFinal;
        this.valorPecas = valorPecas;
        this.valorMaoObra = valorMaoObra;
        this.dataHora = dataHora;
    }

    public double getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(double valorFinal) {
        this.valorFinal = valorFinal;
    }

    public double getValorPecas() {
        return valorPecas;
    }

    public void setValorPecas(double valorPecas) {
        this.valorPecas = valorPecas;
    }

    public double getValorMaoObra() {
        return valorMaoObra;
    }

    public void setValorMaoObra(double valorMaoObra) {
        this.valorMaoObra = valorMaoObra;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    @Override
    public String toString() {
        return "Orcamento{" +
                "valorFinal=" + valorFinal +
                ", valorPecas=" + valorPecas +
                ", valorMaoObra=" + valorMaoObra +
                ", dataHora=" + dataHora +
                '}';
    }
}
