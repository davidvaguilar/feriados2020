package cl.dyi.feriados.Model;

import java.util.ArrayList;

public class Feriado {
    private String nombre;
//    private String comentarios;
    private String fecha;
    private int irrenunciable;
    private String tipo;
    private ArrayList<Ley> leyes;

    public String getNombre() {
        return nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public int getIrrenunciable() {
        return irrenunciable;
    }

    public String getTipo() {
        return tipo;
    }

    public ArrayList<Ley> getLeyes() {
        return leyes;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setIrrenunciable(int irrenunciable) {
        this.irrenunciable = irrenunciable;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setLeyes(ArrayList<Ley> leyes) {
        this.leyes = leyes;
    }
}
