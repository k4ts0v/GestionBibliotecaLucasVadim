package com.lvgvg.modelo.dto;

import java.util.Objects;

// Clase LibroAutor, que sirve como tabla intermedia para la relación entre Libro y Autor.
public class LibroAutor {
    private Integer idLibro; // Id del libro.
    private Integer idAutor; // Id del autor.

    public LibroAutor(Integer idLibro, Integer idAutor) {
        setIdLibro(idLibro); // Id del libro.
        setIdAutor(idAutor); // Id del autor.
    }

    // Getters y setters.

    /**
     * Getter que devuelve el id del autor.
     *
     * @return Id del autor.
     */
    public Integer getIdAutor() {
        return idAutor;
    }

    /**
     * Setter que asigna el id del autor a una instancia de LibroAutor.
     *
     * @param id Id del autor a asignar.
     */
    public void setIdAutor(Integer idAutor) {
        this.idAutor = idAutor;
    }

    /**
     * Getter que devuelve el id del libro.
     *
     * @return Id del libro.
     */
    public Integer getIdLibro() {
        return idLibro;
    }

    /**
     * Setter que asigna el id del libro a una instancia de LibroAutor.
     *
     * @param id Id del libro a asignar.
     */
    public void setIdLibro(Integer idLibro) {
        this.idLibro = idLibro;
    }

    /**
     * Este método genera el hashcode, que es un integer generado por un algoritmo
     * de hashing. Para ello, ha usado ambos IDs.
     *
     * @return int - Hashcode generado en base los IDs.
     */
    @Override
    public int hashCode() {
        return Objects.hash(idAutor, idLibro);
    }

    /**
     * Compara dos objetos para ver si son iguales.
     * En este caso, solo son iguales si son dos instancias de LibroAutor y poseen los
     * mismos IDs.
     *
     * @param e0 - La otra instancia de libro a usar para la comparación.
     * @return boolean - Si son iguales, devuelve verdadero, si no, falso.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof LibroAutor))
            return false;
        LibroAutor other = (LibroAutor) obj;
        return Objects.equals(idAutor, other.idAutor) && Objects.equals(idLibro, other.idLibro);
    }

    /**
     * Método toString que genera una String con toda la información del objeto.
     *
     * @return String formateada con toda la información de la instancia de la clase
     *         LibroAutor.
     */
    @Override
    public String toString() {
        return String.format("LibroAutor [idAutor=%s, idLibro=%s]", idAutor, idLibro);
    }
}