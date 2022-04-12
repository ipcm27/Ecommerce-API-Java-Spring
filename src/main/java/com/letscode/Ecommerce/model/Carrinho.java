package com.letscode.Ecommerce.model;

import java.util.Date;

import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class Carrinho {

    @EmbeddedId
    private ItemCarrinhoId idComposto;

    @Id
    private Long id;

    @Column
    private Date dataCriacao;

    @Column
    private Date dataAletaracao;

    @Column
    @OneToMany()
    private List<Produto> produtos;

    // private Usuario usuario;
}
