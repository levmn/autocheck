package br.com.autocheck.model.vo;

public class Diagnostico {
    private int id;
    private String codigoFalha;
    private String categoria;
    private String descricao;
    private String descricaoProblema;

    public Diagnostico() {

    }

    public Diagnostico(int id, String codigoFalha, String categoria, String descricao, String descricaoProblema) {
        this.id = id;
        this.codigoFalha = codigoFalha;
        this.categoria = categoria;
        this.descricao = descricao;
        this.descricaoProblema = descricaoProblema;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigoFalha() {
        return codigoFalha;
    }

    public void setCodigoFalha(String codigoFalha) {
        this.codigoFalha = codigoFalha;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricaoProblema() {
        return descricaoProblema;
    }

    public void setDescricaoProblema(String descricaoProblema) {
        this.descricaoProblema = descricaoProblema;
    }

    @Override
    public String toString() {
        return "Diagnostico [id=" + id + ", codigoFalha=" + codigoFalha + ", categoria=" + categoria + ", descricao="
                + descricao + ", descricaoProblema=" + descricaoProblema + "]";
    }
}
