package com.letscode.Ecommerce.service;

import java.util.List;

import com.letscode.Ecommerce.repository.UsuarioRepository;
import com.letscode.Ecommerce.model.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    //CRUD

@Autowired
private UsuarioRepository usuarioRepository;

public List<Usuario> list(String nome){
    return usuarioRepository.findAll();
}

public Usuario listById(Long id){
    return usuarioRepository.findById(id).orElse(null);  
}

public Usuario create(Usuario usuario){
return usuarioRepository.save(usuario);
}

public Usuario update(Usuario usuario) {
    Long id = usuario.getId();
    if(id == null || !usuarioRepository.existsById(id)){
        return null;
    }else{
    return usuarioRepository.save(usuario);
    }
}

public void delete(Long id) {
    usuarioRepository.deleteById(id);
}

public void deleteAll(){
    usuarioRepository.deleteAll();
}


}