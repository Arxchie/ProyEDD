/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructuras;

/**
 *
 * @author HP
 */
public class PilaDinamica
{

    private Nodo tope;

    /**
     * @return the tope
     */
    public Nodo getTope()
    {
        return tope;
    }

    /**
     * @param tope the tope to set
     */
    public void setTope(Nodo tope)
    {
        this.tope = tope;
    }

    public void inserta(Nodo n)
    {
        if (n == null)
        {
            System.out.println("no se puede insertar");
        } else
        {
            if (tope != null)
            {
                n.setSiguiente(tope);
            }
            tope = n;
        }
    }

    public Nodo elimina()
    {
        Nodo eliminado = tope;

        if (tope != null)
        {
            tope = tope.getSiguiente();
            eliminado.setSiguiente(null);
        } else
        {
            System.out.println("pila vacia");
        }
        return eliminado;
    }
    public static void main(String[] args)
    {
        Nodo<String> n1=new Nodo<>("A","A");
        Nodo<String> n2=new Nodo<>("B","B");
        Nodo<String> n3=new Nodo<>("C","C");
        PilaDinamica pilaDinamica=new PilaDinamica();
        pilaDinamica.inserta(n1);
        pilaDinamica.inserta(n2);
        pilaDinamica.inserta(n3);
        System.out.println(pilaDinamica.elimina().getEtiqueta());
        System.out.println(pilaDinamica.elimina().getEtiqueta());
        System.out.println(pilaDinamica.elimina().getEtiqueta());
    }
}
