package com.davi.bytebank.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String sobrenome;
    private LocalDate dataNascimento;
    private String cpf;
    @ManyToOne
    @JoinColumn(name ="endereco_id")
    private Endereco endereco;
    private String email;
    @OneToOne
    @JoinColumn(name ="conta_id")
    private Conta conta;

}