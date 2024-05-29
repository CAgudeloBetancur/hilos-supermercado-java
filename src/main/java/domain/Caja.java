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
public class Caja extends Thread {
    
    private int id;
    private List<Cliente> clientes;
    private Cajera cajera;
    
    private List<Producto> productos;
    private double total = 0;
    private int unidadesTotales = 0;
    private double totalVentasCaja = 0;

    public double getTotalVentasCaja() {
        return totalVentasCaja;
    }
    
    private String stringInforme = "";
    
    private Caja(int id , Cajera cajera, List<Cliente> listaClientes) {
        this.id = id;
        this.cajera = cajera;
        this.clientes = listaClientes;
    }
    
    public static Caja crear(int id) {
        int cantidadClientes = (int)((Math.random() * 4) + 2); // entre 2 y 5
        Cliente cliente;
        List<Cliente> listaClientes = new ArrayList<>();
        for (int i = 0; i < cantidadClientes; i++) {
            cliente = Cliente.crear( "Cliente " + (i + 1) );
            listaClientes.add(cliente);
        }
        var cajera = Cajera.crear("Cajera " + id);
        var caja = new Caja(id, cajera, listaClientes);
        return caja;
    }

    @Override
    public void run() {
        
        
        long comienzo = System.currentTimeMillis();
        
        System.out.println(
            "\n !!!!!!!!!!!!!!!!!!!!! Apertura ( Caja "+ this.id +" ) !!!!!!!!!!!!!!!!!!!!!!!!!!!\n" +
            "\n | caja: " + this.id + 
            "\n | cajera: " + this.cajera.getNombre() + 
            "\n | clientes: " + clientes.size() +
            "\n | inicio: " + tiempoTranscurridoDesde(comienzo) + " segundos."
        );
        
        procesarClientes(clientes);
        
        System.out.println("XXXXXXXXXXXXXX CIERRE ( Caja "+ this.id +" ) XXXXXXXXXXXXXXXXXXXXXX");
        
        stringInforme = 
            "\n#########################################################" +
            "\n ++++++++++++++ Cierre ( Caja "+ this.id +" ) ++++++++++++++++++++" +
            "\n\n | cajera: " + this.id + 
            "\n | clientes:  " + clientes.size() +
            "\n | total unidades de productos procesados: " + unidadesTotales +
            "\n | total caja: $ " + total + 
            "\n | tiempo procesamiento: " + tiempoTranscurridoDesde(comienzo) + " segundos." +
            "\n\n" + stringInforme + 
            "\n#########################################################";        
    }

    public String getStringInforme() {
        return stringInforme;
    }
    
    private void esperar(long CantidadSegundos) {
        try {
            Thread.sleep((long)(CantidadSegundos * 1000));                
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private long tiempoTranscurridoDesde(long comienzo) {
        return  ((System.currentTimeMillis() - comienzo) / 1000);
    }
    
    private void procesarClientes(List<Cliente> clientes) {
        stringInforme += "~~~~~~~~~~ Informe Caja " + this.id + " ~~~~~~~~~~~~\n";
        Cliente cliente;
        long inicioTiempoProcesoCliente;
        for (int i = 0; i < clientes.size(); i++) {
            cliente = clientes.get(i);
            System.out.println("\n---- Caja " + this.id + " | ATENDIENDO a cliente # " + (i + 1) + " | ( " + cliente.getNombre() + " )...\n");
            inicioTiempoProcesoCliente = System.currentTimeMillis();
            stringInforme += "\n  ~ " + cliente.getNombre() + " ( " + cliente.getProductos().size() +" productos )\n\n";
            productos = cliente.getProductos();
            var totalCliente = procesarProductos(cliente, productos);
            totalVentasCaja += totalCliente;
            total += totalCliente;
            stringInforme += "\n     = total: $" + totalCliente + "\n     + tiempo: " + tiempoTranscurridoDesde(inicioTiempoProcesoCliente) + " segundos\n" + 
                "  -----------------------"; 
//            System.out.println(
//                "\n****  Caja " + this.id + " | TERMINA con cliente # " + (i + 1) + " | ( " + cliente.getNombre() + " )...\n" +
//                "\n | tiempo: " + tiempoTranscurridoDesde(inicioTiempoProcesoCliente) + " segundos" + 
//                "\n | productos: " + productos.size() +
//                "\n | total compra: $" + totalCliente
//            );
        }
    }
    
    private double procesarProductos(Cliente cliente, List<Producto> productos) {
        long inicioTiempoProcesoProducto;
        Producto producto;
        double totalCliente = 0;
        for (int j = 0; j < productos.size(); j++) {
            inicioTiempoProcesoProducto = System.currentTimeMillis();
            producto = productos.get(j);
            esperar(producto.getCantidad());
            double totalProducto = producto.getPrecio() * producto.getCantidad();
            totalCliente += totalProducto;
            unidadesTotales += producto.getCantidad();
            stringInforme += "     - " + 
                producto.getNombre() + 
                "\n         " + producto.getCantidad() + " unidades" +
                "\n         precio unidad: $" + producto.getPrecio() + 
                "\n         total: $" + totalProducto + 
                "\n         tiempo: " + tiempoTranscurridoDesde(inicioTiempoProcesoProducto) + " segundos\n";
            System.out.println(
                "\n   -> ( Caja " + this.id + " ) procesando producto ( " + producto.getNombre() + " )" + " | cliente: " + cliente.getNombre() + " | " + producto.getCantidad() + " unidad(es)\n"
            );
        }
        return totalCliente;
    }
    
}
