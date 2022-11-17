package com.codarini.jpaservice.repository;

import com.codarini.jpaservice.model.DetalleFacturaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetalleFacturaRepository extends JpaRepository<DetalleFacturaModel, Long> {
   // List<Producto_VentaModel> findByIdProducto(int idProducto);
    //List<Producto_VentaModel> findByIdVenta(int idVenta);
    List<DetalleFacturaModel> findByCantidad(int cantidad);

}
