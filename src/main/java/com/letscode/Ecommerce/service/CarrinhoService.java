package com.letscode.Ecommerce.service;

import java.util.List;

import com.letscode.Ecommerce.model.Carrinho;
import com.letscode.Ecommerce.repository.CarrinhoRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class CarrinhoService {

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    public List<Carrinho> list(String nome){
        return carrinhoRepository.findAll();
    }
    
    public Carrinho listById(Long id){
        return carrinhoRepository.findById(id).orElse(null);  
    }
    
    public Carrinho create(Carrinho carrinho){
    return carrinhoRepository.save(carrinho);
    }
    
    public Carrinho update(Carrinho carrinho) {
        Long id = carrinho.getId();
        if(id == null || !carrinhoRepository.existsById(id)){
            return null;
        }else{
        return carrinhoRepository.save(carrinho);
        }
    }
    
    public void delete(Long id) {
        carrinhoRepository.deleteById(id);
    }
    
    public void deleteAll(){
        carrinhoRepository.deleteAll();
    }
    
}
