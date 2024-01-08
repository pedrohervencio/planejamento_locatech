package br.com.locacar.api.planejamento_locatech.repository;

import br.com.locacar.api.planejamento_locatech.entities.Planejamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanejamentoRepository  extends JpaRepository<Planejamento, Long> {
}
