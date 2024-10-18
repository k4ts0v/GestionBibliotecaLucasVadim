/*
 * File: Autor.java
 * Project: biblioteca_jdbc
 * File Created: Thursday, 17th October 2024 2:02:17 PM
 * Author: Lucas Villa (k4ts0v@protonmail.com)
 * -----
 * Last Modified: Thursday, 17th October 2024 6:49:13 PM
 * Modified By: Lucas Villa (k4ts0v@protonmail.com)
 */


package com.lvgvg.modelo.dto;

import java.util.Objects;

// Clase autor, que representa al autor de un libro.
public class Autor {
    private Integer id; // Id del autor.
    private String nombre; // Nombre del autor.

    /**
     * Constructor del Autor.
     * @param id Id del autor
     * @param nombre Nombre del autor.
     */
    public Autor(Integer id, String nombre) {
        setId(id);
        setNombre(nombre);
    }

    /**
     * Constructor del Autor.
     * @param nombre Nombre del autor.
     */
    public Autor(String nombre) {
        setNombre(nombre);
    }

    // Getters y setters

    /**
     * Getter que devuelve el id del autor.
     * @return Id del autor.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Setter que asigna el id a una instancia de autor.
     * @param id Id a asignar.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Getter que devuelve el nombre del autor.
     * @return Nombre del autor.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Setter que asigna el nombre al una instancia de autor.
     * @param nombre Nombre a asignar.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Este método genera el hashcode, que es un integer generado por un algoritmo
     * de hashing. Para ello, ha usado el ID.
     *
     * @return Hashcode generado en base al ID del autor.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Compara dos objetos para ver si son iguales.
     * En este caso, solo son iguales si son dos instancias de Autor y poseen el
     * mismo ID.
     *
     * @param a0 - La otra instancia de autor a usar para la comparación.
     * @return boolean - Si son iguales, devuelve verdadero, si no, falso.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Autor))
            return false;
        Autor other = (Autor) obj;
        return Objects.equals(id, other.id);
    }

    /**
     * Método toString que genera una String con toda la información del objeto.
     *
     * @return String formateada con toda la información de la instancia de la clase
     *         Autor.
     */
    @Override
    public String toString() {
        return String.format("Autor [id=%s, nombre=%s]", id, nombre);
    }
}