package com.davi.bytecommerce.integration;


import com.davi.bytecommerce.dto.PedidoDTO;
import com.davi.bytecommerce.service.PedidoService;
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


    @KafkaListener(topics = "analise-pedidos-saida-v1", groupId = "my-group-testt")
    public void listen(String message) throws JsonProcessingException {
        PedidoDTO dto = objectMapper.readValue(message, PedidoDTO.class);
        System.out.println(dto.getItensPedido());
        pedidoService.concluirPedido(dto);

    }
}
