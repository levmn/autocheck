package br.com.autocheck.model.vo;

public class Carro {
    private int id;
    private String chassi;
    private String marca;
    private String modelo;
    private String anoFabricacao;
    private String anoModelo;
    private Usuario usuario;

    public Carro() {

    }

    public Carro(int id, String chassi, String marca, String modelo, String anoFabricacao, String anoModelo) {
        this.id = id;
        this.chassi = chassi;
        this.marca = marca;
        this.modelo = modelo;
        this.anoFabricacao = anoFabricacao;
        this.anoModelo = anoModelo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChassi() {
        return chassi;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(String anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public String getAnoModelo() {
        return anoModelo;
    }

    public void setAnoModelo(String anoModelo) {
        this.anoModelo = anoModelo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Carro{" +
                "id=" + id +
                ", chassi='" + chassi + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", anoFabricacao='" + anoFabricacao + '\'' +
                ", anoModelo='" + anoModelo + '\'' +
                ", usuario=" + usuario +
                '}';
    }

}
