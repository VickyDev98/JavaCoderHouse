package com.codarini.jpaservice.model;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "productos")

public class ProductoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProducto;
    @Column
    private String nombre;
    @Column
    private double precio;
    @Column
    private int stock;


}
