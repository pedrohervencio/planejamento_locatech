package br.com.locacar.api.planejamento_locatech.dto;

import br.com.locacar.api.planejamento_locatech.valueobject.Veiculo;

import java.time.LocalDate;

public record ReservaDTO(
        LocalDate dataRetirada,
        Integer qtdeDiarias,
        Integer vlrDiaria,
        Veiculo veiculo

) {
}
