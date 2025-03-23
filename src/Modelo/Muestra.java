/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Estructuras.ColaDinamica;
import Estructuras.ListaCircularSL;
import Estructuras.Nodo;

/**
 *
 * @author HP
 */
public class Muestra 
{

    
    public  static void mostrarTodosLosPacientesPorPrioridad(ListaCircularSL listaPacientes)
    {
        if (listaPacientes != null && listaPacientes.getR() != null)
        {
            System.out.println("----------------PRIORIDADES---------------");
            Nodo<ColaDinamica> aux = listaPacientes.getR().getSiguiente();
            do
            {
                System.out.println("Prioridad: " + aux.getEtiqueta());
                muestraPacientesDeCola(aux.getObj());
                aux = aux.getSiguiente();

            } while (aux != listaPacientes.getR().getSiguiente());
        }

    }

    public static void muestraPacientesDeCola(ColaDinamica colaPacientes)
    {
        if (colaPacientes != null)
        {
            Nodo<Paciente> aux = colaPacientes.getFrente();
            while (aux!=null)
            {
                System.out.println(aux.getObj().toString());
                aux = aux.getSiguiente();   
            }

        }

    }
}
