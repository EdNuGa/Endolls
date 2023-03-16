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
        Placas[] listplacas = new Placas[10];
        int contador = 0;
        boolean controlador = false;
        boolean salir = false;
        BufferedReader Consola = new BufferedReader(new InputStreamReader(System.in));
        
        do {
            System.out.print(">");
            String orden = Consola.readLine();
            String[] datos = orden.split(" ");
            if (datos.length > 0){
                switch(datos[0].toLowerCase()){
                    //CASE 1.
                    case "addcasa":                        
                        if (datos.length == 4) {                            
                            String nif = datos[1];
                            String nombre = datos[2];
                            String num = datos[3];
                            int superficie = Integer.parseInt(num);
                            if(superficie < 10){
                                System.out.println("ERROR: Superfície incorrecta. Ha de ser més gran de 10.");
                            } else {
                                Casa nueva = new Casa(nif, nombre, superficie);
                                casas.add(nueva);
                                System.out.println("OK: Casa registrada.");
                            }   
                        } else {
                            System.out.println("ERROR: Número de paràmetres incorrecte.\nÚs: addCasa [nif] [nom] [superficie]");
                        }
                        break;
                    //CASE 2.
                    case "addplaca":
                        if (datos.length == 5){                       
                            controlador = false;
                            String nifplaca = datos[1];
                            String longitud = datos[2];
                            String valor = datos[3];
                            String consumo = datos[4];
                            int superficie = Integer.parseInt(longitud);
                            float precio = Float.parseFloat(valor);
                            int potencia = Integer.parseInt(consumo);
                            if(precio > 0){
                                for(Casa lacasa: casas){
                                    Integer supTotal = lacasa.getSuperficie();
                                    Integer supRest = lacasa.getRestante();
                                    Integer resto = supTotal - supRest;
                                    if(lacasa.getNif().equals(nifplaca)){
                                        if(superficie < resto){
                                            lacasa.setPlaca(superficie,precio,potencia);  
                                            System.out.println("OK: Placa afegida a la casa.");
                                            controlador = true;
                                        } else {  
                                            System.out.println("ERROR: No hi ha espai disponible per a instal·lar aquesta placa.");
                                            controlador = true;
                                        }
                                    }                                 
                                } if(controlador == false){
                                        System.out.println("ERROR: No hi ha cap casa registrada amb aquest nif.");
                                    }
                            } else {
                                System.out.println("ERROR: Preu incorrecte. Ha de ser més gran de 0.");
                            }
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
                                if(lacasa.getNif().equals(nifaparell) && !lacasa.getAparell(descripcion)){                                
                                    lacasa.setAparell(descripcion,energia);  
                                    System.out.println("OK: Aparell afegit a la casa.");
                                    controlador = true;
                                }else if(lacasa.getAparell(descripcion)){
                                    System.out.println("ERROR: Ja existeix un aparell amb aquesta descripció a la casa indicada.");
                                }                                                                
                            }
                            if(controlador == false){  
                                    System.out.println("ERROR: No hi ha cap casa registrada amb aquest nif.");                                    
                                    }
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
                                if(lacasa.getNif().equals(datos[1]) && lacasa.getInterruptor() == true){
                                    if(lacasa.getAparellEstado(true, descripcion) == 2){
                                        lacasa.setAparellEstado(descripcion);                                        
                                        if(lacasa.getGasto() < lacasa.getEnergia()){
                                            System.out.println("OK: Aparell encès.");
                                        }else{
                                            lacasa.setOffPlomos();
                                            lacasa.setInterruptor(false);
                                            System.out.println("ERROR: Han saltat els ploms. La casa ha quedat completament apagada.");
                                        }                                        
                                    }else if(lacasa.getAparellEstado(true, descripcion) == 1){
                                        System.out.println("ERROR: L'aparell ja està encès.");
                                    }else if(lacasa.getAparellEstado(true, descripcion) == 3){
                                        System.out.println("ERROR: No hi ha cap aparell registrat amb aquesta descripció a la casa indicada.");
                                    }
                                }else if(lacasa.getInterruptor() == false){
                                    System.out.println("ERROR: No es pot encendre l'aparell. L'interruptor general està apagat.");
                                }
                            }
                            
                        }else {
                            System.out.println("ERROR: Número de paràmetres incorrecte\nÚs: onAparell [nif] [descripció aparell]");
                        }
                        break;
                    //CASE 6.
                    case "offaparell":
                        if(datos.length == 3){
                            String descripcion = datos[2];
                            for(Casa lacasa: casas){
                                if(lacasa.getNif().equals(datos[1])){
                                    if(lacasa.getAparellEstado(false, descripcion) == 2){
                                        lacasa.setAparellEstado(descripcion);                                        
                                        if(lacasa.getGasto() < lacasa.getEnergia()){
                                            System.out.println("OK: Aparell apagat.");
                                        }                                       
                                    }else if(lacasa.getAparellEstado(false, descripcion) == 1){
                                        System.out.println("ERROR: L'aparell ja està apagat.");
                                    }else if(lacasa.getAparellEstado(false, descripcion) == 3){
                                        System.out.println("ERROR: No hi ha cap aparell registrat amb aquesta descripció a la casa indicada.");
                                    }
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
                            Integer contar = 1;
                            for(Casa lacasa : casas){
                                System.out.println("Casa " + contar);
                                System.out.println("Client: " + lacasa.getNif() + " - " + lacasa.getNombre());
                                System.out.println("Superficie teulada: " + lacasa.getSuperficie());
                                Integer resta = lacasa.getSuperficie() - lacasa.getRestante();
                                System.out.println("Superficie disponible: " + resta);
                                if(lacasa.getInterruptor() == true){
                                    System.out.println("Interruptor general: encès");
                                } else {
                                    System.out.println("Interruptor general: apagat");
                                }
                                if(lacasa.getPlacas().equals(0)){
                                    System.out.println("No té plaques solars instal·lades.");
                                }else{
                                    System.out.println("Places solars instal·lades: " + lacasa.getPlacas());
                                }
                                if(lacasa.getAparells().equals(0)){
                                    System.out.println("No té cap aparell elèctric registrat.\n");
                                } else {
                                    System.out.println("Aparells registrats: " + lacasa.getAparells() + "\n");
                                }                                
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
