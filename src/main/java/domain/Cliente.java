/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Camilo
 */
public class Cliente {
    private String nombre;
    private List<Producto> productos;
    
    
    private Cliente(String nombre, List<Producto> productos) {
        this.nombre = nombre;
        this.productos = productos;
    }
    
    public static Cliente crear(String nombreCliente) {
        var cantidadProductos = (int)((Math.random() * 4) + 2); // entre 2 y 5
        List<Producto> listaProductos = new ArrayList();
        Producto nuevoProducto;
        for (int i = 0; i < cantidadProductos; i++) {
            nuevoProducto = Producto.crear();
            listaProductos.add(nuevoProducto);
        }
        Set<Producto> conjunto = new LinkedHashSet<>(listaProductos); // con esto eliminamos posibles duplicados
        List<Producto> listaSinDuplicados = new ArrayList<>(conjunto);
        var cliente = new Cliente(nombreCliente, listaSinDuplicados);
        return cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Producto> getProductos() {
        return productos;
    }
}
