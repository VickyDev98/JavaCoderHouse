package com.codarini.jpaservice.controller;

import com.codarini.jpaservice.model.ProductoModel;
import com.codarini.jpaservice.service.ProductoService;
import com.codarini.jpaservice.service.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Producto")

public class ProductoController{

    @Autowired
    private ProductoService productoService;

    @GetMapping("/")
    public ResponseEntity<Object> findAll(){
        return new ResponseEntity<Object>(this.productoService.findAll() , HttpStatus.OK);
    }

    @GetMapping("/EncontrarPorId")
    public ResponseEntity<Object> findById(@RequestParam int idProducto){
        return new ResponseEntity<Object>(this.productoService.findById(idProducto), HttpStatus.OK);
    }

    @GetMapping("/EncontrarPorNombre")
    public ResponseEntity<Object> findByNombre(@RequestParam String nombre){
        return new ResponseEntity<Object>(this.productoService.findByNombre(nombre), HttpStatus.OK);
    }

    @GetMapping("/EncontrarPorPrecio")
    public ResponseEntity<Object> findByPrecio(@RequestParam int precio){
        return new ResponseEntity<Object>(this.productoService.findByPrecio(precio),HttpStatus.OK);
    }

    @GetMapping("/EncontrarPorStock")
    public ResponseEntity<Object> findByStock(@RequestParam int stock){
        return new ResponseEntity<Object>(this.productoService.findByStock(stock), HttpStatus.OK);
    }

    @PostMapping("/Crear")
    public ResponseEntity<ProductoModel> create(@RequestBody ProductoModel producto){
        return new ResponseEntity<>(this.productoService.create(producto), HttpStatus.OK);
    }

    @PutMapping("/Actualizar{id}")
    public ResponseEntity<ProductoModel> update(@RequestBody ProductoModel producto,@PathVariable int id) throws ResourceNotFoundException {


        return new ResponseEntity<>(this.productoService.update(producto , id),HttpStatus.OK);
    }
}
