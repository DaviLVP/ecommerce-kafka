package com.davi.bytecommerce.controller;

import com.davi.bytecommerce.dto.EstoqueDTO;
import com.davi.bytecommerce.dto.ItemPedidoDTO;
import com.davi.bytecommerce.dto.PedidoDTO;
import com.davi.bytecommerce.service.EstoqueService;
import com.davi.bytecommerce.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor

public class EstoqueController {
    private final EstoqueService estoqueService;

    @PostMapping("/cadastrar-produto/{produtoId}")
    public ResponseEntity<EstoqueDTO> cadastrarProduto(@PathVariable Long produtoId,
                                                      @RequestParam(required = false) Integer quantidadeInicial) {
        EstoqueDTO estoqueDTO = estoqueService.cadastrarProduto(produtoId, quantidadeInicial);
        return ResponseEntity.ok(estoqueDTO);

    }
}