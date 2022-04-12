package com.letscode.Ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.letscode.Ecommerce.model.enums.produtoStatus;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity(name = "PRODUTO")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CODIGO")
    private UUID codigo;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "VALOR")
    private BigDecimal valor;

    @Column(name = "CODIGO_BARRA")
    private String codigoBarra;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private produtoStatus status;

    @Column(name = "PESO")
    private Integer peso;

    @Column(name = "PESO_UNIDADE_MEDIDA")
    private String pesoUnidadeMedida;

    @Column(name = "DATA_CRIACAO")
    private ZonedDateTime dataCriacao;

    @Column(name = "DATA_ATUALIZACAO")
    private ZonedDateTime dataAtualizacao;


    public Produto(String nome, String descricao,
                         BigDecimal valor, String codigoBarra,
                            Integer peso,
                         String pesoUnidadeMedida) {
        this.codigo = UUID.randomUUID();
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.codigoBarra = codigoBarra;
        this.status = produtoStatus.ATIVO;
    
        this.peso = peso;
        this.pesoUnidadeMedida = pesoUnidadeMedida;
        this.dataCriacao = ZonedDateTime.now();
        this.dataAtualizacao = ZonedDateTime.now();
    }
}
