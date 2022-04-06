package com.letscode.Ecommerce.repository;

import com.letscode.Ecommerce.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario, Long>{
    
}
