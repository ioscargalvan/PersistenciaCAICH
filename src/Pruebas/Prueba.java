/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pruebas;

import Clases.Cita;
import Clases.Paciente;
import PaqueteDatosPersistencia.Persistencia;
import java.util.List;

/**
 *
 * @author Oscar
 */
public class Prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Persistencia persistencia = new Persistencia();
            List<Cita> listaRecuperada = persistencia.recuperarCitas();
            for (int i = 0; i < listaRecuperada.size(); i++) {
                System.out.println(listaRecuperada.get(i).toString());
                for (int j = 0; j < listaRecuperada.get(j).getMateriales().size(); j++) {
                    System.out.println(listaRecuperada.get(j).getMateriales().get(j).toString());

                }
                
//                System.out.println(listaRecuperada.get(i).getPaciente().getClave());
//                System.out.println(listaRecuperada.get(i).getTerapeuta().getNombre());
//                System.out.println(listaRecuperada.get(i).getCubiculo());
//                System.out.println(listaRecuperada.get(i).getEstado());
//                System.out.println(listaRecuperada.get(i).getFecha());

            }
            //   persistencia.recuperarCitas();
        } catch (Exception e) {
            e.getMessage();
        }

    }

}
