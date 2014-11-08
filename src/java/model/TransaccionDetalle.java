/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;

/**
 *
 * @author ccaballero
 */
public class TransaccionDetalle implements Serializable {
    private static final long serialVersionUID = 6297385302078200511L;
    
    private int id_detalle;
    private int id_transaccion;
    private int id_producto;
    private int cantidad;
    private double subtotal;
    private Producto prod;

    public Producto getProd() {
        return prod;
    }

    public void setProd(Producto prod) {
        this.prod = prod;
    }

    public TransaccionDetalle(int id_detalle, int id_transaccion, int id_producto, int cantidad, double subtotal) {
        this.id_detalle = id_detalle;
        this.id_transaccion = id_transaccion;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    public TransaccionDetalle(int id_transaccion, int id_producto, int cantidad, double subtotal) {
        this.id_transaccion = id_transaccion;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    public int getId_detalle() {
        return id_detalle;
    }

    public void setId_detalle(int id_detalle) {
        this.id_detalle = id_detalle;
    }

    public int getId_transaccion() {
        return id_transaccion;
    }

    public void setId_transaccion(int id_transaccion) {
        this.id_transaccion = id_transaccion;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
    
    
    
}
