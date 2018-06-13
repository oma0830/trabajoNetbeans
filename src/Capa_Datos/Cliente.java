package Capa_Datos;
import Capa_Conexion.Conexion;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author mancera.edwin
 */
public class Cliente {
    private String id_cliente;
    private String nombrec;
    private String apellido1;
    private String direccion;
    private String telefono;
    //Prueba repositorio emancerar
public String EliminarCliente()
    {
        Conexion objmod=new Conexion();
        String cad="delete from cliente where id_cliente='"+this.getId_cliente()+"'";
        return objmod.Ejecutar(cad);       
    }

public String GrabarCliente()
    {
        Conexion objmod=new Conexion();
        String cad="insert into cliente values ('"+this.getId_cliente()
                    +"','"+this.getNombrec()+"','"+this.getApellido1()+"','"
                    +this.getDireccion()+"','"+this.getTelefono()+"')";
            return objmod.Ejecutar(cad);
    }



    public ArrayList<Cliente> ListaCliente()
    {
        ArrayList lista2=new ArrayList();
        try
        {
                Conexion objmod=new Conexion();
                ResultSet tabla=objmod.Listar("select * from cliente");
                Cliente objart;
                while(tabla.next())
                {
                    objart=new Cliente();
                    objart.setId_cliente(tabla.getString("id_cliente"));
                    objart.setNombrec(tabla.getString("nombrec"));
                    objart.setApellido1(tabla.getString("apellido1"));
                    objart.setDireccion(tabla.getString("direccion"));
                    objart.setTelefono(tabla.getString("telefono"));
                    lista2.add(objart);
                }
        }
        catch(Exception e)
        {
            javax.swing.JOptionPane.showMessageDialog(null,e.getMessage());
        }
        return lista2;
    }
    
    public String getId_cliente()
    {
        return id_cliente;
    }
    public void setId_cliente(String id_cliente)
    {
        this.id_cliente =id_cliente;
    }
    
    public String getNombrec()
    {
        return nombrec;
    }
    public void setNombrec(String nombrec)
    {
        this.nombrec = nombrec;
    }
    
    public String getApellido1()
    {
        return apellido1;
    }
    public void setApellido1(String apellido1)
    {
        this.apellido1= apellido1;
    }
    
    public String getDireccion()
    {
        return direccion;
    }
    public void setDireccion(String direccion)
    {
        this.direccion = direccion;
    }
    
    public String getTelefono()
    {
        return telefono;
    }
    public void setTelefono(String telefono)
    {
        this.telefono = telefono;
    }
}
