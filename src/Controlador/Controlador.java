/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Archivos.ManipulacionArchivos;
import Modelo.Clinica;
import Modelo.Paciente;
import Vista.*;
import cjb.ci.CtrlInterfaz;
import cjb.ci.Mensajes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP
 */
public class Controlador
{

    Clinica clinica;
    VentanaPrincipal vtnPrincipal;

    public Controlador(Clinica clinica, VentanaPrincipal vtnPrincipal)
    {
        this.clinica = clinica;
        this.vtnPrincipal = vtnPrincipal;
    }

    public void setClinica(Clinica clinica)
    {
        this.clinica = clinica;
    }

    public void setVtnPrincipal(VentanaPrincipal vtnPrincipal)
    {
        this.vtnPrincipal = vtnPrincipal;
    }

    public Clinica getClinica()
    {
        return clinica;
    }

    public VentanaPrincipal getVtnPrincipal()
    {
        return vtnPrincipal;
    }

    public void inicializar()
    {
        recepcionPaciente();
        atenderPaciente();
        asignarMoverPacientes();
        ordenarPorZona();
        mostrarTodosLosPacientes();
    }

    public void recepcionPaciente()
    {
        vtnPrincipal.getBtnRegistrarPaciente().addActionListener((ActionEvent e) ->
        {
            if (vtnPrincipal.getJtfNombrePaciente().getText().trim().length() == 0 || vtnPrincipal.getJtfNumeroPaciente().getText().trim().length() == 0)
            {
                Mensajes.error(vtnPrincipal, "Debe llenar todos los campos");
                CtrlInterfaz.cambia(vtnPrincipal.getJtfNumeroPaciente());
                return;
            }
            try
            {
                int numeroPaciente = Integer.parseInt(vtnPrincipal.getJtfNumeroPaciente().getText());
                System.out.println(vtnPrincipal.getJtfNumeroPaciente().getText());
                Paciente nuevoPaciente = new Paciente(numeroPaciente, vtnPrincipal.getJtfNombrePaciente().getText(), vtnPrincipal.getJcbPrioridadPaciente().getSelectedIndex() + 1, vtnPrincipal.getJcbZonaPaciente().getSelectedIndex() + 1);
                clinica.recepcionPaciente(nuevoPaciente);
                mostrarTodosLosPacientes();
                Mensajes.exito(vtnPrincipal, "Paciente registrado");
                ManipulacionArchivos.guarda(null, clinica.getPrioridades(), "Datos.dat");

            } catch (NumberFormatException ex)
            {
                System.out.println(ex.getMessage());
                Mensajes.error(vtnPrincipal, "No se ha podido guardar el registro");
            }
            limpiaCampoRegistro();
        });
    }

    public void asignarMoverPacientes()
    {
        vtnPrincipal.getBtnMoverPaciente().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                moverPacientes();
            }
        });
    }

    private void moverPacientes()
    {

        if (validaListaPrioridades() == false)
        {
            return;
        }
        PedirPrioridadOZona vtn = new PedirPrioridadOZona();
        vtn.getJlbTexto().setText("Elija la Prioridad a mover: ");
        vtn.setVisible(true);
        vtn.getBtnAceptarPrioridad().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int prioridad = vtn.getjComboBox().getSelectedIndex() + 1;
                if (prioridad == 0)
                {
                    Mensajes.error(vtnPrincipal, "Debe seleccionar una prioridad");
                } else
                {
                    if (clinica.getPrioridades().buscarNodoPorEtiqueta(String.valueOf(prioridad)) != null)
                    {
                        clinica.moverPacientes(String.valueOf(prioridad));
                        ManipulacionArchivos.guarda(null, clinica.getPrioridades(), "Datos.dat");
                        Mensajes.exito(vtnPrincipal,"Pacientes trasladados");
                        mostrarTodosLosPacientes();
                        vtn.dispose();
                    } else
                    {
                        Mensajes.error(vtnPrincipal, "No existe esa prioridad");
                    }
                }
            }
        });

    }

    public boolean validaListaPrioridades()
    {
        if (clinica == null)
        {
            Mensajes.error(vtnPrincipal, "No se pudo cargar la clinica");
            return false;
        }
        if (clinica.getPrioridades() != null)
        {
            if (clinica.getPrioridades().getR() != null)
            {
                return true;
            }
            Mensajes.error(vtnPrincipal, "No hay pacientes");
        } else
        {
            Mensajes.error(vtnPrincipal, "No existe la lista de prioridades");
        }
        return false;
    }

    private void limpiaCampoRegistro()
    {
        CtrlInterfaz.limpia(vtnPrincipal.getJtfNombrePaciente(), vtnPrincipal.getJtfNumeroPaciente());
        vtnPrincipal.getJcbPrioridadPaciente().setSelectedIndex(0);
        vtnPrincipal.getJcbZonaPaciente().setSelectedIndex(0);
        CtrlInterfaz.cambia(vtnPrincipal.getJtfNumeroPaciente());
    }

    public void atenderPaciente()
    {
        vtnPrincipal.getBtnAtenderPaciente().addActionListener((ActionEvent e) ->
        {
            if (clinica == null)
            {
                Mensajes.error(vtnPrincipal, "No se pudo cargar la clinica");
                return;
            }
            Paciente pacienteAtendido = clinica.atenderPaciente();
            if (pacienteAtendido != null)
            {
                Mensajes.exito(vtnPrincipal, "Paciente " + pacienteAtendido.getNombrePaciente() + " atendido");
                mostrarTodosLosPacientes();
                ManipulacionArchivos.guarda(null, clinica.getPrioridades(), "Datos.dat");
            } else
            {
                Mensajes.error(vtnPrincipal, "No se atendio nigun paciente");
            }
        });
    }

    public void ordenarPorZona()
    {

        vtnPrincipal.getBtnOrdenarPorZona().addActionListener((ActionEvent e) ->
        {
            if (validaListaPrioridades() == false)
            {
                return;
            }
            PedirPrioridadOZona vtn = new PedirPrioridadOZona();
            vtn.getJlbTexto().setText("Elija la prioridad a ordenar: ");
            vtn.setVisible(true);
            vtn.getBtnAceptarPrioridad().addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    int prioridad = vtn.getjComboBox().getSelectedIndex() + 1;
                    if (prioridad == 0)
                    {
                        Mensajes.error(vtnPrincipal, "Debe seleccionar una prioridad");
                    } else
                    {
                        if (clinica.getPrioridades().buscarNodoPorEtiqueta(String.valueOf(prioridad)) != null)
                        {
                            clinica.ordenarPorZonaDePrioridad(String.valueOf(prioridad));
                            Mensajes.exito(vtnPrincipal, "Elementos ordenados");
                            vtn.dispose();
                            mostrarTodosLosPacientes();
                            ManipulacionArchivos.guarda(null, clinica.getPrioridades(), "Datos.dat");
                        } else
                        {
                            Mensajes.error(vtnPrincipal, "No existe esa prioridad");
                        }
                    }
                }
            });
        });

    }

    public void mostrarTodosLosPacientes()
    {
        vtnPrincipal.mostrarTodosLosPacientesPorPrioridad(clinica.getPrioridades());
    }

}
