package com.generation.crud_farmacia.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "category")
@Getter @Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank(message = "O atributo nome é obrigatório")
    private String name;

    @NotBlank(message = "O atributo descrição é obrigatorio")
    private String description;
}
