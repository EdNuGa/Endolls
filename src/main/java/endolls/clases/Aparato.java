package endolls.clases;

/**
 *
 * @author Eduardo Núñez
 */
public class Aparato {
    private String descripcion;
    private Integer consumo;
    private boolean interruptor = false;
    
    public Aparato(String descripcion, Integer consumo) {
        this.descripcion = descripcion;
        this.consumo = consumo;        
    }
    
    public String getDescripcion(){
        return this.descripcion;
    }
    
    public Integer getConsumo(){
        return this.consumo;
    }
    
    public boolean getEstado(){
        return this.interruptor;
    }
    
    public void setOn(){
        this.interruptor = true;
    }
    
    public void setOff(){
        this.interruptor = false;
    }
}
