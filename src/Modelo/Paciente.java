package Modelo;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HP
 */
public class Paciente
{
    private int noPaciente;
    private String nomPaciente;
    private int prioridad;
    private int zona;

    public Paciente(int noPaciente, String nomPaciente, int prioridad, int zona)
    {
        this.noPaciente = noPaciente;
        this.nomPaciente = nomPaciente;
        this.prioridad = prioridad;
        this.zona = zona;
    }

    /**
     * @return the noPaciente
     */
    public int getNoPaciente()
    {
        return noPaciente;
    }

    /**
     * @return the nomPaciente
     */
    public String getNomPaciente()
    {
        return nomPaciente;
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
     * @param noPaciente the noPaciente to set
     */
    public void setNoPaciente(int noPaciente)
    {
        this.noPaciente = noPaciente;
    }

    /**
     * @param nomPaciente the nomPaciente to set
     */
    public void setNomPaciente(String nomPaciente)
    {
        this.nomPaciente = nomPaciente;
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
    
}
