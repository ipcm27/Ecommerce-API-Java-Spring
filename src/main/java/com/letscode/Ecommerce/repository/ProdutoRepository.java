package com.letscode.Ecommerce.repository;

import java.util.List;

import com.letscode.Ecommerce.model.Produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    // List<Produto> findByNameContainingIgnoreCase(String nome);
    
}
