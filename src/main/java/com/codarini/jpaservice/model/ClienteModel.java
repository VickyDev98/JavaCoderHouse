package com.codarini.jpaservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "clientes")

public class ClienteModel {
   // @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="IdCliente")
    private int idCliente;
    @Column(name="Nombre")
    private String nombre;
    @Column(name="Apellido")
    private String apellido;
    @Column(name="Dni")
    private int dni;
}
