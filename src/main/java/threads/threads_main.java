/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package threads;

import domain.Sede;

/**
 *
 * @author Camilo
 */
public class threads_main {

    public static void main(String[] args) {
        
        long comienzo = System.currentTimeMillis();
        
        var sedeSupermercado = Sede.crear();
        
        sedeSupermercado.iniciarOperaciones();
        
        // Registramos tiempo final
        long fin = System.currentTimeMillis();
        
        long tiempoTotalProcesamiento = ( (fin - comienzo) / 1000 );
        
        System.out.println(
            "\n\n-----------------------------------------------------------------------\n" +
            "Tiempo total procesamiento: " + tiempoTotalProcesamiento + " segundos.\n" +
            "------------------------------------------------------------------------\n"
        ); 
    }
}
