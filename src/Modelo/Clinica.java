package Modelo;

import Estructuras.*;

/**
 * Clase que representa una clínica con manejo de pacientes por prioridad.
 */
public class Clinica
{

    private ListaCircularSL prioridades;
    private static final String PRIORIDAD_URGENTE = "2";

    public Clinica()
    {
        prioridades = new ListaCircularSL();
    }

    public Clinica(ListaCircularSL prioridades)
    {
        this.prioridades = prioridades;
    }

    /**
     * @return the prioridades
     */
    public ListaCircularSL getPrioridades()
    {
        return prioridades;
    }

    public static void main(String[] args)
    {
        Clinica clinica = new Clinica();
        Paciente paciente = new Paciente(1234, "José", 1, 4);
        Paciente paciente2 = new Paciente(1234, "José", 2, 4);
        clinica.recepcionPaciente(paciente);
        clinica.recepcionPaciente(paciente);
        clinica.recepcionPaciente(paciente);
        clinica.recepcionPaciente(paciente2);
        clinica.recepcionPaciente(paciente2);
        clinica.recepcionPaciente(paciente2);
        Muestra.muestraPacientesPorProridad(clinica.getPrioridades());

    }

    public Paciente atenderPaciente()
    {
        Nodo siguietePrioridad = obtenerSiguientePrioridadAAtender();
        ColaDinamica colaPacientes = obtenerColaPacientes(siguietePrioridad);
        if (colaPacientes != null && siguietePrioridad != null)
        {
            if (colaPacientes.getAtras() == null)
            {
                prioridades.elimina(siguietePrioridad.getEtiqueta());
            }
        }
        return obtenerSiguientePaciente(siguietePrioridad);
    }

    public Nodo obtenerSiguientePrioridadAAtender()
    {
        if (prioridades != null && prioridades.getR() != null)
        {
            return prioridades.getR();
        }
        return null;

    }

    public ColaDinamica obtenerColaPacientes(Nodo<ColaDinamica> prioridad)
    {
        if (prioridad != null)
        {
            return prioridad.getObj();
        }
        return null;
    }

    public Paciente obtenerSiguientePaciente(Nodo<ColaDinamica> prioridad)
    {
        if (prioridad != null)
        {
            ColaDinamica colaPacientes = obtenerColaPacientes(prioridad);
            if (colaPacientes != null)
            {
                Nodo<Paciente> nodoPaciente = colaPacientes.elimina();
                if (nodoPaciente != null)
                {
                    return nodoPaciente.getObj();
                }
            }
        }
        return null;
    }

    public void recepcionPaciente(Paciente paciente)
    {
        if (paciente == null)
        {
            System.out.println("Error: Paciente inválido.");
            return;
        }
        final String PRIORIDAD_DEL_PACIENTE = String.valueOf(paciente.getPrioridad());
        Nodo prioridad = obtenerOCrearPrioridad(PRIORIDAD_DEL_PACIENTE);
        if (prioridad != null)
        {
            insertarPacienteEnPrioridad(prioridad, paciente);
            System.out.println("Paciente agregado correctamente.");
        } else
        {
            System.out.println("No se pudo obtener la prioridad");
        }

    }

    public Nodo obtenerOCrearPrioridad(String prioridad)
    {
        Nodo nodoPrioridad = prioridades.buscarNodoPorEtiqueta(prioridad);
        if (nodoPrioridad != null)
        {
            return nodoPrioridad;
        }

        Nodo nuevaPrioridad = crearPrioridad(prioridad);
        if (nuevaPrioridad != null)
        {
            agregarPrioridad(nuevaPrioridad); //agrega la nueva prioridad a la lista
            return nuevaPrioridad;
        }

        System.out.println("No se pudo crear la prioridad");
        return null;
    }

    public void insertarPacienteEnPrioridad(Nodo prioridad, Paciente paciente)
    {
        if (prioridad != null && paciente != null)
        {
            encolarPaciente(paciente, (ColaDinamica) prioridad.getObj());
        } else
        {
            System.out.println("la prioridad o el paciente es nulo");
        }
    }

    public Nodo crearPrioridad(String prioridad)
    {
        if (prioridad == null || prioridad.trim().isEmpty())
        {
            System.out.println("Prioridad invalida");
        }
        ColaDinamica colaPacientes = new ColaDinamica();
        return new Nodo<>(colaPacientes, prioridad);
    }

    public void agregarPrioridad(Nodo nuevaPrioridad)
    {
        if (nuevaPrioridad != null)
        {
            prioridades.inserta(nuevaPrioridad);
        }
    }

    private void encolarPaciente(Paciente paciente, ColaDinamica cola)
    {
        if (cola == null)
        {
            System.out.println("Error: No se encontró la cola de prioridad correspondiente.");
            return;
        }
        if (paciente == null)
        {
            System.out.println("Error: Paciente inválido.");
            return;
        }

        Nodo nodoPaciente = crearNodoPaciente(paciente);
        if (nodoPaciente != null)
        {
            cola.inserta(nodoPaciente);
            System.out.println("Paciente " + paciente.getNombrePaciente() + " agregado correctamente.");
        } else
        {
            System.out.println("Error: No se pudo crear el nodo del paciente " + paciente.getNombrePaciente());
        }
    }

    public Nodo crearNodoPaciente(Paciente paciente)
    {
        if (paciente == null)
        {
            System.out.println("Paciente inválido");
            return null;
        }
        return new Nodo<>(paciente, String.valueOf(paciente.getPrioridad()));
    }
}
