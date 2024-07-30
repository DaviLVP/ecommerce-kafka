package com.davi.bytebank.integration;

import com.davi.bytebank.dto.PedidoDTO;
import com.davi.bytebank.service.PedidoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PedidosConsumer {
    private final ObjectMapper objectMapper;
    private final PedidoService pedidoService;


    @KafkaListener(topics = "analise-pedidos-entrada-v1", groupId = "my-group-testt")
    public void listen(String message) throws JsonProcessingException {
        PedidoDTO dto = objectMapper.readValue(message, PedidoDTO.class);
        System.out.println(dto.getItensPedido());
        pedidoService.processarPedido(dto);

    }
}
