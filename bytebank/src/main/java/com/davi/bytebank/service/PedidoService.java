package com.davi.bytebank.service;

import com.davi.bytebank.dto.PedidoDTO;
import com.davi.bytebank.dto.StatusPedido;
import com.davi.bytebank.exception.BussinessException;
import com.davi.bytebank.exception.ResourceNotFoundException;
import com.davi.bytebank.integration.PedidoProducer;
import com.davi.bytebank.model.Cliente;
import com.davi.bytebank.model.Conta;
import com.davi.bytebank.repository.ClienteRepository;
import com.davi.bytebank.repository.ContaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PedidoService {
    private final ClienteRepository clienteRepository;
    private final ContaRepository contaRepository;
    private final PedidoProducer pedidoProducer;
    public void processarPedido(PedidoDTO dto){
        Optional<Cliente> cliente = clienteRepository.findByCpf(dto.getClienteCpf());
        if (cliente.isPresent()) {
           if (cliente.get().getConta().getSaldo().compareTo(dto.getValorTotalPedido()) >= 0) {
                Conta conta = cliente.get().getConta();
                conta.getSaldo().subtract(dto.getValorTotalPedido());
                contaRepository.save(conta);
                dto.setStatus(StatusPedido.SUCESSO_VALIDACAO);
            }else{
               dto.setStatus(StatusPedido.ERRO_VALIDACAO);
               log.error("Saldo insuficiente");
           }

        }else {
            dto.setStatus(StatusPedido.ERRO_VALIDACAO);
            log.error((String.format("Cliente documento: %s não encontrado", dto.getClienteCpf())));
        }

        pedidoProducer.sendMessage(dto);
    }
}
