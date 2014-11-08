/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author ccaballero
 */
public class Transaccion implements Serializable{
    
    private static final long serialVersionUID = 6297385302078200511L;
    
    private int id_transaccion;
    private Date fecha;
    private int id_usuario;
    private double total;
    private String direccionEnvio;
    private int medioPago;
    private String forma;

    public String getForma() {
        return forma;
    }

    public void setForma(String forma) {
        this.forma = forma;
    }
    private String nroTarjeta;
    private ArrayList<TransaccionDetalle> detalles;

    public Transaccion(int id_transaccion, Date fecha, int id_usuario, double total, String direccionEnvio, int medioPago, String nroTarjeta) {
        this.id_transaccion = id_transaccion;
        this.fecha = fecha;
        this.id_usuario = id_usuario;
        this.total = total;
        this.direccionEnvio = direccionEnvio;
        this.medioPago = medioPago;
        this.nroTarjeta = nroTarjeta;
    }

    public Transaccion(Date fecha, int id_usuario, double total, String direccionEnvio, int medioPago, String nroTarjeta) {
        this.fecha = fecha;
        this.id_usuario = id_usuario;
        this.total = total;
        this.direccionEnvio = direccionEnvio;
        this.medioPago = medioPago;
        this.nroTarjeta = nroTarjeta;
    }

    public int getId_transaccion() {
        return id_transaccion;
    }

    public void setId_transaccion(int id_transaccion) {
        this.id_transaccion = id_transaccion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getDireccionEnvio() {
        return direccionEnvio;
    }

    public void setDireccionEnvio(String direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }

    public int getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(int medioPago) {
        this.medioPago = medioPago;
    }

    public String getNroTarjeta() {
        return nroTarjeta;
    }

    public void setNroTarjeta(String nroTarjeta) {
        this.nroTarjeta = nroTarjeta;
    }

    public ArrayList<TransaccionDetalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(ArrayList<TransaccionDetalle> detalles) {
        this.detalles = detalles;
    }  
}
