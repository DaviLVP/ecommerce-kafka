package com.davi.bytebank.controller;

import com.davi.bytebank.dto.ClienteDTO;
import com.davi.bytebank.dto.ClienteRequest;
import com.davi.bytebank.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {
   private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteRequest> criar(@RequestBody ClienteRequest request){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(request.getId()).toUri();
        return ResponseEntity.created(uri).body(clienteService.criar(request));

    }
}
