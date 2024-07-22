package com.generation.crud_farmacia.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.generation.crud_farmacia.utils.ValidationPrice;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Getter @Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotBlank(message = "O atributo nome é obrigatório")
    private String name;
    @NotBlank(message = "O atributo descrição é obrigatório")
    private String description;
    @ValidationPrice
    @NotNull(message = "O atributo preço é obrigatório")
    @Column(columnDefinition = "DECIMAL(10,2)")
    private BigDecimal price;
    private String image;
    @NotNull(message = "O estoque nome é obrigatório")
    private int stock;
    @ManyToOne
    @JsonIgnoreProperties("products")
    private Category category;
}
