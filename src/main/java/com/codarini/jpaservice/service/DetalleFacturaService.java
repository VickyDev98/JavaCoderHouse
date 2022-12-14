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

    public Optional<DetalleFacturaModel> findById(int idDetalle_Venta){
        return this.detalleFacturaRepository.findById(idDetalle_Venta);
    }


    public List<DetalleFacturaModel> findByCantidad(int cantidad){return  this.detalleFacturaRepository.findByCantidad(cantidad);}

    public DetalleFacturaModel create (DetalleFacturaModel nuevoDetalleFactura) throws ResourceNotFoundException {

        int cantidad =0;
        double precio = 0;
        Optional<VentaModel> ventaModelOptional = ventaRepository.findById(nuevoDetalleFactura.getIdVentaModel().getIdVenta());
        VentaModel ventaModel = new VentaModel();
        if(ventaModelOptional.isPresent()){
            ventaModel = ventaModelOptional.get();
            cantidad = nuevoDetalleFactura.getCantidad();
        }else{
            throw new ResourceNotFoundException("");
        }




        Optional<ProductoModel> productoModelOptional = productoRepository.findById(nuevoDetalleFactura.getIdProductoModel().getIdProducto());
        ProductoModel productoModel = new ProductoModel();

        if(productoModelOptional.isPresent()){

            productoModel = productoModelOptional.get();

            precio = productoModel.getPrecio();
            int stockRestante = productoModel.getStock();
            int stockDeducir = nuevoDetalleFactura.getCantidad();

            if(stockRestante >= stockDeducir){
                productoModel.setStock(stockRestante - stockDeducir);
                productoRepository.save(productoModel);
                ventaModel.setTotal((int) (precio*cantidad));
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

    public DetalleFacturaModel update (DetalleFacturaModel detalle_factura, int id){
        Optional<DetalleFacturaModel> producto_ventaBD = this.detalleFacturaRepository.findById(id);
        if(producto_ventaBD.isPresent()){
            DetalleFacturaModel pv = producto_ventaBD.get();
            pv.setCantidad(detalle_factura.getCantidad());
            pv.setIdProductoModel(detalle_factura.getIdProductoModel());
            pv.setIdVentaModel(detalle_factura.getIdVentaModel());
            return this.detalleFacturaRepository.save(pv);
        }else{
            return null;
        }
    }


}
