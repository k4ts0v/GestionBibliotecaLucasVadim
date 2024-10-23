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

    public BibliotecaService() {
        try {
            init();
        } catch (SQLException e) {
            System.out.println("Se ha producido un error cargando el contenido: " + e.getMessage());
        }
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
            throw new SQLException();
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
    public Integer anhadirLibro(Libro l) {
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
    public Integer mostrarLibro(Libro l) {
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
    public Integer mostrarTodosLibros() {
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
    public Integer actualizarLibro(Libro l) {
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
    public Integer borrarLibro(Libro l) {
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
    public Integer anhadirAutor(Autor a) {
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
    public Integer mostrarAutor(Autor a) {
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
    public Integer mostrarTodosAutores() {
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
    public Integer actualizarAutor(Autor a) {
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
    public Integer borrarAutor(Autor a) {
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
     * @throws Exception Lanza una excepción si no se ha asignado correctamente.
     */
    public Integer anhadirLibroAutor(Libro l, Autor a) {
        try {
            if(existeLibro(l) && existeAutor(a)) {
                LibroAutor la = new LibroAutor(a.getId(), l.getId());
                if (laDAO.create(la) == 1) {
                    listaLibrosAutores.add(la);
                    return 1;
                }
            }
        } catch (Exception e) {
            System.out.println("Error asignando el libro al autor: " + e.getMessage());
        }
        return -1;
    }


    /**
     * Este método lee una asignación de un libro a un autor.
     * @param l Libro de la asignación.
     * @param a Autor de la asignación.
     * @return Integer - Resultado de la operación:
     * 1 -> Realizado correctamente.
     * -1 -> No realizado, ha habido algún error.
     * @throws Exception Lanza una excepción si no se ha leído correctamente de la BD.
     */
    public Integer mostrarLibroAutor(Libro l, Autor a) {
        try {
            if(existeLibro(l) && existeAutor(a)) {
                LibroAutor la = new LibroAutor(a.getId(), l.getId());
                LibroAutor laBD = laDAO.read(la);
                System.out.println(la);
                System.out.println(laBD.toString());
                if (listaLibrosAutores.contains(laBD)) {
                    System.out.println(laBD.toString());
                    return 1;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al mostrar las asginaciones: " + e.getMessage());
        }
        return -1;
    }

    /**
     * Este método lee todas las asignaciones de un libro a un autor.
     * @param l Libro de la asignación.
     * @param a Autor de la asignación.
     * @return Integer - Resultado de la operación:
     * 1 -> Realizado correctamente.
     * -1 -> No realizado, ha habido algún error.
     * @throws Exception Lanza una excepción si no se ha leído correctamente de la BD.
     */
    public Integer mostrarTodosLibrosAutores() {
        listaLibrosAutores.forEach(System.out::println);
        return 1;
    }

    /**
     * Este método actualiza una asignación de un libro a un autor.
     * @param l Libro de la asignación.
     * @param a Autor de la asignación.
     * @return Integer - Resultado de la operación:
     * 1 -> Realizado correctamente.
     * -1 -> No realizado, ha habido algún error.
     * @throws Exception Lanza una excepción si no se ha actualizado correctamente en la BD.
     */
    public Integer actualizarLibroAutor(Libro l, Autor a) {
        try {
            if(existeLibro(l) && existeAutor(a)) {
                LibroAutor la = new LibroAutor(a.getId(), l.getId());
                if (laDAO.update(la) == 1) {
                    listaLibrosAutores.remove(la);
                    listaLibrosAutores.add(la);
                    return 1;
                }
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar la asignación: " + e.getMessage());
        }
        return -1;
    }

    /**
     * Este método borra una asignación de un libro a un autor.
     * @param l Libro de la asignación.
     * @param a Autor de la asignación.
     * @return Integer - Resultado de la operación:
     * 1 -> Realizado correctamente.
     * -1 -> No realizado, ha habido algún error.
     * @throws Exception Lanza una excepción si no se ha borrado correctamente de la BD.
     */
    public Integer borrarLibroAutor(Libro l, Autor a) {
        try {
            if(existeLibro(l) && existeAutor(a)) {
                LibroAutor la = new LibroAutor(a.getId(), l.getId());
                if (laDAO.delete(la) == 1) {
                    listaLibrosAutores.remove(la);
                    return 1;
                }
            }
        } catch (Exception e) {
            System.out.println("Error al borrar la asignación: " + e.getMessage());
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