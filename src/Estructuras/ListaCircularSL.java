/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructuras;

import java.io.Serializable;

/**
 *
 * @author HP
 */
public class ListaCircularSL implements Serializable
{

    private Nodo r;

    /**
     * @return the r
     */
    public Nodo getR()
    {
        return r;
    }

    /**
     * @param r the r to set
     */
    public void setR(Nodo r)
    {
        this.r = r;
    }

    public void inserta(Nodo n)
    {
        if (n != null)
        {
            if (r == null)
            {
                r = n;
                r.setSiguiente(n);
            } else
            {
                if (n.getEtiqueta().compareTo(r.getSiguiente().getEtiqueta()) < 0 || n.getEtiqueta().compareTo(r.getEtiqueta()) > 0)
                {
                    n.setSiguiente(r.getSiguiente());
                    r.setSiguiente(n);
                    if (n.getEtiqueta().compareTo(r.getEtiqueta()) > 0)
                    {
                        r = n;//r.getSiguiente()
                    }
                } else
                {
                    Nodo aux = r;
                    while (true)
                    {
                        if (aux.getSiguiente().getEtiqueta().compareTo(n.getEtiqueta()) > 0)
                        {
                            n.setSiguiente(aux.getSiguiente());
                            aux.setSiguiente(n);
                            break;
                        }
                        aux = aux.getSiguiente();
                    }
                }
            }
        } else
        {
            System.out.println("El nodo es nulo");
        }
    }

    public Nodo elimina(String etiqueta)
    {
        Nodo n = null;
        if (etiqueta == null)
        {
            System.out.println("inserte una etiqueta valida");
            return n;
        }
        if (r != null)
        {
            if (etiqueta.compareTo(r.getSiguiente().getEtiqueta()) >= 0 && etiqueta.compareTo(r.getEtiqueta()) <= 0)
            {

                if (etiqueta.compareTo(r.getSiguiente().getEtiqueta()) == 0)
                {
                    n = r.getSiguiente();
                    r.setSiguiente(n.getSiguiente());
                    n.setSiguiente(null);
                    if (n == r)
                    {
                        r = null;
                    }
                } else
                {
                    Nodo aux = r.getSiguiente();
                    while (aux != r)
                    {
                        if (etiqueta.compareTo(aux.getSiguiente().getEtiqueta()) >= 0)
                        {
                            if (etiqueta.compareTo(aux.getSiguiente().getEtiqueta()) == 0)
                            {
                                n = aux.getSiguiente();
                                aux.setSiguiente(n.getSiguiente());
                                if (n == r)
                                {
                                    r = aux;
                                }
                                n.setSiguiente(null);
                                break;

                            } else
                            {
                                aux = aux.getSiguiente();
                            }

                        } else
                        {
                            break;
                        }
                    }
                    if (n == null)
                    {
                        System.out.println("no encontrado D:");
                    }

                }

            } else
            {
                System.out.println("El elemento no esta en la lista");
            }

        } else
        {
            System.out.println("No hay elementos en la lista");
        }

        return n;
    }

    public String desp()
    {
        String s = "";
        if (r != null)
        {
            Nodo aux = r.getSiguiente();
            do
            {
                s += aux.getEtiqueta() + "\t";
                aux = aux.getSiguiente();
            } while (aux != r.getSiguiente());

        }
        return s;
    }

    public String despRecu(Nodo r, Nodo primerNodo)
    {
        if (primerNodo != r)
        {
            return primerNodo.getEtiqueta() + "\t" + despRecu(r, primerNodo.getSiguiente());
        } else
        {
            return r.getEtiqueta();
        }
    }

    public Nodo buscarNodoPorEtiqueta(String etiqueta)
    {
        if (etiqueta==null)
        {
            return null;
        }
        Nodo aux = r;
        if (r != null)
        {
            if (r.getEtiqueta().compareTo(etiqueta) >= 0 && r.getSiguiente().getEtiqueta().compareTo(etiqueta) <= 0)
            {
                do
                {
                    if (aux.getEtiqueta().equals(etiqueta))
                    {
                        return aux;
                    }
                    aux = aux.getSiguiente();
                } while (aux != r);
            }
        }
        System.out.println("No se encontro el nodo...");
        return null;
    }

    public static void main(String[] args)
    {
        ListaCircularSL lista = new ListaCircularSL();
        Nodo<String> n1 = new Nodo<>("A", "A");
        Nodo<String> n2 = new Nodo<>("B", "B");
        Nodo<String> n3 = new Nodo<>("C", "C");
        Nodo<String> n4 = new Nodo<>("D", "F");
        lista.inserta(null);
        lista.inserta(n2);
        lista.inserta(n1);
        lista.inserta(n4);
        lista.inserta(n3);
        System.out.println(lista.desp());
        System.out.println(lista.buscarNodoPorEtiqueta("D"));

      
    }
}
