
package baserelacionala;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Conexion {
    Connection conn;
    PreparedStatement ps;
    Statement st;
    ResultSet rs;
  
    public void conexion(){
        
            String driver = "jdbc:oracle:thin:";
            String host = "localhost.localdomain"; // tambien puede ser una ip como "192.168.1.14"
            String porto = "1521";
            String sid = "orcl";
            String usuario = "hr";
            String password = "hr";
            String  url = driver + usuario + "/" + password + "@" + host + ":" + porto + ":" + sid;
        try {
            //para conectar co native protocal all java driver: creamos un obxecto Connection usando o metodo getConnection da clase  DriverManager
            conn = DriverManager.getConnection(url);
            System.out.println("Conexión realizada correctamente ");
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void insireProduto(String codigo,String nome,int prezo){
        
        try {
         ps = conn.prepareStatement("Insert into produtos(codigo,descricion,prezo) values(?,?,?)");
         ps.setString(1,codigo);
         ps.setString(2,nome);
         ps.setInt(3,prezo);
         ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro,o Codigo que intentas inxerir xa existe "+ex.getMessage());
        }
        
        
    }
    
    public void listaProdutos(){
        
        try {
          st = conn.createStatement();
          rs = st.executeQuery("SELECT * from produtos");
          while(rs.next()){
             String codigo = rs.getString("CODIGO");
             String descricion= rs.getString("DESCRICION");
             int prezo =  rs.getInt("PREZO");
              System.out.println("CODIGO :"+codigo+" DESCRICIÓN :"+descricion+" PREZO :"+prezo);
          }
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void actualizaPre(String codigo,int prezo){
        try {
            ps = conn.prepareStatement("UPDATE produtos set PREZO = ? where CODIGO = ?");
            ps.setString(2,codigo);
            ps.setInt(1,prezo);
           int val = ps.executeUpdate();
           if(val>0){
            System.out.println("Actualizado Correctamente ");
           }else{
               System.out.println("Error ao actualizar ");
           }
           
        } catch (SQLException ex) {
            System.out.println("O articulo que intenta actualizar non existe "+ex.getMessage());
        }
        
        
    }
    
    public void borraFila(String codigo){
        try {
            ps = conn.prepareStatement("DELETE from produtos where CODIGO = ?");
            ps.setString(1, codigo);
            int validar =ps.executeUpdate();
            if(validar>0){
                System.out.println("Borrado");
                
            }else{
            System.out.println("erro");
            }
        } catch (SQLException ex) {
            System.out.println("A fila que intenta borrar no existe"+ex.getMessage());;
        }
    }
    
    public void amosaFila(String co){
        try {
            ps = conn.prepareStatement("SELECT * from  produtos where CODIGO = ?");
            
            ps.setString(1, co);
            rs = ps.executeQuery();
            while(rs.next()){
             String codigo = rs.getString("CODIGO");
             String descricion= rs.getString("DESCRICION");
             int prezo =  rs.getInt("PREZO");
            
       
              System.out.println("CODIGO :"+codigo+" DESCRICIÓN :"+descricion+" PREZO :"+prezo);
              ps.executeQuery();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
