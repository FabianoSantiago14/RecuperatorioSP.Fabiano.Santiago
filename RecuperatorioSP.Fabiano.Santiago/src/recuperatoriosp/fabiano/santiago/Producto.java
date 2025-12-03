
package recuperatoriosp.fabiano.santiago;

import java.time.LocalDate;


public abstract class Producto {
    private String nombre;
    private String concentracion;
    private LocalDate vencimiento;
    
    public String getNombre() {
        return nombre;
    }
    
    public String getConcentracion() {
        return concentracion;
    }
    
    public LocalDate getVencimiento() {
        return vencimiento;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setConcentracion(String concentracion) {
        this.concentracion = concentracion;
    }
    
    public void setVencimiento(LocalDate vencimiento) {
        this.vencimiento = vencimiento;
    }
    
    public boolean estaPorVencer() {
        LocalDate hoy = LocalDate.now();
        LocalDate fechaLimite = hoy.plusDays(60);
        
        return vencimiento.isBefore(fechaLimite);
    }
    
    public boolean estaVencido() {
        return vencimiento.isBefore(LocalDate.now());
    }
    
    public Producto(String nombre, String concentracion, LocalDate vencimiento) {
        this.nombre = nombre;
        this.concentracion = concentracion;
        this.vencimiento = vencimiento;
    }
    
    @Override
    public String toString() {
        return "Nombre: " + nombre + " / Concentracion: " + concentracion + " / Vencimiento: " + vencimiento; 
    }
    
}
