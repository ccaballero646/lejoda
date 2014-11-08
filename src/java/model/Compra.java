/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author Cristhian
 */
public class Compra {
    
    private int idCompra;
    private Date fecha;
    private int usuario;
    private int total;

    public Compra(int idCompra, int usuario, int total, Date fecha) {
        this.idCompra = idCompra;
        this.usuario = usuario;
        this.total = total;
        this.fecha = fecha;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Compra(int usuario, int total, Date fecha) {
        this.usuario = usuario;
        this.total = total;
        this.fecha = fecha;
    }
    
    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
    
    
}
