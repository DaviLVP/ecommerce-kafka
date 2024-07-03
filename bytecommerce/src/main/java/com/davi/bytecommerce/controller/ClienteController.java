package com.davi.bytecommerce.controller;

import com.davi.bytecommerce.dto.CategoriaDTO;
import com.davi.bytecommerce.dto.ClienteDTO;
import com.davi.bytecommerce.dto.EnderecoDTO;
import com.davi.bytecommerce.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService service;

    @PostMapping
    public ResponseEntity<ClienteDTO> criar(@RequestBody ClienteDTO request) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(request.getId()).toUri();
        return ResponseEntity.created(uri).body(service.criar(request));
    }
    @GetMapping
    public ResponseEntity <List<ClienteDTO>>buscarTodos(){
        return ResponseEntity.ok(service.buscarTodos());
    }
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable Long id, @RequestBody ClienteDTO request) {
        return ResponseEntity.ok(service.update(id,request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity <ClienteDTO>buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(service.buscarPorId(id));
    }
}
