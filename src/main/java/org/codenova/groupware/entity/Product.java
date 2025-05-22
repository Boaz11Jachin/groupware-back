package org.codenova.groupware.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotNull
    private String name;


    @NotNull
    private int price;


    @NotNull
    private int stock;


    @NotNull
    private LocalDateTime createdAt;


}