package com.codarini.jpaservice.service;


import ch.qos.logback.core.net.server.Client;
import com.codarini.jpaservice.model.ClienteModel;
import com.codarini.jpaservice.model.DetalleFacturaModel;
import com.codarini.jpaservice.model.VentaModel;
import com.codarini.jpaservice.repository.ClienteRepository;
import com.codarini.jpaservice.repository.DetalleFacturaRepository;
import com.codarini.jpaservice.repository.ProductoRepository;
import com.codarini.jpaservice.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class VentaService {

    private VentaModel create;
    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private DetalleFacturaRepository detalleFacturaRepository;

    public List<VentaModel> findAll() {
        return this.ventaRepository.findAll();
    }

    public Optional<VentaModel> findById(int idVenta){
        return this.ventaRepository.findById(idVenta);
    }

    public  List<VentaModel> findByFecha(LocalDate fecha){ return  this.ventaRepository.findByFecha(fecha);}

    public List<VentaModel> findByIdClienteModel(ClienteModel clienteModel){return this.ventaRepository.findByIdClienteModel(clienteModel);}

    public List<VentaModel> findByTotal(int total){return  this.ventaRepository.findByTotal(total);}

    public VentaModel create (VentaModel nuevaVenta) throws ResourceNotFoundException {
        int idCliente = nuevaVenta.getIdClienteModel().getIdCliente();
        if(!clienteRepository.existsById(idCliente)){
            throw new ResourceNotFoundException("El cliente no existe");
        }

        VentaModel ventaModel;
        ventaModel = ventaRepository.save(nuevaVenta);
        if(ObjectUtils.isEmpty(ventaModel)){
            throw new ResourceNotFoundException("La venta no se pudo insertar");
        }

        return ventaModel;
    }

    public VentaModel update (VentaModel venta , int id){
        Optional<VentaModel> ventaBD = this.ventaRepository.findById(id);
        if(ventaBD.isPresent()){
            VentaModel v = ventaBD.get();
            v.setFecha(venta.getFecha());
            v.setIdClienteModel(venta.getIdClienteModel());
            v.setTotal(venta.getTotal());
            return this.ventaRepository.save(v);
        }else{
            return null;
        }
    }

}
