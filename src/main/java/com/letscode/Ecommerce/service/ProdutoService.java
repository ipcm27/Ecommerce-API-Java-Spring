package com.letscode.Ecommerce.service;

import java.util.List;

import com.letscode.Ecommerce.repository.ProdutoRepository;
import com.letscode.Ecommerce.model.Produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> list(String nome) {
        return produtoRepository.findAll();
    }
   
    public Produto listById(Long id) {
        return produtoRepository.findById(id).orElse(null);
    }

    public Produto listByCodigoBarra(String codigoBarra){
        return produtoRepository.findByCodigoBarra(codigoBarra);
}


    public Produto create(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto update(Produto produto) {
        Long id = produto.getId();
        if(id == null || !produtoRepository.existsById(id)){
            return null;
        }else{
        return produtoRepository.save(produto);
        }
    }

    public void delete(Long id) {
        produtoRepository.deleteById(id);
    }

    public void deleteAll(){
        produtoRepository.deleteAll();
    }
    
}
