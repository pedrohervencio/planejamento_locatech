package br.com.locacar.api.planejamento_locatech.controller;

import br.com.locacar.api.planejamento_locatech.dto.PlanejamentoDTO;
import br.com.locacar.api.planejamento_locatech.service.PlanejamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/manutencao/planejamento")
@RestController
public class PlanejamentoController {
    private final PlanejamentoService planejamentoService;

    @Autowired
    public PlanejamentoController(PlanejamentoService planejamentoService) {
        this.planejamentoService = planejamentoService;
    }

    @GetMapping
    public ResponseEntity<Page<PlanejamentoDTO>> findAll(
            @PageableDefault(size = 10, page = 0, sort = "numero")Pageable pageable
            ) {
        Page<PlanejamentoDTO> planejamentosDTO = planejamentoService.findAll(pageable);
        return ResponseEntity.ok(planejamentosDTO);
    }

    @GetMapping("/{numero}")
    public ResponseEntity<PlanejamentoDTO> findById(@PathVariable Long numero) {
        PlanejamentoDTO planejamentoDTO = planejamentoService.findById(numero);
        return ResponseEntity.ok(planejamentoDTO);
    }

    @PostMapping
    public ResponseEntity<PlanejamentoDTO> save(@RequestBody PlanejamentoDTO planejamentoDTO) {
        PlanejamentoDTO savedPlanejamentoDTO = planejamentoService.save(planejamentoDTO);
        return new ResponseEntity<>(savedPlanejamentoDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{numero}")
    public ResponseEntity<PlanejamentoDTO> update(
            @PathVariable Long numero,
            @RequestBody PlanejamentoDTO planejamentoDTO
    ) {
        PlanejamentoDTO updatedPlanejamentoDTO = planejamentoService.update(numero, planejamentoDTO);
        return ResponseEntity.ok(updatedPlanejamentoDTO);
    }

    @DeleteMapping("/{numero}")
    public ResponseEntity<Void> delete(@PathVariable Long numero) {
        planejamentoService.delete(numero);
        return ResponseEntity.noContent().build();
    }
}
