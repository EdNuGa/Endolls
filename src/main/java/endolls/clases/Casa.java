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
    
    
    //Constructor.
    public Casa(String nif, String nombre, Integer superficie){
        this.nif = nif;
        this.nombre = nombre;
        this.superficie = superficie;
    }
    
    //Getters principales.
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
    //Cambiamos el interruptor general al valor introducido (true o false).
    public void setInterruptor(boolean interruptor){
        this.interruptor = interruptor;
    }
    //Metodo que registra una placa solar en el ArrayList de la casa.
    public void setPlaca(Integer longitud, float precio, Integer potencia){
        Placas nueva = new Placas(longitud, precio, potencia);
        placas.add(nueva);
    }  
    //Getter que nos muestra el número de placas registradas.
    public Integer getPlacas(){
        return placas.size();
    }
    //Metodo que calcula la superficie que ocupan todas las placas.
    public Integer getRestante(){
        Integer suma = 0;
        for(Placas laplaca : placas){
            suma = suma + laplaca.getSuperficie();
        }
        return suma;
    }
    //Metodo que obtiene y devuelve la potencia de todas las placas solares.
    public Integer getEnergia(){
        Integer suma = 0;
        for(Placas laplaca : placas){
            suma = suma + laplaca.getPotencia();
        }
        return suma;
    }
    //Metodo que añade un aparato en el ArrayList de la casa.
    public void setAparell(String descripcion,Integer consumo){
        Aparato nuevo = new Aparato(descripcion,consumo);
        aparatos.add(nuevo);
    }
    //Getter para obtener el número de aparatos registrados.
    public Integer getAparells(){
        return aparatos.size();
    }
    //Metodo que devuelve el gasto de los aparatos encendidos.
    public Integer getGasto(){
        Integer suma = 0;
        for(Aparato elaparato : aparatos){
            if(elaparato.getEstado() == true){
                suma = suma + elaparato.getConsumo();
            }            
        }
        return suma;
    }
   //Metodo que comprueba el estado del interruptor de un aparato (Encendido o apagado).
   public Integer getAparellEstado(boolean estado, String desc){
       for(Aparato elaparato : aparatos){
           if(elaparato.getEstado() == estado && elaparato.getDescripcion().equals(desc)){
               //Si el estado del aparato es igual al valor booleano pasado, devolvemos 1.
               return 1;                             
           } else if(elaparato.getEstado() != estado && elaparato.getDescripcion().equals(desc)){
               //Si el estado del aparato es diferente al valor booleano, devolvemos 2.
               return 2;
           }
       } return 3; //Si no encuentra el aparato devolvemos 3.
    }
   //Comprueba el estado del interruptor del aparato y lo cambia al contrario.
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
   //Metodo para apagar todos los aparatos (Usado para cuando saltan los plomos).
   public void setOffPlomos(){
       for(Aparato elaparato : aparatos){
           elaparato.setOff();
       }
   }
    //Metodo que devuelve el precio total de todas las placas.
    public float getInversion(){
        float suma = 0;
        for(Placas laplaca : placas){
            suma = suma + laplaca.getPrecio();
        }
        return suma;
    }
    //Comprueba que la descripción del aparato esté dentro del ArrayList de la casa.
    public boolean getAparell(String desc){
        for(Aparato elaparato : aparatos){
            if(elaparato.getDescripcion().equals(desc)){
                return true;
            }
        } return false;
    }
}
