package br.com.locacar.api.planejamento_locatech.service;

import br.com.locacar.api.planejamento_locatech.controller.exception.ControllerNotFoundException;
import br.com.locacar.api.planejamento_locatech.dto.PlanejamentoDTO;
import br.com.locacar.api.planejamento_locatech.dto.ReservaDTO;
import br.com.locacar.api.planejamento_locatech.entities.Agendamento;
import br.com.locacar.api.planejamento_locatech.entities.Planejamento;
import br.com.locacar.api.planejamento_locatech.repository.PlanejamentoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Service
public class PlanejamentoService {
    private final PlanejamentoRepository planejamentoRepository;

    public PlanejamentoService(PlanejamentoRepository planejamentoRepository) {
        this.planejamentoRepository = planejamentoRepository;
    }

    public Page<PlanejamentoDTO> findAll(Pageable pageable) {
        Page<Planejamento> planejamentos = planejamentoRepository.findAll(pageable);
        return planejamentos.map(this::toDTO);
    }

    public PlanejamentoDTO findById(Long numero) {
        Planejamento planejamento = planejamentoRepository.findById(numero).
                orElseThrow(() -> new ControllerNotFoundException("Planejamento não encontrado"));
        return toDTO(planejamento);
    }

    public PlanejamentoDTO save(PlanejamentoDTO planejamentoDTO) {
        Planejamento planejamento = toEntity(planejamentoDTO);
        planejamento = planejamentoRepository.save(planejamento);
        bloqueiaReserva(planejamento);
        return toDTO(planejamento);
    }

    public PlanejamentoDTO update(Long numero, PlanejamentoDTO planejamentoDTO) {
        Planejamento planejamento = planejamentoRepository.getReferenceById(numero);
        planejamento.setInspetor(planejamentoDTO.inspetor());
        planejamento.setAgendamentos(planejamentoDTO.agendamentos());
        planejamento = planejamentoRepository.save(planejamento);
        bloqueiaReserva(planejamento);
        return toDTO(planejamento);
    }

    public void delete(Long numero) {
        planejamentoRepository.deleteById(numero);
        return;
    }


    private Planejamento toEntity(PlanejamentoDTO planejamentoDTO) {
        return new Planejamento(
                planejamentoDTO.numero(),
                planejamentoDTO.inspetor(),
                planejamentoDTO.agendamentos()
        );
    }

    private PlanejamentoDTO toDTO(Planejamento planejamento) {
        return new PlanejamentoDTO(
                planejamento.getNumero(),
                planejamento.getInspetor(),
                planejamento.getAgendamentos()
        );
    }
    
    private void bloqueiaReserva(Planejamento planejamento) {
        List<Agendamento> agendamentos = planejamento.getAgendamentos();
        for (Agendamento agendamento: agendamentos) {
            ReservaDTO reservaDTO = new ReservaDTO(
                    agendamento.getData(),
                    agendamento.getDuracao(),
                    0,
                    agendamento.getVeiculo()
            );

            RestTemplate restTemplate = new RestTemplate();
            try {
                URI uri = new URI("http://localhost:" + "3002" + "/reserva");
                ResponseEntity<ReservaDTO> result = restTemplate.postForEntity(uri, reservaDTO, ReservaDTO.class);
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }
        return;
    }
}
