package logica;

import java.util.Objects;


public class Nodo {
    private String data;
    private Nodo si;
    private Nodo no;
    private Type tipo;

    enum Type {
        pregunta, respuesta;
    }

    public Nodo(String linea) {
        setInfo(linea);
    }

    public Nodo(String data, Type tipo) {
        setData(data);
        setTipo(tipo);
    }

    public Nodo() {
        this.data = null;
        this.tipo = null;

    }

    public boolean esPregunta() {
        return tipo == Type.pregunta;
    }

    // se usa cuando se crea el arbol desde el archivo
    // mientras lee verifica si es respuesta y le asigna un tipo y corta el prefijo
    public void setInfo(String data) {
        if (data.startsWith("#R")) {
            this.tipo = Type.respuesta;
        } else {
            this.tipo = Type.pregunta;
        }
        this.data = data.substring(3);
    }

    public String getInfo() {
        return data;
    }

    public Nodo getSi() {
        return si;
    }

    public Nodo getNo() {
        return no;
    }

    public Type getTipo() {
        return tipo;
    }

    public void setSi(Nodo si) { // parte izq
        this.si = si;
    }

    public void setNo(Nodo no) { // parte derecha
        this.no = no;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setTipo(Type tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Nodo{" + "data=" + data + ", tipo=" + tipo + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.data);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Nodo other = (Nodo) obj;
        return true;
    }
}
