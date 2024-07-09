package com.davi.bytecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {
    private Long id;
    private Long clienteId;
    private LocalDate dataPedido;
    private List<ItemPedidoDTO> itensPedido = new ArrayList<>();
    private BigDecimal valorTotalPedido;
}
