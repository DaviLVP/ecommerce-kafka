package com.davi.bytecommerce.controller;

import com.davi.bytecommerce.dto.ItemPedidoDTO;
import com.davi.bytecommerce.dto.PedidoDTO;
import com.davi.bytecommerce.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/fazer-pedido")
public class PedidoController {
    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoDTO> fazerPedido(@RequestBody List<ItemPedidoDTO> itensPedidosRequest) {
        return ResponseEntity.ok(pedidoService.criarPedido(itensPedidosRequest));
    }
    
}
