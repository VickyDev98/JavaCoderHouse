package com.codarini.jpaservice.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "detalle_ventas")

public class DetalleFacturaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "IdDetalle_Venta")
    private int idDetalle_Venta;

    @ManyToOne
    @JoinColumn(name = "IdVenta")
    private VentaModel idVentaModel;

    @ManyToOne
    @JoinColumn(name = "IdProducto")
    private ProductoModel idProductoModel;

    @Column(name="Cantidad_Producto")
    private int cantidad;



}
