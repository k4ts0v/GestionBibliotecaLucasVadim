package com.lvgvg.modelo.servicio;

import java.sql.SQLException;
import java.util.ArrayList;

import com.lvgvg.modelo.dao.AutorDAO;
import com.lvgvg.modelo.dao.DDL;
import com.lvgvg.modelo.dao.LibroAutorDAO;
import com.lvgvg.modelo.dao.LibroDAO;
import com.lvgvg.modelo.dto.Autor;
import com.lvgvg.modelo.dto.Libro;
import com.lvgvg.modelo.dto.LibroAutor;

public class BibliotecaService {
    private static DDL ddl;
    private static ArrayList<Libro> listaLibros;
    private static LibroDAO lDAO;
    private static ArrayList<Autor> listaAutores;
    private static AutorDAO aDAO;
    private static ArrayList<LibroAutor> listaLibrosAutores;
    private static LibroAutorDAO laDAO;

    public BibliotecaService() throws SQLException {
        init();
    }

    /**
     * Este método inicializa las listas en memoria mediante el ReadAll de los DAOs.
     * @throws SQLException Lanza una excepción si no se ha podido leer el contenido de la BD.
     */
    private static void init() throws SQLException {
        ddl = new DDL();
        lDAO = new LibroDAO();
        aDAO = new AutorDAO();
        laDAO = new LibroAutorDAO();
        ddl.run();
        try {
            listaLibros = lDAO.readAll();
            listaAutores = aDAO.readAll();
            listaLibrosAutores = laDAO.readAll();
        } catch (SQLException e) {
            System.out.println("Se ha producido un error cargando el contenido: " + e.getMessage());
        }
    }

    /**
     * Este método añade un libro de la lista y de la base de datos.
     * @param l Libro a añadir.
     * @return Integer - Resultado de la operación:
     * 1 -> Realizado correctamente.
     * -1 -> No realizado, ha habido algún error.
     * @throws SQLException Lanza una excepción si no se ha añadido correctamente de la BD.
     */
    private static Integer anhadirLibro(Libro l) {
        try {
            if (lDAO.create(l) == 1) {
                listaLibros.add(l);
                return 1;
            }
        } catch (SQLException e) {
            System.out.println("Se produjo un error añadiendo el libro: " + e.getMessage());
        }
        return -1;
    }

    /**
     * Este método lee a un libro de la lista y de la base de datos.
     * @param l Libro a leer.
     * @return Integer - Resultado de la operación:
     * 1 -> Realizado correctamente.
     * -1 -> No realizado, ha habido algún error.
     * @throws SQLException Lanza una excepción si no se ha leído correctamente de la BD.
     */
    private Integer readLibro(Libro l) {
        try {
            Libro lBD = lDAO.read(l);
            if (listaLibros.contains(lBD)) {
                System.out.println(lBD.toString());
                return 1;
            }
        } catch (SQLException e) {
            System.out.println("No se encontró el libro: " + e.getMessage());
        }
        return -1;
    }

    /**
     * Este método lee todo el contenido de la lista.
     * @return 1 - Si se ha leído correctamente.
     */
    private Integer readAllLibro() {
        listaLibros.forEach(System.out::println);
        return 1;
    }

    /**
     * Este método actualiza a un libro de la lista y de la base de datos.
     * @param l Libro a actualizar.
     * @return Integer - Resultado de la operación:
     * 1 -> Realizado correctamente.
     * -1 -> No realizado, ha habido algún error.
     * @throws SQLException Lanza una excepción si no se ha actualizado correctamente de la BD.
     */
    private Integer updateLibro(Libro l) {
        try {
            if (lDAO.update(l) == 1) {
                listaLibros.remove(l);
                listaLibros.add(l);
                return 1;
            }
        } catch (SQLException e) {
            System.out.println("Se produjo un error actualizando el libro: " + e.getMessage());
        }
        return -1;
    }

    /**
     * Este método borra a un libro de la lista y de la base de datos.
     * @param l Libro a borrar.
     * @return Integer - Resultado de la operación:
     * 1 -> Realizado correctamente.
     * -1 -> No realizado, ha habido algún error.
     * @throws SQLException Lanza una excepción si no se ha borrado correctamente de la BD.
     */
    private Integer borrarLibro(Libro l) {
        try {
            if (lDAO.delete(l) == 1) {
                listaLibros.remove(l);
                return 1;
            }
        } catch (SQLException e) {
            System.out.println("Se produjo un error borrando el libro: " + e.getMessage());
        }
        return -1;
    }

