package com.letscode.Ecommerce.model;

import java.util.Date;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class Carrinho {

    @Id
    private Long id;

    @Column
    private Date dataCriacao;

    @Column
    private Date dataAletaracao;

    @Column
    @OneToMany()
    private List<Produto> produtos;
    // ADICIONAR PK COMPOSTA(USANDO ID DO CARRINHO E ID DO PRODUTO);

    // private Usuario usuario;
}
