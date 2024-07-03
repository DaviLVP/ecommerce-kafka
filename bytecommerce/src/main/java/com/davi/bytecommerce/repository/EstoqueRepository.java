package com.davi.bytecommerce.repository;

import com.davi.bytecommerce.model.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque,Long> {
    Optional<Estoque> findByProdutoId(Long produtoId);
}
