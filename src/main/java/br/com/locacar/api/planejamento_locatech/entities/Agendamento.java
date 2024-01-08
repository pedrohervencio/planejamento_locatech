package br.com.locacar.api.planejamento_locatech.entities;

import br.com.locacar.api.planejamento_locatech.valueobject.Veiculo;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tb_agendamento")
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long numero;
    private LocalDate data;
    private Integer duracao;
    private Veiculo veiculo;

    public Agendamento() {}

    public Agendamento(Long numero, LocalDate data, Integer duracao, Veiculo veiculo) {
        this.numero = numero;
        this.data = data;
        this.duracao = duracao;
        this.veiculo = veiculo;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agendamento that = (Agendamento) o;
        return Objects.equals(numero, that.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }

    @Override
    public String toString() {
        return "Agendamento{" +
                "numero=" + numero +
                ", data=" + data +
                ", duracao=" + duracao +
                ", veiculo=" + veiculo +
                '}';
    }
}
