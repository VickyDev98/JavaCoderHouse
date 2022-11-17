package com.codarini.jpaservice.repository;

import com.codarini.jpaservice.model.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<ClienteModel,Integer> {


    List<ClienteModel> findByNombre(String nombreCliente);
    List<ClienteModel> findByApellido(String apellidoCliente);
    List<ClienteModel> findByDni(int dni);

}
