package com.codarini.jpaservice.service;

import com.codarini.jpaservice.model.ClienteModel;
import com.codarini.jpaservice.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private ClienteModel create;
    @Autowired
    private ClienteRepository clienteRepository;

    public List<ClienteModel> findAll() {
        return this.clienteRepository.findAll();
    }

    public Optional<ClienteModel> findById(int idCliente){
        return this.clienteRepository.findById(idCliente);
    }

    public  List<ClienteModel> findByNombre(String nombre){ return  this.clienteRepository.findByNombre(nombre);}

    public List<ClienteModel> findByApellido(String apellido){return this.clienteRepository.findByApellido(apellido);}

    public List<ClienteModel> findByDni(int dni){return  this.clienteRepository.findByDni(dni);}

    public ClienteModel create (ClienteModel nuevoCliente){
        return this.clienteRepository.save(nuevoCliente);
    }

    public ClienteModel update (ClienteModel cliente , int id) throws ResourceNotFoundException {
        Optional<ClienteModel> clienteBD = this.clienteRepository.findById(id);
        if(clienteBD.isPresent()){
            ClienteModel c = clienteBD.get();
            c.setNombre(cliente.getNombre());
            c.setApellido(cliente.getApellido());
            c.setDni(cliente.getDni());
            return this.clienteRepository.save(c);
        }else{
            throw new ResourceNotFoundException("El cliente no existe");
        }
    }
}
