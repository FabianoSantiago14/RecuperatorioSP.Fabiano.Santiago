
package recuperatoriosp.fabiano.santiago;

import java.time.LocalDate;


public class ProductoEcologico extends Producto {
    private String etiqueta;
    
    public ProductoEcologico(String nombre, String concentracion, LocalDate vencimiento, String etiqueta) {
        super(nombre,concentracion,vencimiento);
        this.etiqueta = etiqueta;
    }
    
    public String getEtiqueta() {
        return etiqueta;
    }
    
    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }
    
    @Override
    public String toString() {
        return super.toString() + " / Etiqueta: " + etiqueta;
    }
    
}
