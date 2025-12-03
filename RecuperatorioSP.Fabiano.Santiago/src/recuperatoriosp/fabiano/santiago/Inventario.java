
package recuperatoriosp.fabiano.santiago;

import java.util.ArrayList;
import java.util.List;


public class Inventario {
    private List<Producto> productos;
    
    
    public Inventario() {
        this.productos = new ArrayList<>();
    }
    
    public List<Producto> getProductos() {
        return productos;
    }
    
    public void agregarProducto(Producto p) throws ProductoVencidoExcepcion, ProductoDuplicadoExcepcion {
        if (p.estaVencido()) {
            throw new ProductoVencidoExcepcion("El producto esta Vencido");
        } 
        
        for (Producto x : productos) {
            boolean mismoNombre = x.getNombre().equalsIgnoreCase(p.getNombre());
            boolean mismaConcentracion = x.getConcentracion().equalsIgnoreCase(p.getConcentracion());
            boolean mismoVencimiento = x.getVencimiento().equals(p.getVencimiento());
            
            if (mismoNombre && mismaConcentracion && mismoVencimiento) {
                throw new ProductoDuplicadoExcepcion("Este producto YA existe");
            }
            
        }
        
        productos.add(p);
    }
    
    public void eliminarProducto(Producto producto) {
        productos.remove(producto);
    }
    
    public void modificarProducto(Producto original,Producto remplazo) throws ProductoVencidoExcepcion, ProductoDuplicadoExcepcion {
               if (remplazo.estaVencido()) {
            throw new ProductoVencidoExcepcion("El producto que se quiere remplaza esta VENCIDO");
        }
               
        for (Producto prod : productos) {
            if (prod != original) {
                boolean mismoNombre = prod.getNombre().equalsIgnoreCase(remplazo.getNombre());
                boolean mismaConcentracion = prod.getConcentracion().equalsIgnoreCase(remplazo.getConcentracion());
                boolean mismoVencimiento = prod.getVencimiento().equals(remplazo.getVencimiento());
                
                if (mismoNombre && mismaConcentracion && mismoVencimiento) {
                    throw new ProductoDuplicadoExcepcion("El remplazo del producto genera un duplicado");
                }
                        
            }
        }
        
        original.setNombre(remplazo.getNombre());
        original.setConcentracion(remplazo.getConcentracion());
        original.setVencimiento(remplazo.getVencimiento());
        
        if (original instanceof ProductoQuimico qui && remplazo instanceof ProductoQuimico mico) {
            qui.setAdvertencia(mico.getAdvertencia());
        }
        
        if (original instanceof ProductoEcologico ecol && remplazo instanceof ProductoEcologico ogico) {
            ecol.setEtiqueta(ogico.getEtiqueta());
        }
        
    }
    
    public List<Producto> getProxmimosAVencer() {
        List<Producto> lista = new ArrayList<>();
        for (Producto producto : productos) {
            if (!producto.estaVencido() && producto.estaPorVencer()) {
                lista.add(producto);
            }
        }
        return lista;
    }
    
    
}
