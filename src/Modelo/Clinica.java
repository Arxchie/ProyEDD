package Modelo;

import Archivos.ManipulacionArchivos;
import Estructuras.*;
import java.io.Serializable;

/**
 * Clase que representa una clínica con manejo de colaDinamicaPacientes por
 * prioridad.
 */
public class Clinica
{

    private ListaCircularSL prioridades;

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

    public void setPrioridades(ListaCircularSL prioridades)
    {
        this.prioridades = prioridades;
    }

    public static void main(String[] args)
    {
        Clinica clinica = new Clinica();
       
        clinica.setPrioridades((ListaCircularSL) ManipulacionArchivos.carga(null, "Datos.dat"));
        if (clinica.getPrioridades() == null)
        {
            clinica.setPrioridades(new ListaCircularSL());
        }
        Paciente paciente1 = new Paciente(1234, "José", 1, 3);
        clinica.recepcionPaciente(paciente1);

//        Muestra.muestraPacientesDeCadaProridad(clinica.getPrioridades());
//
//        Nodo prioridadBeneficiada2 = clinica.getPrioridades().buscarNodoPorEtiqueta("2");
//        if (prioridadBeneficiada2 != null)
//        {
//            ColaDinamica colaZonaN = clinica.obtenerColaDeZona((ColaDinamica) prioridadBeneficiada2.getObj(), 6);
//            clinica.mandarAPrioridadCero(colaZonaN);
//            clinica.eliminarPrioridadSiNoTienePacientes(prioridadBeneficiada2);
//
//        }
      //  Muestra.muestraPacientesDeCadaProridad(clinica.getPrioridades());
        ManipulacionArchivos.guarda(null, clinica.getPrioridades(), "Datos.dat");

    }

    public void mandarAPrioridadCero(ColaDinamica cola)
    {
        if (cola == null)
        {
            System.out.println("La cola es nula");
            return;
        }
        if (cola.getAtras() == null)
        {
            System.out.println("La cola esta Vacia");
            return;
        }
        final String PRIORIDAD_CERO = "0";
        Nodo prioridadCero = obtenerOCrearPrioridad(PRIORIDAD_CERO);
        if (prioridadCero != null)
        {
            ColaDinamica colaPacientesPrioridadCero = (ColaDinamica) prioridadCero.getObj();
            if (colaPacientesPrioridadCero != null)
            {
                while (cola.getAtras() != null)
                {
                    colaPacientesPrioridadCero.inserta(cola.elimina());
                }
            }

        }

    }

    public ColaDinamica obtenerColaDeZona(ColaDinamica cola, int zona)
    {
        if (cola == null)
        {
            return null;
        }

        ColaDinamica colaDeZona = new ColaDinamica();
        ColaDinamica colaAuxiliar = new ColaDinamica();
        Nodo eliminado;
        // Separa los elementos en dos colas
        while (cola.getAtras() != null)
        {
            eliminado = cola.elimina();
            if (((Paciente) eliminado.getObj()).getZona() == zona)
            {
                colaDeZona.inserta(eliminado);
            } else
            {
                colaAuxiliar.inserta(eliminado);
            }
        }
        // Reinserta los elementos de colaAuxiliar en la cola original
        while (colaAuxiliar.getAtras() != null)
        {
            cola.inserta(colaAuxiliar.elimina());
        }

        return colaDeZona;
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
        eliminarPrioridadSiNoTienePacientes(siguietePrioridad);
        return pacienteAAtender;
    }

    public void eliminarPrioridadSiNoTienePacientes(Nodo<ColaDinamica> prioridad)
    {
        if (prioridad == null)
        {
            System.out.println("Prioridad null");
            return;
        }
        ColaDinamica colaPacientes = (ColaDinamica) prioridad.getObj();
        if (colaPacientes == null || colaPacientes.getAtras() == null)
        {
            prioridades.elimina(prioridad.getEtiqueta());
        }
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
        if (prioridades == null)
        {
            System.out.println("Error: Prioridades es null");
            return;
        }
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
        if (prioridades == null)
        {
            System.out.println("Error: Prioridades es null");
            return null;
        }
        Nodo nodoPrioridad = prioridades.buscarNodoPorEtiqueta(prioridad);
        final boolean LA_PRIORIDAD_EXISTE = nodoPrioridad != null;
        if (LA_PRIORIDAD_EXISTE)
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
        if (prioridades == null)
        {
            System.out.println("Error: Prioridades es null");
            return;
        }
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
