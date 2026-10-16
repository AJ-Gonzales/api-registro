package com.github.AJ_Gonzales.api_registro_anna_557473_m1.controller;

import com.github.AJ_Gonzales.api_registro_anna_557473_m1.dto.RegistroRequestDto;
import com.github.AJ_Gonzales.api_registro_anna_557473_m1.dto.RegistroResponseDto;
import com.github.AJ_Gonzales.api_registro_anna_557473_m1.service.RegistroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/registros")
public class RegistroController {

    @Autowired
    private RegistroService registroService;

    @GetMapping
    public ResponseEntity<List<RegistroResponseDto>> getAllRegistros() {

        List<RegistroResponseDto> registros = registroService.findAllRegistros();

        return ResponseEntity.ok(registros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroResponseDto> getRegistroById(@PathVariable Long id) {

        RegistroResponseDto categoriaDto = registroService.findRegistroById(id);
        return ResponseEntity.ok(categoriaDto);

    }

    @PostMapping
    public ResponseEntity<RegistroResponseDto> createRegistro(@RequestBody
                                                                    @Valid
                                                               RegistroRequestDto inputDTO) {

        RegistroResponseDto registroDto = registroService.saveRegistro(inputDTO);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(registroDto.getRegistroId())
                .toUri();

        return ResponseEntity.created(uri).body(registroDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegistroResponseDto> updateRegistro(@PathVariable Long id,
                                                               @Valid @RequestBody
                                                                RegistroRequestDto inputDTO) {

        RegistroResponseDto registroDto = registroService.updateRegistro(id, inputDTO);
        return ResponseEntity.ok(registroDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegistro(@PathVariable Long id) {

        registroService.deleteRegistroById(id);

        return ResponseEntity.noContent().build();
    }
}
