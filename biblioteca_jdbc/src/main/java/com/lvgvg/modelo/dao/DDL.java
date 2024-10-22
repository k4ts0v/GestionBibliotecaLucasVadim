/*
 * File: DDL.java
 * Project: biblioteca_jdbc
 * File Created: Wednesday, 16th October 2024 1:20:13 PM
 * Author: Lucas Villa (k4ts0v@protonmail.com)
 * -----
 * Last Modified: Thursday, 17th October 2024 1:56:36 PM
 * Modified By: Lucas Villa (k4ts0v@protonmail.com)
 */

package com.lvgvg.modelo.dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DDL {
    private static Connection conexion = JDBC.getConexion();
    private static final String DB = "Biblioteca";
    private static String[] tablas = { "Usuario", "Autor", "Libro", "Prestamo", "Libro_Autor" };
    private static String[][] campos = { { "id", "nombre" },
            { "id", "nombre" },
            { "id", "titulo", "isbn" },
            { "id", "fechaInicio", "fechaFin", "usuarioId", "libroId" },
            { "idLibro", "idAutor" } };
    private static String[][] tipoDatos = { { "INT", "VARCHAR(100)" },
            { "INT", "VARCHAR(100)" },
            { "INT", "VARCHAR(100)", "VARCHAR(20)" },
            { "INT", "DATE", "DATE", "INT", "INT" },
            { "INT", "INT" } };
    private static String[][] modsCampo = { { "AUTO_INCREMENT PRIMARY KEY", "NOT NULL" },
            { "AUTO_INCREMENT PRIMARY KEY", "NOT NULL" },
            { "AUTO_INCREMENT PRIMARY KEY", "NOT NULL", "NOT NULL" },
            { "AUTO_INCREMENT PRIMARY KEY", "NOT NULL", "NOT NULL", "NOT NULL", "NOT NULL" },
            { "NOT NULL", "NOT NULL" } }; // Modificadores de cada campo.
    private static String[][] modsTabla = { {}, {}, {},
            { "FOREIGN KEY (usuarioId) REFERENCES Usuario(id) ON DELETE CASCADE",
                    "FOREIGN KEY (libroId) REFERENCES Libro(id) ON DELETE CASCADE" },
            { "PRIMARY KEY (idLibro, idAutor)",
                    "FOREIGN KEY (idLibro) REFERENCES Libro(id) ON DELETE CASCADE",
                    "FOREIGN KEY (idAutor) REFERENCES Autor(id) ON DELETE CASCADE" } }; // Modificadores relativos a la tabla.

    /**
     * Método que crea la base de datos.
     */
    private static void createDB() {
        try (Statement st = conexion.createStatement()) {
            st.execute(String.format("CREATE DATABASE IF NOT EXISTS %s;", DB));
            st.execute(String.format("USE %s;", DB));
        } catch (SQLException e) {
            System.out.println("Error en la creación de la base de datos: " + e.getMessage());
        }
    }

    /**
     * Método que crea las tablas.
     */
    private static void createTables() {
        try (Statement st = conexion.createStatement()) {
            // Itera sobre el array de tablas y crea la sentencia SQL de manera dinámica.
            for (int i = 0; i < tablas.length; i++) {
                StringBuilder sql = new StringBuilder();
                sql.append(String.format("CREATE TABLE IF NOT EXISTS %s (", tablas[i]));

                // Itera sobre los campos para añadir el nombre, tipo de dato y modificadores de
                // cada campo.
                for (int j = 0; j < campos[i].length; j++) {
                    sql.append(String.format("%s %s %s", campos[i][j], tipoDatos[i][j], modsCampo[i][j]));
                    // Añade la coma al final de cada campo excepto el último
                    if (j < campos[i].length - 1) {
                        sql.append(", ");
                    }
                }

                // Añadir los modificadores de tabla (ej. las claves foráneas).
                if (modsTabla[i].length > 0) {
                    sql.append(", ");
                    for (String mod : modsTabla[i]) {
                        sql.append(mod).append(", ");
                    }
                    // Elimina la última coma sobrante
                    sql.setLength(sql.length() - 2);
                }

                // Cierra la sentencia
                sql.append(");");
                st.execute(sql.toString());
            }
        } catch (SQLException e) {
            System.out.println("Error en la creación de las tablas: " + e.getMessage());
        }
    }

    /**
     * Método que verifica si una tabla contiene datos.
     * @param tableName Nombre de la tabla.
     * @return true si la tabla tiene datos, false en caso contrario.
     */
    private static boolean hasData(String tableName) {
        String query = String.format("SELECT COUNT(*) FROM %s", tableName);
        try (Statement st = conexion.createStatement(); ResultSet rs = st.executeQuery(query)) {
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar datos en la tabla " + tableName + ": " + e.getMessage());
        }
        return false;
    }

    /**
     * Método que extrae el nombre de la tabla de una sentencia SQL.
     * @param sentence La sentencia SQL.
     * @return El nombre de la tabla o null si no se pudo determinar.
     */
    private static String extractTableName(String sentence) {
        if (sentence == null || sentence.trim().isEmpty()) {
            return null;
        }
        // Eliminar comentarios que empiezan con -- o /* */
        sentence = sentence.replaceAll("--.*", "").replaceAll("/\\*.*?\\*/", "").trim();
        // Verificar que es una sentencia INSERT INTO
        if (!sentence.toUpperCase().startsWith("INSERT INTO")) {
            return null;
        }
        // Remover el prefijo 'INSERT INTO' para enfocarnos en la parte del nombre de la
        // tabla
        sentence = sentence.substring(11).trim();
        // Dividir la sentencia usando espacios o paréntesis
        String[] parts = sentence.split("\\s+|\\(");
        if (parts.length > 0) {
            return parts[0].trim(); // El primer elemento después de 'INSERT INTO' es el nombre de la tabla
        }
        // Si no se pudo obtener el nombre, retorna null
        System.out.println("No se pudo extraer el nombre de la tabla de la sentencia: " + sentence);
        return null;
    }

    /**
     * Método que importa un archivo SQL y ejecuta las sentencias.
     * @param file Ruta del archivo SQL.
     * @throws IOException Si hay problemas con el archivo.
     */
    private static void importSQL(String file) throws IOException {
        String sqlScript = new String(Files.readAllBytes(Paths.get(file)));
        String[] stc = sqlScript.split(";");

        try (Statement st = conexion.createStatement()) {
            for (String sentence : stc) {
                if (!sentence.trim().isEmpty()) { // Evita ejecutar sentencias vacías
                    String tableName = extractTableName(sentence);
                    if (tableName == null) {
                        continue;
                    }
                    if (!hasData(tableName)) {
                        st.executeUpdate(sentence);
                    } else {
                        System.out.printf("Omitiendo la importación de datos en la tabla %s, ya contiene datos.%n",
                                tableName);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error importando los datos: " + e.getMessage());
        }
    }

    /**
     * Método que ejecuta todos los métodos de creación de la BD, encargándose de crear toda la estructura.
     */
    public void run() {
        createDB();
        createTables();
        try {
            importSQL("./src/main/java/com/lvgvg/modelo/datos/bdBiblioteca.sql");
        } catch (IOException e) {
            System.out.println("Error importando los datos.");
        }
    }
}