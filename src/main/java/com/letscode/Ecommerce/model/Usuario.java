package com.letscode.Ecommerce.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Usuario{

    @Id
    private Long id;

    @Column
    private String nome;

    @Column
    private Date dataNascimento;

    @Column
    private Date dataCriacao;

    @Column
    private Date dataAletaracao;

}