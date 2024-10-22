/*
 * File: LibroAutorDAO.java
 * Project: biblioteca_jdbc
 * File Created: Tuesday, 22nd October 2024 8:31:22 AM
 * Author: Lucas Villa (k4ts0v@protonmail.com)
 * -----
 * Last Modified: Tuesday, 22nd October 2024 8:46:33 AM
 * Modified By: Lucas Villa (k4ts0v@protonmail.com)
 */



package com.lvgvg.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

import com.lvgvg.modelo.dto.LibroAutor;

public class LibroAutorDAO implements DAO<LibroAutor> {
    private Connection conexion = JDBC.getConexion();
    private final String CREATE = "INSERT INTO Libro_Autor VALUES(?,?)";
    private final String READ = "SELECT * FROM Libro_Autor WHERE id = ?";
    private final String READALL = "SELECT * FROM Libro_Autor";
    private final String UPDATE = "UPDATE Libro_Autor SET titulo=?, isbn=? WHERE id = ?";
    private final String DELETE = "DELETE * FROM Libro_Autor WHERE id = ?";

    /**
     * Este método añade un libroAutor a la BD.
     * @param j Objeto libroAutor a añadir.
     * @return Devuelve 1 si la operación se ha realizado correctamente.
     * @throws SQLException Lanza una SQLException si no se ha podido crear el libroAutor en la BD.
     */
    @Override
    public Integer create(LibroAutor l) throws SQLException {
        try {
            PreparedStatement ps = conexion.prepareStatement(CREATE);
            ps.setInt(1, l.getIdLibro());
            ps.setInt(2, l.getIdAutor());

            ps.executeUpdate();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            //throw new SQLException();
        }
        return -1;
    }

    /**
     * Este método lee el libroAutor especificado (por ID) de la BD.
     * @param id ID del libroAutor.
     * @return El objeto libroAutor que ha sido leído.
     * @throws SQLException Lanza una SQLException si no se ha podido leer el libroAutor de la BD.
     */
    @Override
    public LibroAutor read(LibroAutor la) throws SQLException {
        try  {
            PreparedStatement ps = conexion.prepareStatement(READ);
            ps.setInt(1, la.getIdLibro());
            ps.setInt(2, la.getIdAutor());
            ResultSet rs = ps.executeQuery();
            LibroAutor l = getLibroAutorRS(rs);
            return l;
        } catch (SQLException e) {
            throw new SQLException();
        }
    }

    /**
     * Este método lee todos los libroAutors que existen en la BD.
     * @return ArrayList<LibroAutor> Lista de los libroAutors existentes en la BD.
     * @throws SQLException Lanza una SQLException si no se ha podido leer el contenido de la BD.
     */
    @Override
    public ArrayList<LibroAutor> readAll() throws SQLException {
        ArrayList<LibroAutor> listaLibroAutores = new ArrayList<>();
        try {
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(READALL);
            while (rs.next()) {
                listaLibroAutores.add(getLibroAutorRS(rs));
            }
            return listaLibroAutores;
        } catch (SQLException e) {
            throw new SQLException();
        }
    }

    /**
     * Este método actualiza un libroAutor de la BD en base a su ID.
     * @param l LibroAutor a actualizar.
     * @return Devuelve 1 si se ha actualizado correctamente.
     * @throws SQLException Lanza una SQLException si no se ha podido actualizar el libroAutor.
     */
    @Override
    public Integer update(LibroAutor l) throws SQLException {
        try {
            PreparedStatement ps = conexion.prepareStatement(UPDATE);
            LibroAutor alBD = read(l);
            ps.setInt(1, alBD.getIdLibro());
            ps.setInt(2, alBD.getIdAutor());
            ps.execute();
            return 1;
        } catch (SQLException e) {
            throw new SQLException();
        }
    }

    /**
     * Este método borra un libroAutor de la BD en base a su ID.
     * @param l LibroAutor a borrar.
     * @return Devuelve 1 si el libroAutor se ha borrado correctamente.
     * @throws SQLException Lanza una SQLException si no se ha podido borrar el libroAutor.
     */
    @Override
    public Integer delete(LibroAutor l) throws SQLException {
        try {
            PreparedStatement ps = conexion.prepareStatement(DELETE);
            ps.setInt(1, l.getIdLibro());
            ps.setInt(2, l.getIdAutor());
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException();
        }
    }

    /**
     * Este método genera un libroAutor en base al ResultSet devuelto por una consulta.
     * @param rs - ResultSet de la consulta.
     * @return Objeto libroAutor con los datos de la consulta.
     * @throws SQLException Lanza una SQLException si no se ha podido crear el libroAutor.
     */
    public LibroAutor getLibroAutorRS(ResultSet rs) throws SQLException {
        return new LibroAutor(rs.getInt(1), rs.getInt(2));
    }
}