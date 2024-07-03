package com.davi.bytecommerce.service;

import com.davi.bytecommerce.dto.EstoqueDTO;
import com.davi.bytecommerce.dto.ItemPedidoDTO;
import com.davi.bytecommerce.exception.BussinessException;
import com.davi.bytecommerce.exception.ResourceNotFoundException;
import com.davi.bytecommerce.model.Estoque;
import com.davi.bytecommerce.model.Produto;
import com.davi.bytecommerce.repository.EstoqueRepository;
import com.davi.bytecommerce.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EstoqueService {
    private final EstoqueRepository estoqueRepository;
    private final ProdutoRepository produtoRepository;
    private final ModelMapper modelMapper;

    public EstoqueDTO cadastrarProduto(Long produtoId, Integer quantidadeInicial) {
        Produto produto = produtoRepository.findById(produtoId).orElseThrow(() ->
                new ResourceNotFoundException(String.format("Produto id: %d não encontrado no estoque", produtoId)));

        Optional<Estoque> estoqueOptional = estoqueRepository.findByProdutoId(produtoId);
        if (estoqueOptional.isPresent()) {
            throw new BussinessException(String.format("Produto id: %d já cadastrado no estoque", produtoId));
        } else {
            Estoque estoque = new Estoque();
            if (quantidadeInicial != null) {
                estoque.setProduto(produto);
                estoque.setQuantidade(quantidadeInicial);
            }
            return modelMapper.map(estoqueRepository.save(estoque), EstoqueDTO.class);
        }
    }
}
