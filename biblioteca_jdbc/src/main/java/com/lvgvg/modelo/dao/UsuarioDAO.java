package com.lvgvg.modelo.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
public class UsuarioDAO implements DAO<UsuarioDAO> {
    private Connection conexion = JDBC.getConexion();
    private final String CREATE = "INSERT INTO Usuario VALUES(?)";
    private final String READ = "SELECT * FROM Usuario WHERE id = ?";
    private final String READALL = "SELECT * FROM Usuario";
    private final String UPDATE = "UPDATE Usuario SET nombre=? WHERE id = ?";
    private final String DELETE = "DELETE * FROM Usuario WHERE id = ?";

    @Override
    public Integer create(Usuario u) throws SQLException{
        PreparedStatement ps = conexion.prepareStatement(CREATE);
        ps.setString(1, u.getNombre());
        return ps.executeUpdate();
    }
    @Override
    public Usuario read(Usuario u) throws SQLException {
        try {
            PreparedStatement ps = conexion.prepareStatement(READ);
            ps.setInt(1, u.getId());
            ResultSet rs = ps.executeQuery();
            Usuario usuario = crearUsuario(rs);
            return usuario;
        } catch(SQLException e) {
            throw new SQLException();
        }
    }
    @Override
    public ArrayList<Usuario> readAll() throws SQLException {
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        try {
            Statement st = conexion.createStatement;
            ResultSet rs = st.executeQuery(READALL);
            while(rs.next) {
                listaUsuarios.add(crearUsuario(rs));
            }
        } catch (SQLException e) {
            return new SQLException();
        }

    }
    @Override
    public Integer update(Usuario u) throws SQLException{
        try {
            PreparedStatement ps = conexion.prepareStatement(UPDATE);
            ps.setString(1, u.getNombre());
            ps.setInt(2, u.getId());
            return ps.executeUpdate();
        } catch (SQLException e)
            return new SQLException();
    }
    @Override
    public Integer delete(Usuarui u) throws SQLException {
        try {
            PreparedStatement ps = conexion.prepareStatement(DELETE);
            ps.setInt(1, u.getId());
            return ps.executeUpdate();
        } catch(SQLException e)
            return new SQLException;
    }
    public crearUsuario(ResultSet rs) {
        return new Usuario(rs.getString(2));
    }
}
