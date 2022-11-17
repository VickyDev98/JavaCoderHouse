package com.codarini.jpaservice.repository;

import com.codarini.jpaservice.model.ClienteModel;
import com.codarini.jpaservice.model.VentaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;


public interface VentaRepository extends JpaRepository<VentaModel, Integer> {
    List<VentaModel> findByFecha(LocalDate fecha);
    List<VentaModel> findByIdClienteModel(ClienteModel clienteModel);
    List<VentaModel> findByTotal(int total);
}
