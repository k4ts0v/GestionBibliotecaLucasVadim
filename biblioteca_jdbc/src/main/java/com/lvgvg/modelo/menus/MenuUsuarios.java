package com.lvgvg.modelo.menus;

public class MenuUsuarios {
    private static Scanner scanner = new Scanner(System.in); // Scanner para la entrada de texto.
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
                1 - Añadir un usuario
                2 - Mostrar un usuario
                3 - Mostrar todos los usuarios
                4 - Actualizar todos los usuarios
                5 - Borrar un usuario
                6 - Salir
                """);
    }
    private void menu() {
        boolean estado = false;
        while (!estado) {
            System.out.println("Opciones del menu Usuarios:");
            prtMenu();
            int opcion = scanner.nextInt();
            switch(opcion) {
                case 1 : biblioteca.anhadirUsuario(crearNuevoUsuario());
                case 2: biblioteca.leerUsuario(getIdUsuario());
                case 3: biblioteca.leerUsuarios();
                case 4: biblioteca.updateUsuario(getNuevoUsuario());
                case 5: biblioteca.borrarUsuario(getIdUsuario());
                case 6: estado = false;
                default:
                    System.out.println("Introduce una opcion valida");
            }
        }
        private Usuario crearNuevoUsuario() {
            scanner.nextLine();
            System.out.println("Introduce el nombre del usuario: ");
            String nombre = scanner.nextLine();
            return new Usuario(nombre);
        }
        private Usuario getIdUsuario() {
            scanner.nextLine();
            System.out.println("Introduce el id del usuario que desea leer: ");
            int id = scanner.nextInt();
            return new Usuario(id);
        }
        private Usuario getNuevoUsuario() {
            scanner.nextLine();
            System.out.println("Introduce el id del usuario que desea actualizar: ");
            int id = scanner.nextInt();
            System.out.println("Introduce el nuevo nombre del usuario");
            String nombre = scanner.nextLine();
            return new Usuario(id, nombre);
        }
        private Usuario getIdUsuario() {
            scanner.nextLine();
            System.out.println("Introduce el id del usuario que desea borrar: ");
            int id = scanner.nextInt();
            return new Usuario(id);
        }
    }


}
