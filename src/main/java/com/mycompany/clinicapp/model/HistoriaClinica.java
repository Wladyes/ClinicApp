/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clinicapp.model;

import java.sql.Date;

public class HistoriaClinica {
    private int id;
    private int pacienteId;
    private Date fecha;
    private String descripcion;

    // Constructor vacío
    public HistoriaClinica() {
    }

    // Constructor con parámetros
    public HistoriaClinica(int id, int pacienteId, Date fecha, String descripcion) {
        this.id = id;
        this.pacienteId = pacienteId;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
  
    public int getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(int pacienteId) {
        this.pacienteId = pacienteId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}