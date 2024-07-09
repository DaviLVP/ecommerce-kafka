package com.davi.bytecommerce.integration;


import com.davi.bytecommerce.dto.PedidoDTO;
import com.davi.bytecommerce.exception.JsonConverterException;
import com.davi.bytecommerce.model.Pedido;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PedidoProducer {

    private static final String TOPIC = "analise-pedido-entrada-V1";
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void sendMessage(PedidoDTO pedidoMessage) {
       log.info("produzindo mensagem");

       try{
           String message = objectMapper.writeValueAsString(pedidoMessage);
           kafkaTemplate.send(TOPIC, message);
       }catch (JsonProcessingException ex){
           throw new JsonConverterException("Nao foi possivel converter a mensagem", ex);
       }
    }
}
