package com.example.ApiRestPrueba.entity;

import jakarta.persistence.*;

import java.io.Serializable;



//CADA CLASE DE ESTE PAQUETE SERA UNA TABLA EN LA CUAL DECLARAMOS CADA CAMPO Y SUS PARAMETROS NECESARIOS CON SUS CORRESPONDIENTES ANOTACIONES
@Entity
@Table(name = "users")
public class User implements Serializable {



    private static final long serialVersionUID = 54545454;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 50)
    private String nombre;

    private String apellido;

    @Column(name = "mail", nullable = false, length = 50, unique = true)//MAIL SERIA EL NOMBRE UNICAMENTE PARA CAMPO DENTRO DE LA DB
    private String email;//A LA HORA DE USAR POSTMAN PARA REALIZAR ALGUNA PETICION HABRIA QUE USAR ESTE NOMBRE

    private Boolean operativo;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getOperativo() {
        return operativo;
    }

    public void setOperativo(Boolean operativo) {
        this.operativo = operativo;
    }
}
