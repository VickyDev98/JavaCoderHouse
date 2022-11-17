package com.codarini.jpaservice.service;

import com.codarini.jpaservice.model.DetalleFacturaModel;
import com.codarini.jpaservice.model.ProductoModel;
import com.codarini.jpaservice.model.VentaModel;
import com.codarini.jpaservice.repository.DetalleFacturaRepository;
import com.codarini.jpaservice.repository.ProductoRepository;
import com.codarini.jpaservice.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;
@Service
public class DetalleFacturaService {
    private DetalleFacturaModel create;
    @Autowired
    private DetalleFacturaRepository detalleFacturaRepository;
    @Autowired
    private VentaRepository ventaRepository;
    @Autowired
    private ProductoRepository productoRepository;

    public List<DetalleFacturaModel> findAll() {
        return this.detalleFacturaRepository.findAll();
    }

    public Optional<DetalleFacturaModel> findById(int idProducto_Venta){
        return this.detalleFacturaRepository.findById((long) idProducto_Venta);
    }

    //public  List<Producto_VentaModel> findByIdProducto(int idProducto){ return  this.producto_ventaRepository.findByIdProducto(idProducto);}

    //public List<Producto_VentaModel> findByIdVenta(int idVenta){return this.producto_ventaRepository.findByIdVenta(idVenta);}

    public List<DetalleFacturaModel> findByCantidad(int cantidad){return  this.detalleFacturaRepository.findByCantidad(cantidad);}

    public DetalleFacturaModel create (DetalleFacturaModel nuevoDetalleFactura) throws ResourceNotFoundException {
        if(!ventaRepository.existsById(nuevoDetalleFactura.getIdVentaModel().getIdVenta())){
            throw new ResourceNotFoundException("");
        }

        Optional<ProductoModel> productoModelOptional = productoRepository.findById(nuevoDetalleFactura.getIdProductoModel().getIdProducto());
        ProductoModel productoModel = new ProductoModel();
        if(productoModelOptional.isPresent()){

            productoModel = productoModelOptional.get();

            int stockRestante = productoModel.getStock();
            int stockDeducir = nuevoDetalleFactura.getCantidad();

            if(stockRestante >= stockDeducir){
                productoModel.setStock(stockRestante - stockDeducir);
                productoRepository.save(productoModel);
            }else{
                ventaRepository.deleteById(nuevoDetalleFactura.getIdVentaModel().getIdVenta());
                throw new ResourceNotFoundException("");
            }
        }else{
            ventaRepository.deleteById(nuevoDetalleFactura.getIdVentaModel().getIdVenta());
            throw new ResourceNotFoundException("");
        }

        DetalleFacturaModel detalleFacturaInsertada = detalleFacturaRepository.save(nuevoDetalleFactura);
        if(ObjectUtils.isEmpty(detalleFacturaInsertada)){
            ventaRepository.deleteById(nuevoDetalleFactura.getIdVentaModel().getIdVenta());
            throw new ResourceNotFoundException("");
        }

        return detalleFacturaInsertada;
    }

    public DetalleFacturaModel update (DetalleFacturaModel producto_venta , int id){
        Optional<DetalleFacturaModel> producto_ventaBD = this.detalleFacturaRepository.findById((long)id);
        if(producto_ventaBD.isPresent()){
            DetalleFacturaModel pv = producto_ventaBD.get();
            pv.setCantidad(producto_venta.getCantidad());
            pv.setIdProductoModel(producto_venta.getIdProductoModel());
            pv.setIdVentaModel(producto_venta.getIdVentaModel());
            return this.detalleFacturaRepository.save(pv);
        }else{
            return null;
        }
    }


}
