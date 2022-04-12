package com.letscode.Ecommerce.model;

import java.time.ZonedDateTime;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;


import lombok.Builder;
import lombok.Data;


@Entity(name = "USUARIO")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Usuario{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "DATA_CRIACAO")
    private ZonedDateTime  dataCriacao;

    @Column(name = "DATA_ATUALIZACAO")
    private ZonedDateTime dataAtualizacao;

    public Usuario(String nome,String nickame ) {

    this.nome = nome;
    this.nickname = nickame;
   
    this.dataCriacao = ZonedDateTime.now();
    this.dataAtualizacao = ZonedDateTime.now();
    }
}