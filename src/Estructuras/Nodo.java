/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructuras;

/**
 *
 * @author HP
 */
public class Nodo<T>
{
    private T obj;
    private String etiqueta;
    private Nodo siguiente;

    public Nodo(T obj, String etiqueta)
    {
        this.obj = obj;
        this.etiqueta = etiqueta;
    }

    /**
     * @return the obj
     */
    public T getObj()
    {
        return obj;
    }

    /**
     * @return the etiqueta
     */
    public String getEtiqueta()
    {
        return etiqueta;
    }

    /**
     * @return the siguiente
     */
    public Nodo getSiguiente()
    {
        return siguiente;
    }

    /**
     * @param obj the obj to set
     */
    public void setObj(T obj)
    {
        this.obj = obj;
    }

    /**
     * @param etiqueta the etiqueta to set
     */
    public void setEtiqueta(String etiqueta)
    {
        this.etiqueta = etiqueta;
    }

    /**
     * @param siguiente the siguiente to set
     */
    public void setSiguiente(Nodo siguiente)
    {
        this.siguiente = siguiente;
    }
    
    
}
