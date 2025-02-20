/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/
package com.mycompany.clinicapp.dao.impl;

import com.mycompany.clinicapp.dao.CitaDAO;
import com.mycompany.clinicapp.model.Cita;
import com.mycompany.clinicapp.util.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitaDAOImpl implements CitaDAO {

    @Override
    public List<Cita> obtenerCitasPorPaciente(int pacienteId) {
        List<Cita> citas = new ArrayList<>();
        String sql = "SELECT * FROM citas WHERE paciente_id = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, pacienteId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Cita cita = new Cita();
                cita.setId(rs.getInt("id"));
                cita.setPacienteId(rs.getInt("paciente_id"));
                cita.setFecha(rs.getTimestamp("fecha"));
                cita.setMotivo(rs.getString("motivo"));
                cita.setEstado(rs.getString("estado"));
                citas.add(cita);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return citas;
    }

    @Override
    public boolean crearCita(Cita cita) {
        String sql = "INSERT INTO citas (paciente_id, fecha, motivo, estado) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, cita.getPacienteId());
            stmt.setTimestamp(2, cita.getFecha());
            stmt.setString(3, cita.getMotivo());
            stmt.setString(4, cita.getEstado());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean actualizarCita(Cita cita) {
        String sql = "UPDATE citas SET fecha = ?, motivo = ?, estado = ? WHERE id = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setTimestamp(1, cita.getFecha());
            stmt.setString(2, cita.getMotivo());
            stmt.setString(3, cita.getEstado());
            stmt.setInt(4, cita.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminarCita(int id) {
        String sql = "DELETE FROM citas WHERE id = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Cita obtenerCitaPorId(int id) {
        String sql = "SELECT * FROM citas WHERE id = ?";
        Cita cita = null;
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                cita = new Cita();
                cita.setId(rs.getInt("id"));
                cita.setPacienteId(rs.getInt("paciente_id"));
                cita.setFecha(rs.getTimestamp("fecha"));
                cita.setMotivo(rs.getString("motivo"));
                cita.setEstado(rs.getString("estado"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cita;
    }

    // Implementación del nuevo método
    @Override
    public List<Cita> obtenerTodasLasCitas() {
        List<Cita> citas = new ArrayList<>();
        String sql = "SELECT * FROM citas";
        try (Connection conn = ConexionBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Cita cita = new Cita();
                cita.setId(rs.getInt("id"));
                cita.setPacienteId(rs.getInt("paciente_id"));
                cita.setFecha(rs.getTimestamp("fecha"));
                cita.setMotivo(rs.getString("motivo"));
                cita.setEstado(rs.getString("estado"));
                citas.add(cita);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return citas;
    }
}