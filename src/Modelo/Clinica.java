package Modelo;

import Estructuras.*;

/**
 * Clase que representa una clínica con manejo de colaDinamicaPacientes por
 * prioridad.
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
        Paciente paciente1 = new Paciente(1234, "José", 1, 3);
        Paciente paciente2 = new Paciente(1234, "Maria", 1, 1);
        Paciente paciente3 = new Paciente(1234, "Lucas", 1, 2);
        Paciente paciente4 = new Paciente(1234, "Pablo", 2, 4);
        Paciente paciente5 = new Paciente(1234, "Marta", 2, 6);
        Paciente paciente6 = new Paciente(1234, "Pablo", 2, 5);
        clinica.recepcionPaciente(paciente1);
        clinica.recepcionPaciente(paciente2);
        clinica.recepcionPaciente(paciente3);
        clinica.recepcionPaciente(paciente4);
        clinica.recepcionPaciente(paciente5);
        clinica.recepcionPaciente(paciente6);
        Muestra.muestraPacientesDeCadaProridad(clinica.getPrioridades());
        clinica.ordenarPorZonaDePrioridad("1");
        clinica.ordenarPorZonaDePrioridad("2");
        Muestra.muestraPacientesDeCadaProridad(clinica.getPrioridades());
   
    }

    public void ordenarPorZonaDePrioridad(String prioridad)
    {
        if (prioridad == null)
        {
            System.out.println("la pioridad no puede ser null");
            return;
        }
        Nodo<ColaDinamica> nodoPrioridad = prioridades.buscarNodoPorEtiqueta(prioridad);
        if (nodoPrioridad != null)
        {
            Paciente[] arrPacientes = convertirColaAArreglo(nodoPrioridad.getObj());
            if (arrPacientes != null)
            {
                final int CANTIDAD_DE_PACIENTES = arrPacientes.length;
                Cola<Paciente> colaOrdenada = ColaPrioridades.ordenaCola(new Cola(arrPacientes, CANTIDAD_DE_PACIENTES - 1),
                        new Pila(new Paciente[CANTIDAD_DE_PACIENTES]),
                        new Pila(new Paciente[CANTIDAD_DE_PACIENTES]));
                if (colaOrdenada != null)
                {
                    ColaDinamica colaDinamicaPacientesOrdenada = convertirColaAColaDinamica(colaOrdenada);
                    if (colaDinamicaPacientesOrdenada != null)
                    {
                        nodoPrioridad.setObj(colaDinamicaPacientesOrdenada);
                    }

                }
            }
        }

    }

    public ColaDinamica convertirColaAColaDinamica(Cola<Paciente> colaEstatica)
    {
        ColaDinamica colaDinamica = new ColaDinamica();
        if (colaEstatica == null)
        {
            System.out.println("error: se ha devuelto una cola dinamica vacia");
            return colaDinamica;
        }
        while (!colaEstatica.isVacia())
        {
            colaDinamica.inserta(crearNodoPaciente(colaEstatica.elimina()));
        }
        return colaDinamica;
    }

    public Paciente[] convertirColaAArreglo(ColaDinamica cola)
    {
        if (cola == null)
        {
            System.out.println("la cola no existe");
            return null;
        }
        ArregloDinamico arregloDinamico = new ArregloDinamico();
        while (cola.getAtras() != null)
        {
            arregloDinamico.insertarPacienteAAarayDinamico((Paciente) cola.elimina().getObj());
        }
        return arregloDinamico.getArr();
    }

    public Nodo moverPacientes(String prioridad)
    {
        if (prioridades != null && prioridades.getR() != null)
        {
            return prioridades.elimina(prioridad);
        }
        System.out.println("No se pudo eliminar la prioridad dado que no existe");
        return null;

    }

    public Paciente atenderPaciente()
    {
        Nodo siguietePrioridad = obtenerSiguientePrioridadAAtender();
        ColaDinamica colaPacientes = obtenerColaPacientesDePrioridad(siguietePrioridad);
        Paciente pacienteAAtender = obtenerSiguientePacienteDeCola(colaPacientes);
        if (colaPacientes != null && siguietePrioridad != null)
        {
            if (colaPacientes.getAtras() == null)
            {
                prioridades.elimina(siguietePrioridad.getEtiqueta());
            }
        }
        return pacienteAAtender;
    }

    public Nodo obtenerSiguientePrioridadAAtender()
    {
        if (prioridades != null && prioridades.getR() != null)
        {
            return prioridades.getR().getSiguiente();
        }
        return null;

    }

    public ColaDinamica obtenerColaPacientesDePrioridad(Nodo<ColaDinamica> prioridad)
    {
        if (prioridad != null)
        {
            return prioridad.getObj();
        }
        return null;
    }

    public Paciente obtenerSiguientePacienteDeCola(ColaDinamica colaPacientes)
    {
        if (colaPacientes != null)
        {
            Nodo<Paciente> nodoPaciente = colaPacientes.elimina();
            if (nodoPaciente != null)
            {
                return nodoPaciente.getObj();
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
