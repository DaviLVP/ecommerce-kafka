package com.davi.bytecommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name= "pedido_id")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name= "produto_id")
    private Produto produto;

    private int quantidade;

    private BigDecimal valorItens;

}
