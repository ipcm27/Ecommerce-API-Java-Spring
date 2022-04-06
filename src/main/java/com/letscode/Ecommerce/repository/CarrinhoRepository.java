package com.letscode.Ecommerce.repository;

import com.letscode.Ecommerce.model.Carrinho;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrinhoRepository extends JpaRepository<Carrinho,Long> {
    
}
