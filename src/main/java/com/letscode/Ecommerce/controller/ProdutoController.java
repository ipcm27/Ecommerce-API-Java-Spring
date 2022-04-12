package com.letscode.Ecommerce.controller;

import java.util.List;

import com.letscode.Ecommerce.repository.ProdutoRepository;
import com.letscode.Ecommerce.model.Produto;
import com.letscode.Ecommerce.service.ProdutoService;

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
@RequestMapping("/api/produtos")
@AllArgsConstructor
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;


    @GetMapping(path="/")
    public ResponseEntity<List<Produto>> list(@RequestParam(name = "partName",required = false) String partName){List<Produto> produtos = produtoService.list(partName);
        return new ResponseEntity<>(produtos,HttpStatus.OK);
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<Produto> listById(@RequestParam(name = "id") Long id){
        Produto produto = produtoService.listById(id);
        if(produto ==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(produto,HttpStatus.OK);
        }
    }

    @GetMapping("/codigo/{codigoBarra}")
    public ResponseEntity<Produto> getByCodigoBarra(
            @PathVariable(name = "codigoBarra") String codigoBarra){

        Produto produto = produtoService.listByCodigoBarra(codigoBarra);
        return ResponseEntity.ok(produto);
    }

   @PostMapping(path="/")
  public ResponseEntity<Produto> create(@RequestBody Produto produto){
        try{Produto produtoSalvo = produtoService.create(produto);
        return new ResponseEntity<>(produtoSalvo,HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path="/{produto_id}")
    public ResponseEntity<Produto> update(@RequestBody Produto produto, @RequestParam(name = "produto_id") Long id){
        try{
            Produto produtoSalvo = produtoService.update(produto);
            return new ResponseEntity<>(produtoSalvo,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path="/{produto_id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "produto_id") Long id){
        try{
            produtoService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll(){
        try{
            produtoService.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    // @Bean
    // CommandLineRunner initDatabase(ProdutoRepository produtoRepository){
    //     return args ->{
    //         produtoRepository.deleteAll();

    //         Produto iphone = new Produto();
            
    //         iphone.setNome("Iphone");
    //         iphone.setDescricao("Iphone X");
        
            

    //         produtoRepository.save(iphone);
    //     };
    // }
    
}
