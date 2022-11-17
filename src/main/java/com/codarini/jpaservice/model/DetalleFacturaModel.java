package com.codarini.jpaservice.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "productos_ventas")

public class DetalleFacturaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "IdProducto_Venta")
    private int idProducto_Venta;

    @ManyToOne
    @JoinColumn(name = "IdVenta")
    private VentaModel idVentaModel;

    @ManyToOne
    @JoinColumn(name = "IdProducto")
    private ProductoModel idProductoModel;

    @Column(name="Cantidad_Producto")
    private int cantidad;



}
