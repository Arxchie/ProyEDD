package Modelo;

import Estructuras.ListaCircuarSL;


public class Clinica
{

    private ListaCircuarSL prioridades;

    public Clinica()
    {
        prioridades = new ListaCircuarSL();
    }

    public Clinica(ListaCircuarSL prioridades)
    {
        this.prioridades = prioridades;
    }

    public ListaCircuarSL getPrioridades()
    {
        return prioridades;
    }

    public static void main(String[] args)
    {
        Clinica clinica = new Clinica();
        Paciente paciente = new Paciente(1234, "jose", 2, 4);
    }
}
