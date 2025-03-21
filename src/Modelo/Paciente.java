package Modelo;

import java.io.Serializable;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HP
 */
public class Paciente implements Serializable
{
    private int numeroPaciente;
    private String nombrePaciente;
    private int prioridad;
    private int zona;

    public Paciente(int noPaciente, String nomPaciente, int prioridad, int zona)
    {
        this.numeroPaciente = noPaciente;
        this.nombrePaciente = nomPaciente;
        this.prioridad = prioridad;
        this.zona = zona;
    }

    /**
     * @return the numeroPaciente
     */
    public int getNumeroPaciente()
    {
        return numeroPaciente;
    }

    /**
     * @return the nombrePaciente
     */
    public String getNombrePaciente()
    {
        return nombrePaciente;
    }

    /**
     * @return the prioridad
     */
    public int getPrioridad()
    {
        return prioridad;
    }

    /**
     * @return the zona
     */
    public int getZona()
    {
        return zona;
    }

    /**
     * @param numeroPaciente the numeroPaciente to set
     */
    public void setNumeroPaciente(int numeroPaciente)
    {
        this.numeroPaciente = numeroPaciente;
    }

    /**
     * @param nombrePaciente the nombrePaciente to set
     */
    public void setNombrePaciente(String nombrePaciente)
    {
        this.nombrePaciente = nombrePaciente;
    }

    /**
     * @param prioridad the prioridad to set
     */
    public void setPrioridad(int prioridad)
    {
        this.prioridad = prioridad;
    }

    /**
     * @param zona the zona to set
     */
    public void setZona(int zona)
    {
        this.zona = zona;
    }

    @Override
    public String toString()
    {
        return "Paciente{" + "numeroPaciente=" + numeroPaciente + ", nombrePaciente=" + nombrePaciente + ", prioridad=" + prioridad + ", zona=" + zona + '}';
    }
    
}
