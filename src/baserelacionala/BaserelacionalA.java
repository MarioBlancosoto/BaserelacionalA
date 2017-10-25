
package baserelacionala;


public class BaserelacionalA {

   
    public static void main(String[] args) {
     
        
        Conexion conexion = new Conexion();
        conexion.conexion();
        conexion.insireProduto("p1","Parafusos",3);
        //conexion.insireProduto("P2", "Cravos",4);
        //conexion.insireProduto("P4", "Tachas", 6);
        //conexion.actualizaPre("P4",10);
        //conexion.listaProdutos();
        //conexion.borraFila("P4");
        conexion.amosaFila("p1");
    }
    
}
