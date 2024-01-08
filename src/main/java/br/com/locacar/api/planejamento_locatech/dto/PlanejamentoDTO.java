package br.com.locacar.api.planejamento_locatech.dto;

import br.com.locacar.api.planejamento_locatech.entities.Agendamento;
import br.com.locacar.api.planejamento_locatech.entities.Inspetor;

import java.util.List;

public record PlanejamentoDTO(
        Long numero,
        Inspetor inspetor,
        List<Agendamento> agendamentos
) {
}
