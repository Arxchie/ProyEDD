/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructuras;

/**
 *
 * @author HP
 */
public class Pila<T> implements Machote<T>
{

    private T arr[];
    private int tope = -1;

    public Pila(T[] arr)
    {
        this.arr = arr;
    }

    @Override
    public void inserta(T objeto)
    {
        if (!isLlena())
        {
            arr[++tope] = objeto;
        } else
        {
            System.out.println("Pila llena");
        }
    }

    @Override
    public T elimina()
    {
        T eliminado = null;
        if (!isVacia())
        {
            eliminado = arr[tope--];
        } else
        {
            System.out.println("Pila Vacia");
        }
        return eliminado;
    }

    @Override
    public boolean isVacia()
    {
        return tope == -1;
    }

    @Override
    public boolean isLlena()
    {
        return tope == arr.length - 1;
    }

    /**
     * @return the arr
     */
    public T[] getArr()
    {
        return arr;
    }

    /**
     * @return the tope
     */
    public int getTope()
    {
        return tope;
    }

    /**
     * @param arr the arr to set
     */
    public void setArr(T[] arr)
    {
        this.arr = arr;
    }

    /**
     * @param tope the tope to set
     */
    public void setTope(int tope)
    {
        this.tope = tope;
    }
    
    public static void main(String[] args)
    {
       
       
    }
}
