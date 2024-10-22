package com.lvgvg.modelo.menus;

import java.util.Scanner;

import com.lvgvg.modelo.dto.Libro;
import com.lvgvg.modelo.servicio.BibliotecaService;

public class MenuLibros {
    private Scanner k = new Scanner(System.in); // Scanner para la entrada de texto.
    private BibliotecaService biblioteca = new BibliotecaService();

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
                1 - Añadir un libro
                2 - Mostrar un libro
                3 - Mostrar todos los libros
                4 - Actualizar un libro
                5 - Borrar un libro
                6 - Salir
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
                case 1 -> biblioteca.anhadirLibro(getDatosLibro());
                case 2 -> biblioteca.mostrarLibro(getIdLibro());
                case 3 -> biblioteca.mostrarTodosLibros();
                case 4 -> biblioteca.actualizarLibro(getDatosIdLibro());
                case 5 -> biblioteca.borrarLibro(getIdLibro());
                case 6 -> salida = true;
                default -> System.out.println("Introduce una opción válida.");
            }
        }
    }

    /**
     * Este método sirve para obtener los datos e ID del libro por consola.
     * @return Objeto Libro con los datos especificados.
     */
    Libro getDatosIdLibro() {
        k.nextLine();
        System.out.println("Introduce los datos del libro:");
        System.out.println("ID: ");
        Integer id = k.nextInt();
        k.nextLine();
        System.out.println("Título: ");
        String titulo = k.nextLine();
        System.out.println("ISBN:");
        String isbn = k.nextLine();

        return new Libro(id, titulo, isbn);
    }

    /**
     * Este método sirve para obtener el ID del libro por consola.
     * @return Objeto Libro con el ID especificado.
     */
    Libro getIdLibro() {
        k.nextLine();
        System.out.println("Introduce los datos del libro:");
        System.out.println("ID: ");
        Integer id = k.nextInt();
        return new Libro(id, "", "");
    }

    /**
     * Este método sirve para obtener los datos del libro por consola.
     * @return Objeto Libro con los datos especificados.
     */
    Libro getDatosLibro() {
        k.nextLine();
        System.out.println("Introduce los datos del libro:");
        System.out.println("Título: ");
        String titulo = k.nextLine();
        System.out.println("ISBN:");
        String isbn = k.nextLine();

        return new Libro(titulo, isbn);
    }
}