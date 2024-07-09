package com.davi.bytecommerce.controller;

import com.davi.bytecommerce.dto.ItemPedidoDTO;
import com.davi.bytecommerce.dto.PedidoDTO;
import com.davi.bytecommerce.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping
public class PedidoController {
    private final PedidoService pedidoService;

    @PostMapping("/clientes/{clienteId}/fazer-pedido")
    public ResponseEntity<PedidoDTO> fazerPedido(@PathVariable Long clienteId, @RequestBody List<ItemPedidoDTO> itensPedidosRequest) {
        return ResponseEntity.ok(pedidoService.criarPedido(clienteId, itensPedidosRequest));
    }

}
