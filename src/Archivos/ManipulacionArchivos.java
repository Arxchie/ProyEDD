/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Archivos; 

import cjb.ci.Mensajes;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author HP
 */
public class ManipulacionArchivos
{

    public static void guarda(JFrame jf, Object obj, String s)
    {
        try
        {
            FileOutputStream fos = new FileOutputStream("Datos/" + s);
            ObjectOutputStream arch = new ObjectOutputStream(fos);
            arch.writeObject(obj);
            arch.close();

        } catch (FileNotFoundException ex)
        {
            
             Mensajes.error(jf,"No se encontro el archivo...");
        } catch (Exception e)
        {
      
            Mensajes.error(jf,"Error..." + e.toString());
        }
    }

    public static void guarda(JFrame jf,Object obj[], String s)
    {
        try
        {
            FileOutputStream fos = new FileOutputStream("Datos/" + s);
            ObjectOutputStream arch = new ObjectOutputStream(fos);
            arch.writeObject(obj);
            arch.close();

        } catch (FileNotFoundException ex)
        {
          Mensajes.error(jf,"No se encontro el archivo...");
        } catch (Exception e)
        {
           Mensajes.error(jf,"Error..." + e.toString());
        }
    }

    public static Object carga(JFrame jf,String s)
    {
        Object obj = null;
        try
        {
            FileInputStream fis = new FileInputStream("Datos/" + s);
            ObjectInputStream arch = new ObjectInputStream(fis);
            obj = arch.readObject();
            arch.close();
        } catch (FileNotFoundException ex)
        {
           
            Mensajes.error(jf,"No se encontro el archivo...");

        } catch (Exception ex)
        {
            
             Mensajes.error(jf,"Error..." + ex.toString());
        }
        return obj;
    }
    

    public static Object[] cargaArr(JFrame jf,String s)
    {
        Object obj[] = null;
        try
        {
            FileInputStream fis = new FileInputStream("Datos/" + s);
            ObjectInputStream arch = new ObjectInputStream(fis);
            obj = (Object[])arch.readObject();
            arch.close();
        } catch (FileNotFoundException ex)
        {
            
            Mensajes.error(jf,"No se encontro el archivo...");

        } catch (Exception ex)
        {
            Mensajes.error(jf,"Error..." + ex.toString());
        }
        return obj;
    }
    
}
