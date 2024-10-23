package com.lvgvg.modelo.menus;

import java.util.Scanner;

import com.lvgvg.modelo.dto.Libro;
import com.lvgvg.modelo.servicio.BibliotecaService;

public class MenuLibroAutor {
    private Scanner k = new Scanner(System.in); // Scanner para la entrada de texto.
    private BibliotecaService biblioteca = new BibliotecaService();
    // No se añaden los constructores de los menús, porque esto provocaría una dependecia cíclica, que deriva en un stackOverflowError.

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
                1 - Asociar un libro a un autor
                2 - Mostrar las asociaciones existentes
                3 - Mostrar todas las asociaciones existentes
                4 - Actualizar una asociación
                5 - Borrar una asociación
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
                case 1 -> biblioteca.anhadirLibroAutor(MenuLibros.getIdLibro(), MenuAutores.getIdAutor());
                case 2 -> biblioteca.mostrarLibroAutor(MenuLibros.getIdLibro(), MenuAutores.getIdAutor());
                case 3 -> biblioteca.mostrarTodosLibrosAutores();
                case 4 -> biblioteca.actualizarLibroAutor(MenuLibros.getIdLibro(), MenuAutores.getIdAutor());
                case 5 -> biblioteca.borrarLibroAutor(MenuLibros.getIdLibro(), MenuAutores.getIdAutor());
                case 6 -> salida = true;
                default -> System.out.println("Introduce una opción válida.");
            }
        }
    }
}