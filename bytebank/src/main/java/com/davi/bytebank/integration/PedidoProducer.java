package com.davi.bytebank.integration;



import com.davi.bytebank.dto.PedidoDTO;
import com.davi.bytebank.exception.JsonConverterException;
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

    private static final String TOPIC = "analise-pedidos-saida-v1";
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
