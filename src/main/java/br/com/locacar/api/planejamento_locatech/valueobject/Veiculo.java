package br.com.locacar.api.planejamento_locatech.valueobject;

import jakarta.persistence.Embeddable;
import java.util.Objects;

/*
  Apesar de Veiculo ter um ID, nao eh necessario representa-lo em uma tabela relacional, o veiculo nao sofrera alteracoes
  no ciclo de vida (do ponto de vista de agendamento), se enquadra melhor cmomo um Value Object
 */
@Embeddable
public class Veiculo {
    private Long id;
    private String marca;
    private String modelo;
    private Integer anoFabricacao;
    private Integer anoModelo;
    private String cor;

    public Veiculo () {}

    public Veiculo(Long id, String marca, String modelo, Integer anoFabricacao, Integer anoModelo, String cor) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.anoFabricacao = anoFabricacao;
        this.anoModelo = anoModelo;
        this.cor = cor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(Integer anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public Integer getAnoModelo() {
        return anoModelo;
    }

    public void setAnoModelo(Integer anoModelo) {
        this.anoModelo = anoModelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Veiculo veiculo = (Veiculo) o;
        return Objects.equals(id, veiculo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Veiculo{" +
                "id=" + id +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", anoFabricacao=" + anoFabricacao +
                ", anoModelo=" + anoModelo +
                ", cor='" + cor + '\'' +
                '}';
    }
}
