package br.com.locacar.api.planejamento_locatech.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_planejamento")
public class Planejamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numero;
    @ManyToOne
    private Inspetor inspetor;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "agendamento_id")
    private List<Agendamento> agendamentos;

    public Planejamento() {}

    public Planejamento(Long numero, Inspetor inspetor, List<Agendamento> agendamentos) {
        this.numero = numero;
        this.inspetor = inspetor;
        this.agendamentos = agendamentos;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public Inspetor getInspetor() {
        return inspetor;
    }

    public void setInspetor(Inspetor inspetor) {
        this.inspetor = inspetor;
    }

    public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planejamento that = (Planejamento) o;
        return Objects.equals(numero, that.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }

    @Override
    public String toString() {
        return "Planejamento{" +
                "numero=" + numero +
                ", inspetor=" + inspetor +
                ", agendamentos=" + agendamentos +
                '}';
    }
}
