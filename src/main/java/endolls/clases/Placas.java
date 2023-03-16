package endolls.clases;

/**
 *
 * @author Eduardo Núñez
 */
public class Placas {
    private Integer superficie;
    private float precio;
    private Integer potencia;
    
    public Placas(Integer superficie, float precio, Integer potencia) {
        this.superficie = superficie;
        this.precio = precio;
        this.potencia = potencia;
    }
    
    public Integer getSuperficie(){
        return this.superficie;
    }
    
    public float getPrecio(){
        return this.precio;
    }
    
    public Integer getPotencia(){
        return this.potencia;
    }
    
}
