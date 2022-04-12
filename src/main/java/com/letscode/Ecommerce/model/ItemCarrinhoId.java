package com.letscode.Ecommerce.model;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class ItemCarrinhoId implements Serializable {

    private long id_carrinho;
    private long id_produto;

    @ManyToOne
    @JoinColumn(name = "id_carrinho")
    private Carrinho carrinho;

    @ManyToOne
    @JoinColumn(name = "id_produto") // referencedColumnName = "coluna" (para usar outra coluna)
    private Produto produto;

}
