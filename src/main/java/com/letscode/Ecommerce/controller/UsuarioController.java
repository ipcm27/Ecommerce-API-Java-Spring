package com.letscode.Ecommerce.controller;

import java.time.Month;
import java.util.List;



import com.letscode.Ecommerce.model.Usuario;
import com.letscode.Ecommerce.repository.UsuarioRepository;
import com.letscode.Ecommerce.service.UsuarioService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@AllArgsConstructor
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @GetMapping(path="/")
    public ResponseEntity<List<Usuario>> list(@RequestParam(name = "partName",required = false) String partName){List<Usuario> produtos = usuarioService.list(partName);
        return new ResponseEntity<>(produtos,HttpStatus.OK);
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<Usuario> listById(@RequestParam(name = "id") Long id){
        Usuario usuario = usuarioService.listById(id);
        if(usuario ==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(usuario,HttpStatus.OK);
        }
    }

   @PostMapping(path="/")
  public ResponseEntity<Usuario> create(@RequestBody Usuario usuario){
        try{Usuario usuarioSalvo = usuarioService.create(usuario);
        return new ResponseEntity<>(usuarioSalvo,HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path="/{usuario_id}")
    public ResponseEntity<Usuario> update(@RequestBody Usuario usuario, @RequestParam(name = "usuario_id") Long id){
        try{
            Usuario usuarioSalvo = usuarioService.update(usuario);
            return new ResponseEntity<>(usuarioSalvo,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path="/{usuario_id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "usuario_id") Long id){
        try{
            usuarioService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll(){
        try{
            usuarioService.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    // @Bean
    // CommandLineRunner initDatabase(UsuarioRepository produtoRepository){
    //     return args ->{
    //         produtoRepository.deleteAll();

    //        Usuario adm = new Usuario();
            
    //        adm.setNome("Adm");
         
    //         produtoRepository.save(adm);
    //     };
    // }
    
}
