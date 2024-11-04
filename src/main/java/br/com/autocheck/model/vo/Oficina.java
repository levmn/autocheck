package br.com.autocheck.model.vo;

public class Oficina {
    private int id;
    private String unidade;
    private String cep;
    private int numeroEndereco;
    private String responsavel;

    public Oficina() {

    }

    public Oficina(int id, String unidade, String cep, int numeroEndereco, String responsavel) {
        this.id = id;
        this.unidade = unidade;
        this.cep = cep;
        this.numeroEndereco = numeroEndereco;
        this.responsavel = responsavel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public int getNumeroEndereco() {
        return numeroEndereco;
    }

    public void setNumeroEndereco(int numeroEndereco) {
        this.numeroEndereco = numeroEndereco;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    @Override
    public String toString() {
        return "Oficina{" +
                "id=" + id +
                ", unidade='" + unidade + '\'' +
                ", cep='" + cep + '\'' +
                ", numeroEndereco=" + numeroEndereco +
                ", responsavel='" + responsavel + '\'' +
                '}';
    }
}
