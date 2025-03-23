/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import Archivos.ManipulacionArchivos;
import Controlador.Controlador;
import Estructuras.ListaCircularSL;
import Modelo.Clinica;
import Modelo.Muestra;
import Vista.VentanaPrincipal;

/**
 *
 * @author HP
 */
public class Main
{

    public static void main(String[] args)
    {
        Clinica clinica = new Clinica();
        VentanaPrincipal vtnPrincipal = new VentanaPrincipal();
        clinica.setPrioridades((ListaCircularSL) ManipulacionArchivos.carga(vtnPrincipal, "Datos.dat"));
        if (clinica.getPrioridades() == null)
        {
            clinica.setPrioridades(new ListaCircularSL());
        }
        Controlador controlador = new Controlador(clinica, vtnPrincipal);
        controlador.inicializar();
        controlador.getVtnPrincipal().setVisible(true);
    }
}
