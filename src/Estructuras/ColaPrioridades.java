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
public class ColaPrioridades<T>
{

    public static void main(String[] args)
    {
         Paciente paciente = new Paciente(1234, "José", 1, 1);
        Paciente paciente2 = new Paciente(1234, "José", 2, 2);
        Paciente paciente3 = new Paciente(1234, "José", 2, 6);
        Paciente paciente4 = new Paciente(1234, "José", 2, 3);
        Paciente paciente5 = new Paciente(1234, "José", 2, 4);
        Paciente paciente6 = new Paciente(1234, "José", 2, 5);
        ArregloDinamico arr=new ArregloDinamico();
        arr.insertarPacienteAAarayDinamico(paciente);
        arr.insertarPacienteAAarayDinamico(paciente2);
        arr.insertarPacienteAAarayDinamico(paciente3);
        arr.insertarPacienteAAarayDinamico(paciente4);
        arr.insertarPacienteAAarayDinamico(paciente5);
        arr.insertarPacienteAAarayDinamico(paciente6);
        Cola<Paciente>colaPacientes=new Cola(arr.getArr());
        colaPacientes.setAtras(arr.getArr().length-1);
        Pila<Paciente>pila1 =new Pila(new Paciente[arr.getArr().length]);
        Pila<Paciente>pila2 =new Pila(new Paciente[arr.getArr().length]);
        ordenaCola(colaPacientes, pila1, pila2);
        System.out.println(colaPacientes.elimina().getZona());
        System.out.println(colaPacientes.elimina().getZona());
        System.out.println(colaPacientes.elimina().getZona());
        System.out.println(colaPacientes.elimina().getZona());
        System.out.println(colaPacientes.elimina().getZona());
        System.out.println(colaPacientes.elimina().getZona());
     
       
    }
    public static void ordenaCola(Cola<Paciente> cola, Pila<Paciente> pila1, Pila<Paciente> pila2)
    {
        while (!cola.isVacia())
        {
            Paciente eliminadoCola = cola.elimina();
            if (pila1.isVacia())
            {
                pila1.inserta(eliminadoCola);
            } else
            {
                Paciente eliminadoPila;
                while (!pila1.isVacia())
                {
                    eliminadoPila = pila1.elimina();
                    if (eliminadoCola.getZona() > eliminadoPila.getZona())
                    {
                        pila2.inserta(eliminadoPila);
                    } else
                    {
                        pila1.inserta(eliminadoPila);
                        break;
                    }
                }
                pila1.inserta(eliminadoCola);
                while (!pila2.isVacia())
                {
                    pila1.inserta(pila2.elimina());
                }
            }
        }
        while (!pila1.isVacia())
        {
            cola.inserta(pila1.elimina());
        }

    }
}
