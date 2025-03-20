/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructuras;

/**
 *
 * @author HP
 */
public class Cola<T> implements Machote<T>
{

    private int atras = -1;
    private T arr[];

    public Cola(T[] arr)
    {
        this.arr = arr;
    }
    public Cola(T[] arr, int atras)
    {
        this.arr = arr;
        this.atras = atras;
    }

    /**
     * @return the atras
     */
    public int getAtras()
    {
        return atras;
    }

    /**
     * @return the arr
     */
    public T[] getArr()
    {
        return arr;
    }

    /**
     * @param atras the atras to set
     */
    public void setAtras(int atras)
    {
        this.atras = atras;
    }

    /**
     * @param arr the arr to set
     */
    public void setArr(T[] arr)
    {
        this.arr = arr;
    }

    @Override
    public void inserta(T objeto)
    {
        if (!isLlena())
        {
            arr[++atras] = objeto;
        } else
        {
            System.out.println("cola llena");
        }

    }

    @Override
    public T elimina()
    {
        T eliminado = null;
        if (!isVacia())
        {
            eliminado = arr[0];
            for (int i = 0; i < atras; i++)
            {
                arr[i] = arr[i+ 1];

            }
            atras--;
        } else
        {
            System.out.println("cola vacia");
        }
        return eliminado;
    }

    @Override
    public boolean isVacia()
    {
        return atras == -1;
    }

    @Override
    public boolean isLlena()
    {
        return  atras==arr.length - 1;
    }

    public static void main(String[] args)
    {
        Integer arr[] = new Integer[5];
        Cola<Integer> cola = new Cola(arr);
        cola.inserta(1);
        cola.inserta(2);
        cola.inserta(3);
        cola.inserta(3);
        cola.inserta(3);
        cola.inserta(3);
        cola.inserta(3);

        System.out.println(cola.elimina());
        System.out.println(cola.elimina());
        System.out.println(cola.elimina());
        System.out.println(cola.elimina());
        System.out.println(cola.elimina());
        System.out.println(cola.elimina());
     

    }
}
