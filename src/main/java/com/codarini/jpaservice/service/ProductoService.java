package com.codarini.jpaservice.service;


import com.codarini.jpaservice.model.ProductoModel;
import com.codarini.jpaservice.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private ProductoModel create;
    @Autowired
    public ProductoRepository productoRepository;

    public List<ProductoModel> findAll() {
        return this.productoRepository.findAll();
    }

    public Optional<ProductoModel> findById(int idProducto){
        return this.productoRepository.findById(idProducto);
    }

    public  List<ProductoModel> findByNombre(String nombre){ return  this.productoRepository.findByNombre(nombre);}

    public List<ProductoModel> findByStock(int stock){return this.productoRepository.findByStock(stock);}

    public List<ProductoModel> findByPrecio(int precio){return  this.productoRepository.findByPrecio(precio);}

    public ProductoModel create (ProductoModel nuevoProducto){
        return this.productoRepository.save(nuevoProducto);
    }

    public ProductoModel update (ProductoModel producto , int id) throws ResourceNotFoundException {
        Optional<ProductoModel> productoBD = this.productoRepository.findById(id);
        boolean ExceptionEnProducto;
        if(productoBD.isPresent()){
            ProductoModel p = productoBD.get();
            p.setNombre(producto.getNombre());
            p.setPrecio(producto.getPrecio());
            p.setStock(producto.getStock());
            return this.productoRepository.save(p);

        }else{

            throw new ResourceNotFoundException("El producto no existe");
        }
    }
}
