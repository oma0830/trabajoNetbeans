
package Capa_Datos;
import Capa_Conexion.Conexion;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Cuenta {
    private String numero;
    private String saldo;
    private String clave;
    
    public String EliminarCuenta()
    {
        Conexion objmod=new Conexion();
        String cad="delete from cuenta where numero='"+this.getNumero ()+"'";
        return objmod.Ejecutar(cad);       
    }
    
    public String GrabarCuenta()
    {
        Conexion objmod=new Conexion();
        String cad="insert into cuenta values ('"+this.getNumero()
                    +"','"+this.getSaldo()+"','"+this.getClave()+"')";
            return objmod.Ejecutar(cad);
    }
    
    public ArrayList<Cuenta> ListaCuenta()
    {
        ArrayList lista3=new ArrayList();
        try
        {
                Conexion objmod=new Conexion();
                ResultSet tabla=objmod.Listar("select * from cuenta");
                Cuenta objart;
                while(tabla.next())
                {
                    objart=new Cuenta();
                    objart.setNumero(tabla.getString("numero"));
                    objart.setSaldo(tabla.getString("saldo"));
                    objart.setClave(tabla.getString("clave"));
                    lista3.add(objart);
                }
        }
        catch(Exception e)
        {
            javax.swing.JOptionPane.showMessageDialog(null,e.getMessage());
        }
        return lista3;
    }
    
    public String getNumero()
    {
        return numero;
    }
    public void setNumero(String numero)
    {
        this.numero =numero;
    }
    
    
    public String getSaldo()
    {
        return saldo;
    }
    public void setSaldo(String saldo)
    {
        this.saldo =saldo;
    }
    
    public String getClave()
    {
        return clave;
    }
    public void setClave(String clave)
    {
        this.clave =clave;
    }
}
