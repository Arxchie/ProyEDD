/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructuras;

import Modelo.Paciente;

/**
 *
 * @author HP
 */
public class ArregloDinamico
{

    private Paciente arr[];

    public ArregloDinamico()
    {
       
    }

    /**
     * @return the arr
     */
    public Paciente[] getArr()
    {
        return arr;
    }

    /**
     * @param arr the arr to set
     */
    public void setArr(Paciente[] arr)
    {
        this.arr = arr;
    }

    public void nuevoP(Paciente paciente)
    {
        if (getArr() == null)
        {
            setArr(new Paciente[1]);
            getArr()[0] = paciente;
        } else
        {
            Paciente nvo[] = new Paciente[getArr().length + 1];
            System.arraycopy(getArr(), 0, nvo, 0, getArr().length);
            nvo[nvo.length - 1] = paciente;
            setArr(nvo);
        }
    }
}
