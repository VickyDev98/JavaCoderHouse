package com.codarini.jpaservice.controller;


import com.codarini.jpaservice.model.ClienteModel;
import com.codarini.jpaservice.model.DetalleFacturaModel;
import com.codarini.jpaservice.model.VentaModel;

import com.codarini.jpaservice.service.ResourceNotFoundException;
import com.codarini.jpaservice.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/Ventas")

public class VentaController {
    @Autowired
    private VentaService ventaService;

    @GetMapping("/")
    public ResponseEntity<Object> findaAll(){
        return new ResponseEntity<Object>(this.ventaService.findAll() , HttpStatus.OK);
    }

    @GetMapping("/EncontrarPorId")
    public ResponseEntity<Object> findById(@RequestParam int idVenta){
        return new ResponseEntity<Object>(this.ventaService.findById(idVenta), HttpStatus.OK);
    }

    @GetMapping("/EncontrarPorFecha")
    public ResponseEntity<Object> findByFecha(@RequestParam LocalDate fecha){
        return new ResponseEntity<Object>(this.ventaService.findByFecha(fecha), HttpStatus.OK);
    }

    @GetMapping("/EncontrarPorIdCliente")
    public ResponseEntity<Object> findByIdCliente(@RequestBody ClienteModel clienteModel){
        return new ResponseEntity<Object>(this.ventaService.findByIdClienteModel(clienteModel),HttpStatus.OK);
    }

    @GetMapping("/EncontrarPorTotal")
    public ResponseEntity<Object> findByTotal(@RequestParam int total){
        return new ResponseEntity<Object>(this.ventaService.findByTotal(total), HttpStatus.OK);
    }

    @PostMapping("/Crear")
    public ResponseEntity<VentaModel> create(@RequestBody VentaModel venta, DetalleFacturaModel detalleFactura) throws ResourceNotFoundException {
        return new ResponseEntity<>(this.ventaService.create(venta,detalleFactura), HttpStatus.OK);
    }

    @PutMapping("/Actualizar{id}")
    public ResponseEntity<VentaModel> update(@RequestBody VentaModel venta, @PathVariable int id){
        return new ResponseEntity<>(this.ventaService.update(venta , id),HttpStatus.OK);
    }


}
