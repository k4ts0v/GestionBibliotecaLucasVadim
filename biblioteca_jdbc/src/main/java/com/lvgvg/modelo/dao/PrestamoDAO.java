package com.lvgvg.modelo.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

import com.lvgvg.modelo.dto.Prestamo;
public class PrestamoDAO implements DAO<Prestamo> {
    private Connection conexion = JDBC.getConexion();
    private final String CREATE = "INSERT INTO Prestamo (fechaInicio, fechaFin, usuarioId, libroId) VALUES(?, ?, ?, ?)";
    private final String READ = "SELECT * FROM Prestamo WHERE id = ?";
    private final String READALL = "SELECT * FROM Prestamo";
    private final String UPDATE = "UPDATE Prestamo SET fechaInicio=?, fechaFin=?, usuarioId=?, libroId=? WHERE id = ?";
    private final String DELETE = "DELETE * FROM Prestamo WHERE id = ?";
    @Override
    public Integer create(Prestamo p) throws SQLException {
            try {
                PreparedStatement ps = conexion.prepareStatement(CREATE);
                ps.setString(1, p.getFechaInicio());
                ps.setString(2, p.getFechaFin());
                ps.setInt(3, p.getIdUsuario());
                ps.setInt(4, p.getIdLibro());
                return 1;
            } catch (SQLException e) {
                throw new SQLException();
            }

    }
    @Override
    public Prestamo read(Prestamo p) throws SQLException {
        try {
        PreparedStatement ps = conexion.prepareStatement(READ);
        ps.setInt(1, p.getId());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return crearPrestamo(rs);
        }
        } catch(SQLException e) {
            throw new SQLException();
        }
        return null;
    }
    @Override
    public ArrayList<Prestamo> readAll() throws SQLException {
        ArrayList<Prestamo> listaPrestamo= new ArrayList<>();
        try {
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(READALL);
        while (rs.next()) {
            listaPrestamo.add(crearPrestamo(rs));
        }
        } catch (SQLException e) {
            throw new SQLException();
        }
        return listaPrestamo;
    }
    @Override
    public Integer update(Prestamo p) throws SQLException {
        try {
            PreparedStatement ps = conexion.prepareStatement(UPDATE);
            ps.setString(1, p.getFechaInicio());
            ps.setString(2, p.getFechaFin());
            ps.setInt(3, p.getIdUsuario());
            ps.setInt(4, p.getIdLibro());
            ps.setInt(5, p.getId());
            return ps.executeUpdate();
        } catch(SQLException e) {
            throw new SQLException();
        }
    }
    @Override
    public Integer delete(Prestamo p) throws SQLException {
        try {
        PreparedStatement ps = conexion.prepareStatement(DELETE);
        ps.setInt(1, p.getId());
        return ps.executeUpdate();
        } catch(SQLException e) {
            throw new SQLException();
        }
    }
    public Prestamo crearPrestamo(ResultSet rs) throws SQLException {
        return new Prestamo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
    }
}