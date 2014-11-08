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
public class Usuario implements Serializable{
    private static final long serialVersionUID = 6297385302078200511L;

    public Usuario(String nombre, String login_name, String password, int tipo) {
        this.nombre = nombre;
        this.login_name = login_name;
        this.password = password;
        this.tipo_usuario = tipo;
    }

    public Usuario(int id_usuario, String nombre, String login_name, String password, int tipo_usuario) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.login_name = login_name;
        this.password = password;
        this.tipo_usuario = tipo_usuario;
    }
    
    
    
    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLoginName() {
        return login_name;
    }

    public void setLoginName(String login_name) {
        this.login_name = login_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(int tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }
    
    private int id_usuario;
    private String nombre;
    private String login_name;
    private String password;
    private int tipo_usuario;
    
    
    
    
}
