/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Cristhian
 */
public class CompraDetalle {
    
    private int idCompraDetalle;
    private int compra;
    private int producto;
    private int cantidad;
    private int subtotal;

    public CompraDetalle(int idCompraDetalle, int compra, int producto, int cantidad, int subtotal) {
        this.idCompraDetalle = idCompraDetalle;
        this.compra = compra;
        this.producto = producto;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    public CompraDetalle(int compra, int producto, int cantidad, int subtotal) {
        this.compra = compra;
        this.producto = producto;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }
    
    public int getIdCompraDetalle() {
        return idCompraDetalle;
    }

    public void setIdCompraDetalle(int idCompraDetalle) {
        this.idCompraDetalle = idCompraDetalle;
    }

    public int getCompra() {
        return compra;
    }

    public void setCompra(int compra) {
        this.compra = compra;
    }

    public int getProducto() {
        return producto;
    }

    public void setProducto(int producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }
    
    
    
}