    /**
     * Este método añade un libro de la lista y de la base de datos.
     * @param l Libro a añadir.
     * @return Integer - Resultado de la operación:
     * 1 -> Realizado correctamente.
     * -1 -> No realizado, ha habido algún error.
     * @throws SQLException Lanza una excepción si no se ha añadido correctamente de la BD.
     */
    private static Integer anhadirAutor(Autor a) {
        try {
            if (aDAO.create(a) == 1) {
                listaAutores.add(a);
                return 1;
            }
        } catch (SQLException e) {
            System.out.println("Se produjo un error añadiendo el autor: " + e.getMessage());
        }
        return -1;
    }

    /**
     * Este método lee a un autor de la lista y de la base de datos.
     * @param l Autor a leer.
     * @return Integer - Resultado de la operación:
     * 1 -> Realizado correctamente.
     * -1 -> No realizado, ha habido algún error.
     * @throws SQLException Lanza una excepción si no se ha leído correctamente de la BD.
     */
    private Integer readAutor(Autor a) {
        try {
            Autor aBD = aDAO.read(a);
            if (listaAutores.contains(aBD)) {
                System.out.println(aBD.toString());
                return 1;
            }
        } catch (SQLException e) {
            System.out.println("No se encontró el autor: " + e.getMessage());
        }
        return -1;
    }

    /**
     * Este método lee todo el contenido de la lista.
     * @return 1 - Si se ha leído correctamente.
     */
    private Integer readAllAutor() {
        listaAutores.forEach(System.out::println);
        return 1;
    }

    /**
     * Este método actualiza a un autor de la lista y de la base de datos.
     * @param l Autor a actualizar.
     * @return Integer - Resultado de la operación:
     * 1 -> Realizado correctamente.
     * -1 -> No realizado, ha habido algún error.
     * @throws SQLException Lanza una excepción si no se ha actualizado correctamente de la BD.
     */
    private Integer updateAutor(Autor a) {
        try {
            if (aDAO.update(a) == 1) {
                listaAutores.remove(a);
                listaAutores.add(a);
                return 1;
            }
        } catch (SQLException e) {
            System.out.println("Se produjo un error actualizando el autor: " + e.getMessage());
        }
        return -1;
    }

    /**
     * Este método borra a un autor de la lista y de la base de datos.
     * @param l Autor a borrar.
     * @return Integer - Resultado de la operación:
     * 1 -> Realizado correctamente.
     * -1 -> No realizado, ha habido algún error.
     * @throws SQLException Lanza una excepción si no se ha borrado correctamente de la BD.
     */
    private Integer borrarAutor(Autor a) {
        try {
            if (aDAO.delete(a) == 1) {
                listaAutores.remove(a);
                return 1;
            }
        } catch (SQLException e) {
            System.out.println("Se produjo un error borrando el autor: " + e.getMessage());
        }
        return -1;
    }

    /**
     * Este método asigna un libro a un autor.
     * @param l Libro al que asignar el autor.
     * @param a Autor al que asignar el libro.
     * @return Integer - Resultado de la operación:
     * 1 -> Realizado correctamente.
     * -1 -> No realizado, ha habido algún error.
     * @throws Exception Lanza una excepción si no se ha borrado correctamente de la BD.
     */
    private static Integer anhadirLibroAutor(LibroAutor la, Libro l, Autor a) {
        try {
            if(existeLibro(l) && existeAutor(a)) {
                if (laDAO.create(la) == 1) {
                    listaLibrosAutores.add(la);
                    return 1;
                }
            }
        } catch (Exception e) {
            System.out.println("No se ha podido asignar el libro al autor: " + e.getMessage());
        }
        return -1;
    }

    /**
     * Este método comprueba si existe un libro en la lista de memoria y en la base de datos.
     * @param l Libro a comprobar.
     * @return Boolean - Resultado de la operación:
     * true -> Libro encontrado.
     * false -> Libro no encontrado.
     */
    private static boolean existeLibro(Libro l) {
        try {
            if (listaLibros.contains(l) || (lDAO.read(l) != null)) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("No se ha encontrado el libro: " + e.getMessage());
        }
        return false;
    }

    /**
     * Este método comprueba si existe un autor en la lista de memoria y en la base de datos.
     * @param a Autor a comprobar.
     * @return Boolean - Resultado de la operación:
     * true -> Autor encontrado.
     * false -> Autor no encontrado.
     */
    private static boolean existeAutor(Autor a) {
        try {
            if (listaAutores.contains(a) || (aDAO.read(a) != null)) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("No se ha encontrado el autor: " + e.getMessage());
        }
        return false;
    }
}