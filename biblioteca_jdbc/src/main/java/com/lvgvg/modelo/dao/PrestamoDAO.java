package com.lvgvg.modelo.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
public class PrestamoDAO implements DAO<Prestamo> {
    private Connection conexion = JDBC.getConexion();
    private final String CREATE = "INSERT INTO Prestamo (fechaInicio, fechaFin, usuarioId, libroId) VALUES(?, ?, ?, ?)";
    private final String READ = "SELECT * FROM Prestamo WHERE id = ?";
    private final String READALL = "SELECT * FROM Prestamo";
    private final String UPDATE = "UPDATE Prestamo SET fechaInicio=?, fechaFin=?, usuarioId=?, libroId=? WHERE id = ?";
    private final String DELETE = "DELETE * FROM Prestamo WHERE id = ?";
    @Override
    public Integer create(Prestamo p) {
        int usuarioId = p.getUsuarioId();
        int libroId = p.getLibroId();
            try {
                PreparedStatement ps = conexion.prepareStatement(CREATE);
                ps.setString(1, p.getFechaInicio());
                ps.setString(2, p.getFechaFin());
                ps.setInt(3, p.getUsuarioId());
                ps.setInt(4, p.getLibroId());
                return 1
            } catch (SQLException e) {
                return new SQLException();
            }
            return -1

    }
    @Override
    public Prestamo read(Prestamo p) {
        try {
        PreparedStatement ps = conexion.prepareStatement(READ);
        ps.setInt(1, p.getId());
        ResultSet rs = ps.executeQuery();
        crearPrestamo(rs);
        return 1;
        } catch(SQLException e) {
            return new SQLException();
        } return -1;
    }
    @Override
    public ArrayList<Prestamo> readAll() {
        ArrayList<Prestamo> listaPrestamo= new ArrayList<>();
        try {
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(READALL);
        while (rs.next) {
            listaPrestamo.add(crearPrestamo(rs));
        }
        } catch (SQLException e) {
            return new SQLException();
        }
        return listaPrestamo;
    }
    @Override
    public Integer update(Prestamo p) {
        try {
            PreparedStatement ps = conexion.prepareStatement(UPDATE);
            ps.setString(1, p.getFechaInicio();
            ps.setString(2, p.getFechaFin());
            ps.setInt(3, p.getUsuarioId());
            ps.setInt(4, p.getLibroId());
            ps.setInt(5, p.getId());
            return ps.executeUpdate();
        } catch(SQLException e) {
            return new SQLException();
        }
        return -1;
    }
    @Override
    public Integer delete(Prestamo p) {
        try {
        PreparedStatement ps = conexion.prepareStatement(DELETE);
        ps.setInt(1, p.getId());
        return ps.executeUpdate();
        } catch(SQLException e) {
            return new SQLException();
        }
        return -1;
    }
    public Prestamo crearPrestamo(ResultSet rs) {
        return new Prestamo(rs.get)
    }
}