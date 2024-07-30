package com.davi.bytebank.service;


import com.davi.bytebank.dto.ClienteRequest;
import com.davi.bytebank.model.Cliente;
import com.davi.bytebank.model.Conta;
import com.davi.bytebank.repository.ClienteRepository;
import com.davi.bytebank.repository.ContaRepository;
import com.davi.bytebank.repository.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;


@Service
@RequiredArgsConstructor
public class ClienteService {
 private final ModelMapper modelMapper;
 private final ClienteRepository clienteRepository;
 private final ContaRepository contaRepository;
 private final EnderecoRepository enderecoRepository;

    public ClienteRequest criar(ClienteRequest dto) {

        Cliente cliente = modelMapper.map(dto, Cliente.class);
        Conta conta = new Conta();
        conta.setSaldo(BigDecimal.ZERO);
        conta.setDataCriacao(LocalDate.now());
        conta.setNumero(gerarNumeroConta(cliente.getCpf()));
        cliente.setConta(conta);

        cliente.setEndereco(enderecoRepository.save(cliente.getEndereco()));
        contaRepository.save(conta);
        clienteRepository.save(cliente);

        return modelMapper.map(cliente,ClienteRequest.class);
    }

    private String gerarNumeroConta(String cpf) {
        String inicioCpf = cpf.substring(0,3);

        LocalDate hoje = LocalDate.now();
        String hojeString = hoje.toString();
        hojeString=  hojeString.replaceAll("-","");

        int digito = (int) (Math.random() * 10);
        return inicioCpf + hojeString + "-" + digito;

    }
}
