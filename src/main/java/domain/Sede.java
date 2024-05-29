/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Camilo
 */
public class Sede {
    
    private int numeroCajas;
    private List<Thread> cajas;
    
    private Sede (int numeroCajas, List<Thread> cajas) {
        this.numeroCajas = numeroCajas;
        this.cajas = cajas;
    }
    
    public static Sede crear() {
        int numCajas = 3;
        Caja caja;
        List<Thread> cajas = new ArrayList<>();
        for (int i = 0; i < numCajas; i++) {
            caja = Caja.crear( (i + 1) );
            cajas.add(caja);
        }
        var sede = new Sede(3, cajas);
        return sede;
    }
    
    public void iniciarOperaciones() {
        double totalSede = 0;
        String informe = "";
        Caja caja;
        for (Thread hiloCaja : this.cajas) hiloCaja.start();
        for(Thread hiloCaja : this.cajas) {
            try {
                hiloCaja.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for(Thread hiloCaja : this.cajas) {
            caja = ((Caja)hiloCaja);
            totalSede += caja.getTotalVentasCaja();
            informe += caja.getStringInforme() + "\n\n";
        }
        System.out.println(
            informe + "\n" +
            "\n\n**************** Total Ventas Sede: $" + totalSede + " **********************\n"
        );
    }    
    
}
