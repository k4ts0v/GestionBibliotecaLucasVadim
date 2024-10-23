package com.lvgvg.modelo.menus;

import java.util.Scanner;

import com.lvgvg.modelo.dto.Autor;
import com.lvgvg.modelo.dto.Libro;
import com.lvgvg.modelo.dto.LibroAutor;
import com.lvgvg.modelo.servicio.BibliotecaService;

public class MenuAutores {

    private static Scanner k = new Scanner(System.in); // Scanner para la entrada de texto.
    private BibliotecaService biblioteca = new BibliotecaService(); // Instancia de la clase de servicio.
    private MenuLibroAutor menuLibroAutor = new MenuLibroAutor();

    /**
     * Método público run, que es llamado desde otras clases. Llama al método
     * privado menu.
     */
    public void run() {
        menu();
    }

    /**
     * Método encargado de pintar el menú.
     */
    private void prtMenu() {
        System.out.println("""
                1 - Añadir un autor
                2 - Mostrar un autor
                3 - Mostrar todos los autores
                4 - Actualizar un autor
                5 - Borrar un autor
                6 - Asignar autor a un libro
                7 - Salir
                """);
    }

    /**
     * Este método gestiona toda la interacción con el usuario, redirigiéndole a los
     * menús correspondientes.
     */
    private void menu() {
        Boolean salida = false;
        while (!salida) {
            prtMenu();
            System.out.print("Selecciona una opción: ");
            Byte opcion = k.nextByte();
            switch (opcion) {
                case 1 -> biblioteca.anhadirAutor(getDatosAutor());
                case 2 -> biblioteca.mostrarAutor(getIdAutor());
                case 3 -> biblioteca.mostrarTodosAutores();
                case 4 -> biblioteca.actualizarAutor(getDatosIdAutor());
                case 5 -> biblioteca.borrarAutor(getIdAutor());
                case 6 -> menuLibroAutor.run();
                case 7 -> salida = true;
                default -> System.out.println("Introduce una opción válida.");
            }
        }
    }
    
    /**
     * Este método sirve para obtener los datos e ID del autor por consola.
     * @return Objeto Autor con los datos especificados.
     */
    private Autor getDatosIdAutor() {
        k.nextLine();
        System.out.println("Introduce los datos del autor:");
        System.out.println("ID: ");
        Integer id = k.nextInt();
        k.nextLine();
        System.out.println("Nombre: ");
        String nombre = k.nextLine();

        return new Autor(id, nombre);
    }

    /**
     * Este método sirve para obtener el ID del autor por consola.
     * @return Objeto Autor con el ID especificado.
     */
    static Autor getIdAutor() {
        //k.nextLine();
        System.out.println("Introduce los datos del autor:");
        System.out.println("ID: ");
        Integer id = k.nextInt();
        return new Autor(id, "");
    }

    /**
     * Este método sirve para obtener los datos del autor por consola.
     * @return Objeto Autor con los datos especificados.
     */
    private Autor getDatosAutor() {
        k.nextLine();
        System.out.println("Introduce los datos del autor:");
        System.out.println("Nombre: ");
        String nombre = k.nextLine();

        return new Autor(nombre);
    }

}
