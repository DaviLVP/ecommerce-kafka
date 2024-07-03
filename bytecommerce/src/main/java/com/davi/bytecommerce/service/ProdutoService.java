package com.davi.bytecommerce.service;

import com.davi.bytecommerce.dto.CategoriaDTO;
import com.davi.bytecommerce.dto.ProdutoDTO;
import com.davi.bytecommerce.exception.ResourceNotFoundException;
import com.davi.bytecommerce.model.Categoria;
import com.davi.bytecommerce.model.Produto;
import com.davi.bytecommerce.repository.ProdutoRepository;

import java.util.ArrayList;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository repository;
    private final CategoriaService categoriaService;
    private final ModelMapper modelMapper;

    public ProdutoDTO criar(ProdutoDTO dto) {
        if (verificarSeCategoriaExiste(dto.getCategoria().getId())){
            Produto entity = modelMapper.map(dto, Produto.class);
            return modelMapper.map(repository.save(entity),ProdutoDTO.class);

        }
        throw  new ResourceNotFoundException
                (String.format("Categoria id: %d não encontrada",dto.getCategoria().getId()));

    }

    public ProdutoDTO update(Long id, ProdutoDTO request) {
        Produto produto = repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(String.format("Produto id: %d não encontrada",id)));
        if(!verificarSeCategoriaExiste(request.getCategoria().getId())){
            produto.setCategoria(categoriaService.buscarPorId(request.getCategoria().getId()));
                throw new ResourceNotFoundException(String.format("Produto id: %d não encontrada",id));
        }

        produto.setNome(request.getNome());
        produto.setDescricao(request.getDescricao());
        produto.setPreco(request.getPreco());
        repository.save(produto);
        return modelMapper.map(produto, ProdutoDTO.class);
    }
    private boolean verificarSeCategoriaExiste(Long categoriaId){
        return categoriaService.existeCategoria(categoriaId);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<ProdutoDTO> buscarTodos() {
       List<Produto> produtos = repository.findAll();
       List<ProdutoDTO> produtosDTO = new ArrayList<>();
      for (Produto produto : produtos) {
          produtosDTO.add(modelMapper.map(produto, ProdutoDTO.class));

      }
        return produtosDTO;
    }

    public ProdutoDTO buscarPorId(@PathVariable Long id) {
        Produto produto = repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(String.format("Produto id: %d não encontrada",id)));
        return modelMapper.map(produto, ProdutoDTO.class);
    }
}
