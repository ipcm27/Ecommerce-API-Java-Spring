// package com.letscode.Ecommerce.model;

// import java.util.Date;

// import java.util.List;

// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.Id;
// import javax.persistence.OneToMany;

// import lombok.AllArgsConstructor;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.Setter;

// @Getter
// @Setter
// @AllArgsConstructor
// @NoArgsConstructor
// @Entity
// public class Carrinho {

//     @Id
//     private Long id;

//     @Column
//     private Date dataCriacao;

//     @Column
//     private Date dataAletaracao;

//     @Column
//     @OneToMany(mappedBy = "carrinho")
//     private List<Produto> produtos;
// }
