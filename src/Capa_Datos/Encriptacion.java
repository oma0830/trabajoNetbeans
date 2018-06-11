/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Datos;
import Capa_Conexion.Conexion;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Edwin
 */
public class Encriptacion {
    private String desencrip;
    private String encrip;
    //private String clave;
    
    public String EliminarEncriptacion()
    {
        Conexion objmod=new Conexion();
        String cad="delete from encriptacion where desencrip='"+this.getDesencrip ()+"'";
        return objmod.Ejecutar(cad);       
    }
    
    public String GrabarEncriptacion()
    {
        Conexion objmod=new Conexion();
        String cad="insert into encriptacion values ('"+this.getDesencrip()
                    +"','"+this.getEncrip()+"')";
            return objmod.Ejecutar(cad);
    }
    
    public ArrayList<Encriptacion> ListaEncriptacion()
    {
        ArrayList lista3=new ArrayList();
        try
        {
                Conexion objmod=new Conexion();
                ResultSet tabla=objmod.Listar("select * from cuenta");
                Encriptacion objart;
                while(tabla.next())
                {
                    objart=new Encriptacion();
                    objart.setDesencrip(tabla.getString("desencrip"));
                    objart.setEncrip(tabla.getString("encrip"));
                    lista3.add(objart);
                }
        }
        catch(Exception e)
        {
            javax.swing.JOptionPane.showMessageDialog(null,e.getMessage());
        }
        return lista3;
    }
    
    public String getDesencrip()
    {
        return desencrip;
    }
    public void setDesencrip(String desencrip)
    {
        this.desencrip =desencrip;
    }
    
    
    public String getEncrip()
    {
        return encrip;
    }
    public void setEncrip(String encrip)
    {
        this.encrip =encrip;
    }
}

