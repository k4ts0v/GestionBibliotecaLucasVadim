/*
 * File: Menu.java
 * Project: menus
 * File Created: Tuesday, 22nd October 2024 6:23:03 PM
 * Author: Lucas Villa (k4ts0v@protonmail.com)
 * -----
 * Last Modified: Tuesday, 22nd October 2024 6:43:18 PM
 * Modified By: Lucas Villa (k4ts0v@protonmail.com)
 */

package com.lvgvg.modelo.menus;

import java.util.Scanner;

public class Menu {

    private Scanner k = new Scanner(System.in); // Scanner para la entrada de texto.
    private MenuLibros menuLibros = new MenuLibros(); // Menú de libros.
    private MenuAutores menuAutores = new MenuAutores(); // Menú de autores.
    private MenuUsuarios menuUsuarios = new MenuUsuarios(); // Menú de usuarios.
    private MenuPrestamos menuPrestamos = new MenuPrestamos(); // Menú de préstamos.

    /**
     * Método público run, que es llamado desde otras clases. Llama al método privado menu.
     */
    public void run() {
        menu();
    }

    /**
     * Método encargado de pintar el menú.
     */
    private void prtMenu() {
        System.out.println("""
                1 - Menú de libros
                2 - Menú de autores
                3 - Menú de usuarios
                4 - Menú de préstamos
                """);
    }

    /**
     * Este método gestiona toda la interacción con el usuario, redirigiéndole a los menús correspondientes.
     */
    private void menu() {
        Boolean salida = false;
        while (!salida) {
            prtMenu();
            System.out.print("Selecciona una opción: ");
            Byte opcion = k.nextByte();
            switch (opcion) {
                case 1 -> menuLibros.run();
                case 2 -> menuAutores.run();
                case 3 -> menuUsuarios.run();
                case 4 -> menuPrestamos.run();
                case 5 -> salida = true;
                default -> System.out.println("Introduce una opción válida.");
            }
        }
    }

}
