package com.codarini.jpaservice.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "ventas")

public class VentaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idVenta;
    @Column
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "IdCliente")
    private ClienteModel idClienteModel;

    @Column
    private int total;



}

