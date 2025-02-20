/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/
package com.mycompany.clinicapp.dao;

import com.mycompany.clinicapp.model.HistoriaClinica;
import java.util.List;

public interface HistoriaClinicaDAO {
    List<HistoriaClinica> obtenerHistoriasPorPaciente(int pacienteId);
    boolean crearHistoriaClinica(HistoriaClinica historia);
    boolean actualizarHistoriaClinica(HistoriaClinica historia);
    boolean eliminarHistoriaClinica(int id);
    HistoriaClinica obtenerHistoriaPorId(int id);

    // Nuevo método
    List<HistoriaClinica> obtenerTodasLasHistorias();
}