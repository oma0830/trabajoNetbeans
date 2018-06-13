package Capa_Conexion;
import java.sql.*;

public class Conexion 
{
    private String driver="com.mysql.jdbc.Driver";
    private String url="jdbc:mysql://localhost:3306/encriptar";
    private String user="root";
    private String pwd="";
    
    public Conexion()
    {}
    
    public ResultSet Listar(String Cad)
    {
        try
        {
            Class.forName(driver).newInstance();
            Connection cn = DriverManager.getConnection(url,user,pwd);
            PreparedStatement da = cn.prepareStatement(Cad);
            ResultSet tbl = da.executeQuery();
            return tbl;
        }
        catch(Exception e)
        {
            javax.swing.JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
        
    }
    public String Ejecutar(String Cad)
    {
        try
            {
                Class.forName(driver).newInstance();
                Connection cn=DriverManager.getConnection(url,user,pwd);
                PreparedStatement da = cn.prepareStatement(Cad);
                int r=da.executeUpdate();
                return "Se afectaron " + r + "filas";
            }
        catch(Exception e)
        {
            javax.swing.JOptionPane.showMessageDialog(null, e.getMessage());
            return "Error"+e.getMessage();
        }
    }
}
