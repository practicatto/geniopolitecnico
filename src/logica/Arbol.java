package logica;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Arbol implements Serializable {
    private Nodo raiz;

    public boolean isEmpty() {
        return raiz.getInfo() == null && raiz.getTipo() == null;
    }

    public Arbol() {
        raiz = new Nodo();
        raiz.setNo(null);
        raiz.setSi(null);
    }

    public Nodo getRaiz() {
        return this.raiz;
    }

    public boolean add(String child, String parent) {
        Nodo childnode = new Nodo(child);
        if (child == null)
            return false;
        if (parent == null && isEmpty()) {
            raiz = childnode;
            return true;
        }
        if (parent != null && searchNode(child.substring(3)) == null) {
            Nodo np = searchNode(parent.substring(3));
            if (np == null || (np.getSi() != null && np.getNo() != null))
                return false;
            if (np.getNo() == null)
                np.setNo(childnode);
            else
                np.setSi(childnode);
            return true;
        }
        return false;

    }

    private Nodo searchNode(String data) {
        return searchNode(data, raiz);
    }

    private Nodo searchNode(String data, Nodo n) {
        if (n == null)
            return n;
        if (n.getInfo().equals(data))
            return n;
        else {
            Nodo l = searchNode(data, n.getSi());
            return l != null ? l : searchNode(data, n.getNo());
        }
    }

    public int height() {
        return height(raiz);
    }

    private int height(Nodo n) {
        if (n == null)
            return 0;
        return 1 + Math.max(height(n.getNo()), height(n.getSi()));
    }

    public void cargarArbol(String nomArchivo) {
        String linea;
        Stack<Nodo> pilaGP = new Stack(); // pila con nodos del archivo donde el top siempre es la raiz

        try (BufferedReader br = new BufferedReader(new FileReader("src/data/" + nomArchivo))) {
            while ((linea = br.readLine()) != null) {
                Nodo current = new Nodo(linea);
                pilaGP.push(current);
                if (pilaGP.size() > 2 && current.esPregunta()) {
                    pilaGP.pop();
                    current.setNo(pilaGP.pop());
                    current.setSi(pilaGP.pop());
                    pilaGP.push(current);
                }
            }

            raiz = pilaGP.pop();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Arbol.class.getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ex) {
            Logger.getLogger(Arbol.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void inOrder() {
        inOrder(raiz);
    }

    private void inOrder(Nodo n) {
        if (n != null) {
            inOrder(n.getSi());
            System.out.print(n.getInfo());
            inOrder(n.getNo());
        }
    }

    @Override
    public String toString() {
        return "Arbol{" + "raiz=" + raiz + '}';
    }

    public Nodo searchParent(String hijo) {
        Nodo parent = new Nodo();
        if (hijo == null)
            return null;
        return searchParent(hijo, raiz, parent);
    }

    public Nodo searchParent(String hijo, Nodo node, Nodo parent) {
        if (node == null) {
            return null;
        } else if (!node.getInfo().equals(hijo)) {
            parent = searchParent(hijo, node.getSi(), node);
            if (parent == null) {
                parent = searchParent(hijo, node.getNo(), node);
            }
        }
        return parent;
    }

    public void posOrder(BufferedWriter bw) {
        posOrder(raiz, bw);
    }

    private void posOrder(Nodo n, BufferedWriter bw) {
        if (n != null) {
            try {
                posOrder(n.getSi(), bw);
                posOrder(n.getNo(), bw);
                if (n.esPregunta()) {
                    bw.write("#P " + n.getInfo() + "\n");
                } else {
                    bw.write("#R " + n.getInfo() + "\n");
                }

            } catch (IOException ex) {
                Logger.getLogger(Arbol.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void guardarArbol(String nomArchivo, Nodo n) {

        try (FileWriter writer = new FileWriter("src/data/" + nomArchivo);
                BufferedWriter bw = new BufferedWriter(writer)) {
            if (n != null) {
                posOrder(bw);
            }

        } catch (IOException ex) {
            Logger.getLogger(Arbol.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addArbol(Nodo hijo, String pregunta, Nodo newanimal, String siOno) {
        Nodo newhijo = new Nodo("#R " + hijo.getInfo());
        switch (siOno) {
            case "Si":
                hijo.setData(pregunta);
                hijo.setTipo(Nodo.Type.pregunta);
                hijo.setSi(newanimal);
                hijo.setNo(newhijo);
                break;

            case "No":
                hijo.setData(pregunta);
                hijo.setTipo(Nodo.Type.pregunta);
                hijo.setNo(newanimal);
                hijo.setSi(newhijo);
                break;

            default:
                break;
        }

    }

    public void posOrden() {

        posOrden(raiz);
    }

    private void posOrden(Nodo n) {
        if (n != null) {
            posOrden(n.getSi());
            posOrden(n.getNo());

            System.out.println(n.getInfo());
        }
    }

}
