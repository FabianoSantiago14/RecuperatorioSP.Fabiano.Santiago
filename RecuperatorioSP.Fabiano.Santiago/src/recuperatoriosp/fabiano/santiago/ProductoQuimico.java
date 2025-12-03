
package recuperatoriosp.fabiano.santiago;

import java.time.LocalDate;

public class ProductoQuimico extends Producto{
    private String advertencia;
    
    public ProductoQuimico(String nombre,String concentracion, LocalDate vencimiento, String advertencia){
        super(nombre,concentracion,vencimiento);
        this.advertencia = advertencia;
    }
    
    public String getAdvertencia() {
        return advertencia;
    }
    
    public void setAdvertencia(String advertencia) {
        this.advertencia = advertencia;
    }
    
    @Override
    public String toString() {
        return super.toString() + " / Advertencia: " + advertencia;
    }
    
    
}
