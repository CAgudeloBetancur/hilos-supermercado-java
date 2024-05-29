/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author Camilo
 */
public class Cajera {
    
    private String nombre;
    
    private Cajera (String nombre) {
        this.nombre = nombre;
    }
    
    public static Cajera crear(String nombre) {
        var cajera = new Cajera(nombre);
        return cajera;
    }

    public String getNombre() {
        return nombre;
    }
}
