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
    private DDL ddl;
    private ArrayList<Libro> listaLibros;
    private LibroDAO lDAO;
    private ArrayList<Autor> listaAutores;
    private AutorDAO aDAO;
    private ArrayList<LibroAutor> listaLibrosAutores;
    private LibroAutorDAO laDAO;
    private PrestamoDAO pDAO;
    private UsuarioDAO uDAO;
    private ArrayList<User> listaUsuarios;
    private ArrayList<Prestamo> listaPrestamos;

    public BibliotecaService() {
        init();
    }

    /**
     * Este método inicializa las listas en memoria mediante el ReadAll de los DAOs.
     * @throws SQLException Lanza una excepción si no se ha podido leer el contenido de la BD.
     */
    private void init() {
        ddl = new DDL();
        lDAO = new LibroDAO();
        aDAO = new AutorDAO();
        laDAO = new LibroAutorDAO();
        pDAO = new PrestamoDAO();
        uDAO = new UsuarioDAO();
        try {
            ddl.run();
            listaLibros = lDAO.readAll();
            listaAutores = aDAO.readAll();
            listaLibrosAutores = laDAO.readAll();
            listaUsuarios = lDAO.readAll();
            listaPrestamos = pDAO.readAll();

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
    private Integer anhadirLibro(Libro l) {
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
     * @throws Exception Lanza una excepción si no se ha borrado correctamente de la BD.
     */
    private Integer anhadirLibroAutor(Libro l, Autor a) {
        try {
            if(existeLibro(l) && existeAutor(a)) {
                listaLibrosAutores.add(new LibroAutor(l.getId(), a.getId()));
                return 1;
            }
        } catch (Exception e) {
            System.out.println("no se ha podido asignar el libro al autor: " + e.getMessage());
        }
        return -1;
    }
    private Integer anhadirUsuario(Usuario u) {
        try {
            if (uDAO.create == 1)
                listaUsuarios.add(u);
          return 1;
        } catch(SQLException )
        { System.out.println(e.getMessage());}
        return -1;
    }
    private Integer leerUsuario(Usuario u) {
        try {
            Usuario u = uDAO.read(u);
            if (listaUsuarios.contains(u)) {
                System.out.println(u.toString());
            } return 1;
           if (uDAO.read(u) == 1) {

           }
        } catch(SQLException e)
        {System.out.println(e.getMessage());}
        return -1;
    }
    private void leerUsuarios() {
        for(Usuario u : listaUsuarios)
            System.out.println(u.toString());
    }
    private Integer updateUsuario(Usuario u) {
        try {
        if (uDao.update(u) == 1) {
            int index = listaUsuarios.indexOf(u);
            listaUsuarios.set(index, u);
        } return 1;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }
    private Integer borrarUsuario(Usuario u) {
        try {
            if (uDao.delete(u) == 1) {
                listaUsuarios.remove(u);
            } return 1;

        } catch(SQLException e) {
            {
                System.out.println(e.getMessage());
            }
        }
        return -1;
    }

    private Integer anhadirPrestamo(Prestamo p) {
        try {
            if (pDAO.create == 1)
                listaPrestamos.add(p);
            return 1;
        } catch(SQLException )
        { System.out.println(e.getMessage());}
        return -1;
    }
    private Integer leerPrestamo(Prestamo p) {
        try {
            Prestamo p = pDAO.read(p);
            if (listaPrestamoss.contains(p)) {
                System.out.println(p.toString());
            } return 1;
            if (pDAO.read(p) == 1) {

            }
        } catch(SQLException e)
        {System.out.println(e.getMessage());}
        return -1;
    }
    private void leerPrestamos() {
        for(Prestamo : listaPrestamos)
            System.out.println(p.toString());
    }
    private Integer updateUsuario(Prestamo p) {
        try {
            if (pDao.update(p) == 1) {
                int index = listaPrestamos.indexOf(p);
                listaPrestamoss.set(index, p);
            } return 1;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }
    private Integer borrarPrestamo(Prestamo p) {
        try {
            if (pDao.delete(p) == 1) {
                listaPrestamos.remove(u);
            } return 1;

        } catch(SQLException e) {
            {
                System.out.println(e.getMessage());
            }
        }
        return -1;
    }
    private boolean existeUsuario(Usuario u) {
        try {
        if (listaUsuarios.contains(u) || (uDAO.read(u) != null)) {
            return true;
        }
        } catch(SQLException e) {
            {
                System.out.println(e.getMessage());
            }
        }
        return false;
    }
    private boolean existePrestamo() {
        try {
            if (listaPrestamos.contains(u) || (pDAO.read(p) != null)) {
                return true;
            }
        } catch(SQLException e) {
            {
                System.out.println(e.getMessage());
            }
        }
        return false;
    }
    private boolean existePrestamo(Prestamo p) {
        try {
            if
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Este método comprueba si existe un libro en la lista de memoria y en la base de datos.
     * @param l Libro a comprobar.
     * @return Boolean - Resultado de la operación:
     * true -> Libro encontrado.
     * false -> Libro no encontrado.
     */
    private boolean existeLibro(Libro l) {
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
    private boolean existeAutor(Autor a) {
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