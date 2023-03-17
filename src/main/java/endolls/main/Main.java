package endolls.main;

import endolls.clases.Placas;
import endolls.clases.Casa;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author Eduardo Núñez
 */
public class Main {

    public static void main(String[] args) throws IOException {
        ArrayList<Casa> casas = new ArrayList<>();
        //Variables para controlar la salidas de futuras condiciones.
        boolean controlador = false;
        boolean salir = false;
        BufferedReader Consola = new BufferedReader(new InputStreamReader(System.in));
        
        do {
            //Iniciamos la consola para el usuario.
            System.out.print(">");
            //Leemos los datos introducidos por el usuario.
            String orden = Consola.readLine();
            //Convertimos los datos en un array.
            String[] datos = orden.split(" ");
            if (datos.length > 0){
                switch(datos[0].toLowerCase()){
                    //CASE 1.
                    case "addcasa":                        
                        if (datos.length == 4) {                            
                            String nif = datos[1];
                            String nombre = datos[2];
                            String num = datos[3];
                            //Convertimos nuestro String num en Integer.
                            int superficie = Integer.parseInt(num);
                            //Comparamos que la superficie sea mayor a 10.
                            if(superficie < 10){
                                System.out.println("ERROR: Superfície incorrecta. Ha de ser més gran de 10.");
                            } else {
                                //Si es mayor a 10, comprobamos si nuestro ArrayList está vacío o no (Si tiene casas registradas).
                                if(casas.isEmpty()){
                                    //Si está vacía registramos la primera casa.
                                    Casa nueva = new Casa(nif, nombre, superficie);
                                    casas.add(nueva);
                                    System.out.println("OK: Casa registrada.");
                                }else{
                                    //Si no está vacía comprobamos que el nif no esté repetido.
                                    for(Casa lacasa: casas){
                                        if(lacasa.getNif().equals(datos[1])){
                                            //Si está repetida, enviamos aviso.
                                            System.out.println("ERROR: La casa ja existeix.");
                                        } else{
                                            //Si no está repetido, añadimos la casa.
                                            Casa nueva = new Casa(nif, nombre, superficie);
                                            casas.add(nueva);
                                            System.out.println("OK: Casa registrada.");
                                        }
                                    }
                                }                               
                            }   
                        } else {
                            //Si los valores introducidos son diferentes a los pedidos, enviamos aviso del uso.
                            System.out.println("ERROR: Número de paràmetres incorrecte.\nÚs: addCasa [nif] [nom] [superficie]");
                        }
                        break;
                    //CASE 2.
                    case "addplaca":
                        if (datos.length == 5){
                            //Reseteamos la variable controlador.
                            controlador = false;
                            String nifplaca = datos[1];
                            String longitud = datos[2];
                            String valor = datos[3];
                            String consumo = datos[4];
                            //Convertimos los Strings numéricos en Integer y Float.
                            int superficie = Integer.parseInt(longitud);
                            float precio = Float.parseFloat(valor);
                            int potencia = Integer.parseInt(consumo);
                            //Comprobamos que el precio sea mayor a 0.
                            if(precio > 0){
                                for(Casa lacasa: casas){
                                    //Creamos variable con la superficie total de la casa.
                                    Integer supTotal = lacasa.getSuperficie();
                                    //Creamos una variable con la superficie ocupada por las placas ya existentes en la casa.
                                    Integer supRest = lacasa.getRestante();
                                    //Creamos variable donde restamos superficie total menos superficie usada.
                                    Integer resto = supTotal - supRest;                                    
                                    if(lacasa.getNif().equals(nifplaca)){
                                        if(superficie < resto){
                                            lacasa.setPlaca(superficie,precio,potencia);  
                                            System.out.println("OK: Placa afegida a la casa.");
                                            //Si la superficie restante es mayor a la superficie de la nueva placa se añade la casa 
                                            //y cambiamos la variable control para saltarnos el resto de condiciones.
                                            controlador = true;
                                        } else {  
                                            System.out.println("ERROR: No hi ha espai disponible per a instal·lar aquesta placa.");
                                            //Si la superficie del aparato es mayor que la superficie libre de la casa
                                            //enviamos error y cambiamos nuestra variable de control.
                                            controlador = true;
                                        }
                                    }                                 
                                } //Si la casa no se ha encontrado en el ArrayList saldrá del for sin cambiar la variable de control
                                //la casa no habrá sido encontrada.
                                if(controlador == false){
                                        System.out.println("ERROR: No hi ha cap casa registrada amb aquest nif.");
                                    } 
                            //Si el precio es 0 o menor saltará del if de su comprobación a este Else.
                            } else {
                                System.out.println("ERROR: Preu incorrecte. Ha de ser més gran de 0.");
                            }
                            //Si los datos son diferente a 5 valores no entrará en el if inicial y entrará en este Else.
                        } else{
                                System.out.println("ERROR: Número de paràmetres incorrecte.\nÚs: addPlaca [nif] [superficie] [preu] [potència]");
                            }
                        break;
                    //CASE 3.
                    case "addaparell":
                        if (datos.length == 4){
                            controlador = false;
                            String nifaparell = datos[1];
                            String descripcion = datos[2];
                            String gasto = datos[3];
                            int energia = Integer.parseInt(gasto);
                            for(Casa lacasa: casas){
                                //Comprobamos que la casa esté registrada y si la casa tiene el aparato registrado.
                                if(lacasa.getNif().equals(nifaparell) && !lacasa.getAparell(descripcion)){                                
                                    lacasa.setAparell(descripcion,energia);  
                                    System.out.println("OK: Aparell afegit a la casa.");
                                    controlador = true;
                                //Si la descripción pasada ya está en el ArrayList de la casa, entra en else if.
                                }else if(lacasa.getAparell(descripcion)){
                                    System.out.println("ERROR: Ja existeix un aparell amb aquesta descripció a la casa indicada.");
                                }                                                                
                            }
                            if(controlador == false){  
                                    System.out.println("ERROR: No hi ha cap casa registrada amb aquest nif.");                                    
                                    }
                        //Si los parámetros son diferentes a 4, entra en else.
                        } else{
                            System.out.println("ERROR: Número de paràmetres incorrecte.\nÚs: addAparell [nif] [descripció] [potència]");
                        } 
                        break;
                    //CASE 4.
                    case "oncasa":
                        controlador = false;
                        for(Casa lacasa: casas){
                            if(lacasa.getNif().equals(datos[1])){
                                if(lacasa.getInterruptor() == true){
                                    System.out.println("ERROR: La casa ja té l'interruptor encès.");
                                    controlador = true;
                                } else {
                                    lacasa.setInterruptor(true);
                                    System.out.println("OK: Interruptor general activat.");
                                    controlador = true;
                                }
                            }
                        } if(controlador == false) {
                                System.out.println("ERROR: No s'ha trovat la casa.");
                            }
                        break;
                    //CASE 5.
                    case "onaparell":
                        if(datos.length == 3){
                            String descripcion = datos[2];
                            for(Casa lacasa: casas){
                                //Comprobamos que la casa existe y el estado del interruptor del aparato.
                                if(lacasa.getNif().equals(datos[1]) && lacasa.getInterruptor() == true){
                                    //Si la casa existe y el interruptor está apagado, cambiará su estado a encendido.
                                    if(lacasa.getAparellEstado(true, descripcion) == 2){
                                        lacasa.setAparellEstado(descripcion);   
                                        //Si el gasto total de la casa es menor a la energía de las placas, entra.
                                        if(lacasa.getGasto() < lacasa.getEnergia()){
                                            System.out.println("OK: Aparell encès.");
                                        //Si todos los aparatos consumen más de la energía de las placas, entra en else.
                                        }else{
                                            //Ponemos en apagado el interruptor general de la casa y el interruptor de los aparatos, simulando el salto
                                            //el salto de los plomos.
                                            lacasa.setOffPlomos();
                                            lacasa.setInterruptor(false);
                                            System.out.println("ERROR: Han saltat els ploms. La casa ha quedat completament apagada.");
                                        }
                                    //Si el estado del aparato es encendido nuestro metodo devuelve 2 y entra en else if.
                                    }else if(lacasa.getAparellEstado(true, descripcion) == 1){
                                        System.out.println("ERROR: L'aparell ja està encès.");
                                    //Si nuestro metodo para comparar el estado de del aparato no lo encuentra en la lista devuelve 3.
                                    }else if(lacasa.getAparellEstado(true, descripcion) == 3){
                                        System.out.println("ERROR: No hi ha cap aparell registrat amb aquesta descripció a la casa indicada.");
                                    }
                                //Si el interruptor general está apagado entra en esle if, saltando todo el proceso anterior.
                                }else if(lacasa.getInterruptor() == false){
                                    System.out.println("ERROR: No es pot encendre l'aparell. L'interruptor general està apagat.");
                                }else{
                                    System.out.println("ERROR: No s'ha trovat la casa.");
                                }
                            }
                        //Si los parámetros pasados son diferentes a 3, entrará en else.    
                        }else {
                            System.out.println("ERROR: Número de paràmetres incorrecte\nÚs: onAparell [nif] [descripció aparell]");
                        }
                        break;
                    //CASE 6.
                    case "offaparell":
                        if(datos.length == 3){
                            String descripcion = datos[2];
                            //Comprobamos que la casa (nif) está registrado.
                            for(Casa lacasa: casas){
                                if(lacasa.getNif().equals(datos[1])){
                                    //Si la casa existe, comrpueba el estado del aparato (true o false).
                                    if(lacasa.getAparellEstado(false, descripcion) == 2){
                                        //Si el interruptor del aparato está en true (Encendido), entra y lo cambia.
                                        lacasa.setAparellEstado(descripcion);
                                        System.out.println("OK: Aparell apagat.");
                                    //Si el interruptor del aparato está en false (Apagado), entra en else if.
                                    }else if(lacasa.getAparellEstado(false, descripcion) == 1){
                                        System.out.println("ERROR: L'aparell ja està apagat.");
                                    //Si el metodo que detecta el estado del aparato no lo encuentra en la lista, nos devuelve 3 y entra en este else if.
                                    }else if(lacasa.getAparellEstado(false, descripcion) == 3){
                                        System.out.println("ERROR: No hi ha cap aparell registrat amb aquesta descripció a la casa indicada.");
                                    }
                                //Si el nif pasado no está en el ArrayList, entra en else.
                                }else{
                                    System.out.println("ERROR: No s'ha trovat la casa.");
                                }
                            }
                            
                        }else {
                            System.out.println("ERROR: Número de paràmetres incorrecte\nÚs: offAparell [nif] [descripció aparell]");
                        }
                        break;
                    //CASE 7.
                    case "list":
                        if(casas.size() <= 0){
                            System.out.println("No hi ha cases registrades.");
                        } else {
                            System.out.println("--- Endolls Solars, S.L. ---");
                            System.out.println("Cases registrades: " + casas.size() + "\n");
                            //Declaramos variable para contar las casas que mostrará por pantalla.
                            Integer contar = 1;
                            for(Casa lacasa : casas){
                                System.out.println("Casa " + contar); //Mostramos la posición de la casa en el registro.
                                System.out.println("Client: " + lacasa.getNif() + " - " + lacasa.getNombre()); //Mostramos Nif de registro y el nombre propietario.
                                System.out.println("Superficie teulada: " + lacasa.getSuperficie());//Mostramos la superficie total de la casa.
                                Integer resta = lacasa.getSuperficie() - lacasa.getRestante(); //Restamos a la superficie total la superficie utilizada por los paneles.
                                System.out.println("Superficie disponible: " + resta);
                                //Comprobamos el estado del interruptor general.
                                if(lacasa.getInterruptor() == true){
                                    System.out.println("Interruptor general: encès");
                                } else {
                                    System.out.println("Interruptor general: apagat");
                                }
                                //Comprobamos si la casa tiene placas registradas o no.
                                if(lacasa.getPlacas().equals(0)){
                                    System.out.println("No té plaques solars instal·lades.");
                                }else{
                                    System.out.println("Places solars instal·lades: " + lacasa.getPlacas());
                                //Comprobamos si la casa tiene aparatos registrados o no.
                                }
                                if(lacasa.getAparells().equals(0)){
                                    System.out.println("No té cap aparell elèctric registrat.\n");
                                } else {
                                    System.out.println("Aparells registrats: " + lacasa.getAparells() + "\n");
                                }
                                //Sumamos uno a nuestra variable que cuenta las casas.                                
                                contar++;
                            }
                        }
                        break;
                    //CASE 8.
                    case "info":
                        controlador = false;
                        for(Casa lacasa : casas){
                            if(lacasa.getNif().equals(datos[1])){
                                System.out.println("Client: " + lacasa.getNif() + " - " + lacasa.getNombre());
                                System.out.println("Places solars instal·lades: " + lacasa.getPlacas());
                                System.out.println("Potència total: " + lacasa.getEnergia() + "W");
                                System.out.println("Inversió total: " + lacasa.getInversion() + "€");                                                              
                                System.out.println("Aparells registrats: " + lacasa.getAparells());
                                System.out.println("Consum actual: " + lacasa.getGasto());
                                System.out.println("Aparells encesos:");
                                //System.out.println("    - " +);
                                controlador = true;
                            }
                        } if(controlador = false){
                                System.out.println("ERROR: No hi ha cap casa registrada amb aquest nif.");
                            }
                        break;
                    //CASE 9.
                    case "quit":
                        salir = true;
                        break;
                    //CASE DEFAULT.
                    default:
                        System.out.println("ERROR: Opció incorrecte.");
                }
                
                
                
            }else {
                System.out.println("ERROR: Número de paràmetres incorrecte.");
            }
        } while(!salir);
        
        
    }
    
}
