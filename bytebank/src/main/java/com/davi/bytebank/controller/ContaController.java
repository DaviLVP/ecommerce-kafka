package com.davi.bytebank.controller;

import com.davi.bytebank.dto.OperacaoResponse;
import com.davi.bytebank.service.ContaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/clientes/{clienteId}")
@RequiredArgsConstructor
public class ContaController {
    private final ContaService contaService;


    @PostMapping("/sacar")
    public ResponseEntity<OperacaoResponse> sacar(@PathVariable Long clienteId, @RequestParam BigDecimal valor) {
        return ResponseEntity.ok(contaService.sacar(clienteId, valor));


    }

    @PostMapping("/depositar")
    public ResponseEntity<OperacaoResponse> depositar(@PathVariable Long clienteId, @RequestParam BigDecimal valor) {
        return ResponseEntity.ok(contaService.depositar(clienteId,valor));
    }
}
