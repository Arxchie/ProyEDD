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

    private  Paciente arr[];

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

    public void insertarPacienteAAarayDinamico(Paciente paciente)
    {
        if (arr == null)
        {
            arr = new Paciente[1];
            arr[0] = paciente;
        } else
        {
            Paciente nvo[] = new Paciente[arr.length + 1];
            System.arraycopy(arr, 0, nvo, 0, arr.length);
            nvo[nvo.length - 1] = paciente;
            arr = nvo;
        }
    }

}
