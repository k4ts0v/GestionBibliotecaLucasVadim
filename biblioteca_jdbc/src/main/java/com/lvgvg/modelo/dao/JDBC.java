/*
 * File: JDBC.java
 * Project: biblioteca_jdbc
 * File Created: Wednesday, 16th October 2024 1:10:53 PM
 * Author: Lucas Villa (k4ts0v@protonmail.com)
 */

package com.lvgvg.modelo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
    private static final String USER = "root"; // Usuario de la BD.
    private static final String PASSWORD = ""; // Contraseña del usuario especificado arriba.
    private static final String URL = "jdbc:mariadb://localhost:3306"; // URL de MariaDB.
    private static Connection conexion = null; // Objeto conexión para la BD.

    /**
     * Constructor privado que se conecta al SGBD usando los datos proporcionados.
     * Si falla, lanza un mensaje de error, pudiendo ser este por no encontrar la
     * librería necesaria o por no poder conectarse el gestor de base de datos pro
     * tener las credenciales incorrectas.
     */
    private JDBC() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Ha ocurrido un error accediendo a la base de datos: " + e.getMessage());
        }
    }

    /**
     * Genera una conexión al SGDB.
     * 
     * @return Connection - Objeto que permite la conexión a la base de datos.
     */
    public static Connection getConexion() {
        if (conexion == null) {
            new JDBC();
        }
        return conexion;
    }

    /**
     * Método que cierra la conexión al SGBD.
     * 
     * @throws SQLException Excepción lanzada en caso de que no se pueda cerrar la
     *                      conexión.
     */
    public static void close() throws SQLException {
        if (conexion != null) {
            conexion.close();
        }
    }
}
