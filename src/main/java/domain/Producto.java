/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Camilo
 */
public class Producto {
    private String nombre;
    private double precio;
    private int cantidad;
    private static final List<String> nombresProductos = Arrays.asList("Panela", "Azucar", "Arroz", "Cuido Perros", "Cuido Gato", "Pan", "Carne", "Sal", "Aguacate", "Mantequilla", "Frijoles");

    private Producto(String nombre, double precio, int cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }
    
    public static Producto crear(){
        String nombreProducto = nombresProductos.get( (int)( Math.random() * (nombresProductos.size()) ) ); // Nombre aleatorio de la lista nombresProductos
        double precioProducto = (int)(Math.random() * 16000) + 3000; // Precio aletario entre 3000 y 18000
        int cantidadProducto = (int)(Math.random() * 4) + 1; // entre 1 y 4 unidades
        var producto = new Producto(nombreProducto, precioProducto, cantidadProducto);
        return producto;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return nombre.equals(producto.nombre);
    }

    @Override
    public int hashCode() {
        return nombre.hashCode();
    }
    
}
