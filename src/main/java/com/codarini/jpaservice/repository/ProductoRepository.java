package com.codarini.jpaservice.repository;

import com.codarini.jpaservice.model.ProductoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<ProductoModel,Integer> {


    List<ProductoModel> findByNombre(String nombreProducto);
    List<ProductoModel> findByStock(int stock);
    List<ProductoModel> findByPrecio(int precio);

}
