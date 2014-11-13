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
public class Producto implements Serializable{
    
    private static final long serialVersionUID = 6297385302078200511L;
    
    private int id_producto;
    private String descripcion;
    private int categoria;
    private double precio;
    private int cantidad;
    private Categoria cat;
    private int cantidadDeposito;

    public Categoria getCat() {
        return cat;
    }

    public void setCat(Categoria cat) {
        this.cat = cat;
    }

    public Producto(int id_producto, String descripcion, int categoria, double precio, int cantidad, int cantidadDeposito) {
        this.id_producto = id_producto;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.precio = precio;
        this.cantidad = cantidad;
        this.cantidadDeposito = cantidadDeposito;
    }

    public Producto(String descripcion, int categoria, double precio, int cantidad, int cantidadDeposito) {
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.precio = precio;
        this.cantidad = cantidad;
        this.cantidadDeposito = cantidadDeposito;
    }
    
    

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCantidadDeposito() {
        return this.cantidadDeposito;
    }
    
    public void setCantidadDeposito(int cantidadDeposito)
    {
        this.cantidadDeposito=cantidadDeposito;
    }
    
    
    
}
