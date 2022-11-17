package com.codarini.jpaservice.controller;


import com.codarini.jpaservice.model.ClienteModel;
import com.codarini.jpaservice.service.ClienteService;
import com.codarini.jpaservice.service.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/Cliente")

public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/")
    public ResponseEntity<Object>findaAll(){
        return new ResponseEntity<Object>(this.clienteService.findAll() , HttpStatus.OK);
    }

    @GetMapping("/EncontrarPorId")
    public ResponseEntity<Object> findById(@RequestParam int idCliente){
        return new ResponseEntity<Object>(this.clienteService.findById(idCliente), HttpStatus.OK);
    }

    @GetMapping("/EncontrarPorNombre")
    public ResponseEntity<Object> findByNombre(@RequestParam String nombre){
       return new ResponseEntity<Object>(this.clienteService.findByNombre(nombre), HttpStatus.OK);
   }

   @GetMapping("/EncontrarPorApellido")
    public ResponseEntity<Object> findByApellido(@RequestParam String apellido){
        return new ResponseEntity<Object>(this.clienteService.findByApellido(apellido),HttpStatus.OK);
    }

    @GetMapping("/EncontrarPorDni")
    public ResponseEntity<Object> findByDni(@RequestParam int dni){
        return new ResponseEntity<Object>(this.clienteService.findByDni(dni), HttpStatus.OK);
    }

    @PostMapping("/Crear")
    public ResponseEntity<ClienteModel> create(@RequestBody ClienteModel cliente){
        return new ResponseEntity<>(this.clienteService.create(cliente), HttpStatus.OK);
    }

    @PutMapping("/Actualizar{id}")
    public ResponseEntity<ClienteModel> update(@RequestBody ClienteModel cliente,@PathVariable int id) throws ResourceNotFoundException {
        return new ResponseEntity<>(this.clienteService.update(cliente , id),HttpStatus.OK);
    }


}


