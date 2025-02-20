/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clinicapp.model;

import java.sql.Timestamp;

public class Cita {
    private int id;
    private int pacienteId;
    private Timestamp fecha;
    private String motivo;
    private String estado;

    // Constructor vacío
    public Cita() {
    }

    // Constructor con parámetros
    public Cita(int id, int pacienteId, Timestamp fecha, String motivo, String estado) {
        this.id = id;
        this.pacienteId = pacienteId;
        this.fecha = fecha;
        this.motivo = motivo;
        this.estado = estado;
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

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}