package com.lvgvg.modelo.dto;

public class Prestamo {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    private int id;
    private String fechaInicio;
    private String fechaFin;
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Libro))
            return false;
        Libro other = (Libro) obj;
        return Objects.equals(id, other.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}