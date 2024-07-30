package com.davi.bytebank.service;

import com.davi.bytebank.dto.OperacaoResponse;
import com.davi.bytebank.dto.TipoOperacao;
import com.davi.bytebank.exception.BussinessException;
import com.davi.bytebank.exception.ResourceNotFoundException;
import com.davi.bytebank.model.Cliente;
import com.davi.bytebank.model.Conta;
import com.davi.bytebank.repository.ClienteRepository;
import com.davi.bytebank.repository.ContaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ContaService {
    private final ClienteRepository clienteRepository;
    private final ContaRepository contaRepository;


    public OperacaoResponse sacar(Long clienteId, BigDecimal valor) {
      Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(() ->
        new ResourceNotFoundException(String.format("\"Cliente id: %d não encontrado\",clienteId")));
      Conta conta = cliente.getConta();
      if (valor.compareTo(conta.getSaldo()) > 0 ) {
            throw new BussinessException("Saldo indisponivel");
        }

      conta.setSaldo(conta.getSaldo().subtract(valor));
      contaRepository.save(conta);

      OperacaoResponse response = new OperacaoResponse();
      response.setOperacao(TipoOperacao.SAQUE);
      response.setMensagem("Saque realizado com sucesso");
      return response;
    }

    public OperacaoResponse depositar(Long clienteId, BigDecimal valor) {

        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(() ->
                new ResourceNotFoundException(String.format("\"Cliente id: %d não encontrado\",clienteId")));
        Conta conta = cliente.getConta();
        conta.setSaldo(conta.getSaldo().add(valor));
        contaRepository.save(conta);

        OperacaoResponse response = new OperacaoResponse();
        response.setOperacao(TipoOperacao.DEPOSITO);
        response.setMensagem("Deposito Realizado com Sucesso");
        return response;
    }
}
