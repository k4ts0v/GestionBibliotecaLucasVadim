/*
 * File: AutorDAO.java
 * Project: biblioteca_jdbc
 * File Created: Friday, 18th October 2024 11:48:45 AM
 * Author: Lucas Villa (k4ts0v@protonmaia.com)
 * -----
 * Last Modified: Friday, 18th October 2024 12:32:12 PM
 * Modified By: Lucas Villa (k4ts0v@protonmaia.com)
 */

package com.lvgvg.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

import com.lvgvg.modelo.dto.Autor;

public class AutorDAO implements DAO<Autor> {
    private Connection conexion = JDBC.getConexion();
    private final String CREATE = "INSERT INTO Autor VALUES(?,?)";
    private final String READ = "SELECT * FROM Autor WHERE id = ?";
    private final String READALL = "SELECT * FROM Autor";
    private final String UPDATE = "UPDATE Autor SET nombre=? WHERE id = ?";
    private final String DELETE = "DELETE FROM Autor WHERE id = ?";

    /**
     * Este método añade un autor a la BD.
     * 
     * @param j Objeto autor a añadir.
     * @return Devuelve 1 si la operación se ha realizado correctamente.
     * @throws SQLException Lanza una SQLException si no se ha podido crear el autor
     *                      en la BD.
     */
    @Override
    public Integer create(Autor a) throws SQLException {
        try {
            PreparedStatement ps = conexion.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, 0);
            ps.setString(2, a.getNombre());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()) {
                a.setId(rs.getInt(1));
                return 1;
            }
        } catch (SQLException e) {
            throw new SQLException();
        }
        return -1;
    }

    /**
     * Este método lee el autor especificado (por ID) de la BD.
     * 
     * @param id ID del autor.
     * @return El objeto autor que ha sido leído.
     * @throws SQLException Lanza una SQLException si no se ha podido leer el autor
     *                      de la BD.
     */
    @Override
    public Autor read(Autor a) throws SQLException {
        try {
            PreparedStatement ps = conexion.prepareStatement(READ);
            ps.setInt(1, a.getId());
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return getAutorRS(rs);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new SQLException();
        }
    }

    /**
     * Este método lee todos los autors que existen en la BD.
     * 
     * @return ArrayList<Autor> Lista de los autors existentes en la BD.
     * @throws SQLException Lanza una SQLException si no se ha podido leer el
     *                      contenido de la BD.
     */
    @Override
    public ArrayList<Autor> readAll() throws SQLException {
        ArrayList<Autor> listaAutors = new ArrayList<>();
        try {
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(READALL);
            while (rs.next()) {
                listaAutors.add(getAutorRS(rs));
            }
            return listaAutors;
        } catch (SQLException e) {
            throw new SQLException();
        }
    }

    /**
     * Este método actualiza un autor de la BD en base a su ID.
     * 
     * @param l Autor a actualizar.
     * @return Devuelve 1 si se ha actualizado correctamente.
     * @throws SQLException Lanza una SQLException si no se ha podido actualizar el
     *                      autor.
     */
    @Override
    public Integer update(Autor a) throws SQLException {
        try {
            PreparedStatement ps = conexion.prepareStatement(UPDATE);
            ps.setString(1, a.getNombre());
            ps.setInt(2, a.getId());
            ps.execute();
            return 1;
        } catch (SQLException e) {
            throw new SQLException();
        }
    }

    /**
     * Este método borra un autor de la BD en base a su ID.
     * 
     * @param l Autor a borrar.
     * @return Devuelve 1 si el autor se ha borrado correctamente.
     * @throws SQLException Lanza una SQLException si no se ha podido borrar el
     *                      autor.
     */
    @Override
    public Integer delete(Autor a) throws SQLException {
        try {
            PreparedStatement ps = conexion.prepareStatement(DELETE);
            ps.setInt(1, a.getId());
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException();
        }
    }

    /**
     * Este método genera un autor en base al ResultSet devuelto por una consulta.
     * 
     * @param rs - ResultSet de la consulta.
     * @return Objeto autor con los datos de la consulta.
     * @throws SQLException Lanza una SQLException si no se ha podido crear el
     *                      autor.
     */
    public Autor getAutorRS(ResultSet rs) throws SQLException {
        return new Autor(rs.getInt(1), rs.getString(2));
    }
}