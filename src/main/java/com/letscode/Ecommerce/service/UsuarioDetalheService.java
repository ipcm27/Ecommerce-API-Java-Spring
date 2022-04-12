package com.letscode.Ecommerce.service;

import com.letscode.Ecommerce.model.entity.UsuarioEntity;
import com.letscode.Ecommerce.repository.UsuarioAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;

public class UsuarioDetalheService implements UserDetailsService {

    @Autowired
    private UsuarioAuthRepository repository;

    @Override
    public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
        UsuarioEntity usuarioEntity = repository.findByUsuario(usuario)
                .orElseThrow(() -> new UsernameNotFoundException("" + usuario));

        return new User(usuario, usuarioEntity.getPassword(), new ArrayList<>());
        }
    }
}
