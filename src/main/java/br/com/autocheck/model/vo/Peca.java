package br.com.autocheck.model.vo;

public class Peca {
    private int id;
    private String nome;
    private double valor;

    public Peca(int id, String nome, double valor) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Peca [id=" + id + ", nome=" + nome + ", valor=" + valor + "]";
    }
}
