package endolls.clases;

import java.util.ArrayList;

/**
 *
 * @author Eduardo Núñez
 */
public class Casa {
    private String nif;
    private String nombre;
    private Integer superficie;
    private boolean interruptor = true;
    private ArrayList<Placas> placas = new ArrayList<>();
    private ArrayList<Aparato> aparatos = new ArrayList<>();
    
    public Casa(String nif, String nombre, Integer superficie){
        this.nif = nif;
        this.nombre = nombre;
        this.superficie = superficie;
    }
    
    public String getNif(){
        return this.nif;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public Integer getSuperficie(){
        return this.superficie;
    }
    
    public boolean getInterruptor(){
        return this.interruptor;
    }
    
    public void setInterruptor(boolean interruptor){
        this.interruptor = interruptor;
    }
    
    public void setPlaca(Integer longitud, float precio, Integer potencia){
        Placas nueva = new Placas(longitud, precio, potencia);
        placas.add(nueva);
    }  
    
    public Integer getPlacas(){
        return placas.size();
    }
    
    public Integer getRestante(){
        Integer suma = 0;
        for(Placas laplaca : placas){
            suma = suma + laplaca.getSuperficie();
        }
        return suma;
    }
    
    public Integer getEnergia(){
        Integer suma = 0;
        for(Placas laplaca : placas){
            suma = suma + laplaca.getPotencia();
        }
        return suma;
    }
    
    public void setAparell(String descripcion,Integer consumo){
        Aparato nuevo = new Aparato(descripcion,consumo);
        aparatos.add(nuevo);
    }
    
    public Integer getAparells(){
        return aparatos.size();
    }
    
    public Integer getGasto(){
        Integer suma = 0;
        for(Aparato elaparato : aparatos){
            if(elaparato.getEstado() == true){
                suma = suma + elaparato.getConsumo();
            }            
        }
        return suma;
    }
   
   public Integer getAparellEstado(boolean estado, String desc){
       for(Aparato elaparato : aparatos){
           if(elaparato.getEstado() == estado && elaparato.getDescripcion().equals(desc)){
               //Si el estado del aparato es igual al valor booleano pasado, devolvemos 1.
               return 1;                             
           } else if(elaparato.getEstado() != estado && elaparato.getDescripcion().equals(desc)){
               //Si el estado del aparato es diferente al valor booleano, devolvemos 2.
               return 2;
           }
       } return 3;
    }
   
   public void setAparellEstado(String desc){
       for(Aparato elaparato : aparatos){
           if(elaparato.getDescripcion().equals(desc)){
               if(elaparato.getEstado() == true){
                   elaparato.setOff();
               } else {
                   elaparato.setOn();
               }
           }
       }
   }
   
   public void setOffPlomos(){
       for(Aparato elaparato : aparatos){
           elaparato.setOff();
       }
   }
    
    public float getInversion(){
        float suma = 0;
        for(Placas laplaca : placas){
            suma = suma + laplaca.getPrecio();
        }
        return suma;
    }
    
    public boolean getAparell(String desc){
        for(Aparato elaparato : aparatos){
            if(elaparato.getDescripcion().equals(desc)){
                return true;
            }
        } return false;
    }
}
