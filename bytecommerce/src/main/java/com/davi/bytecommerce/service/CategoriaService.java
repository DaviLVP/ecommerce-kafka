package com.davi.bytecommerce.service;

import com.davi.bytecommerce.dto.CategoriaDTO;
import com.davi.bytecommerce.model.Categoria;
import com.davi.bytecommerce.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {
    private final CategoriaRepository repository;
    private final ModelMapper modelMapper;

    public CategoriaDTO criar(CategoriaDTO request) {
        Categoria entity = modelMapper.map(request, Categoria.class);
        return modelMapper.map(repository.save(entity), CategoriaDTO.class);
    }

    public List<CategoriaDTO> buscarTodos() {
        List<Categoria> categorias = repository.findAll();
        List<CategoriaDTO> categoriasDTO = new ArrayList<>();
        for (Categoria categoria : categorias) {
            categoriasDTO.add(modelMapper.map(categoria, CategoriaDTO.class));
        }
        return categoriasDTO;
    }

    public CategoriaDTO update(Long id, CategoriaDTO request) {

        Categoria categoria = repository.findById(id).orElseThrow(NullPointerException::new);
        categoria.setNome(request.getNome());
        categoria = repository.save(categoria);
        return modelMapper.map(categoria, CategoriaDTO.class);
    }

    public void delete(Long id) {
        repository.deleteById(id);

    }
    public Categoria buscarPorId(Long id){
        return repository.findById(id).orElseThrow(NullPointerException::new);

    }
    public boolean existeCategoria(Long id){
       return repository.existsById(id);
    }
}
