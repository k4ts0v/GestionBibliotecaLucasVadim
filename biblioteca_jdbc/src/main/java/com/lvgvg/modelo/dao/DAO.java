/*
 * File: DAO.java
 * Project: crudBiblioteca
 * File Created: Thursday, 17th October 2024 2:07:33 PM
 * Author: Lucas Villa (k4ts0v@protonmail.com)
 * -----
 * Last Modified: Thursday, 17th October 2024 5:16:56 PM
 * Modified By: Lucas Villa (k4ts0v@protonmail.com)
 */

package com.lvgvg.modelo.dao;

import java.util.ArrayList;

/**
 * Interfaz genérica que sirve para hacer las operaciones CRUD sobre la base de
 * datos.
 */
public interface DAO<T> {
    /**
     * @param t Objeto a añadir a la base de datos.
     * @return Entero que simboliza el resultado de la operación:
     *         1 -> Realizado correctamente.
     *         -1 -> No realizado correctamente.
     */
    Integer create(T t);

    /**
     * @param id Id del objeto a leer de la base de datos.
     * @return Objeto leído de la base de datos.
     */
    T read(Integer id);

    /**
     * @return Arraylist con el contenido de la tabla de la base de datos del objeto
     *         T.
     */
    ArrayList<T> readAll();

    /**
     * @param id Id del objeto a actualizar en la base de datos.
     * @return Entero que simboliza el resultado de la operación:
     *         1 -> Realizado correctamente.
     *         -1 -> No realizado correctamente.
     */
    Integer update(Integer id);

    /**
     * @param id Id del objeto a borrar en la base de datos.
     * @return Entero que simboliza el resultado de la operación:
     *         1 -> Realizado correctamente.
     *         -1 -> No realizado correctamente.
     */
    Integer delete(Integer id);
}
