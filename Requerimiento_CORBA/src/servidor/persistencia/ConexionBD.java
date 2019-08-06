/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author DD
 */
public class ConexionBD {      
   private Connection con;    
   private String bd;
   private String login;
   private String password;
   private String url;
    
   
    public ConexionBD() {
        con=null;
        bd="bdanteproyecto";
        login="root";
        password="";
        url = "jdbc:mysql://localhost/"+bd;
    }
    /**Permite hacer la conexion con la base de datos    
     */
    public int conectar(){
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
           //crea una instancia de la controlador de la base de datos
            con = DriverManager.getConnection(url,login,password);
            // gnera una conexión con la base de datos
            return 1;
        }
        catch(SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e){
            System.out.println("Error: " + e.getMessage());
        }
        return -1;
    }
    /**Cierra la conexion con la base de datos
     *
     */
   public void desconectar(){
       try{
            con.close();
        }
        catch(Exception e){
            System.out.println("Error " + e.getMessage());
        }
   }
     /**Retorna un objeto que almacena la referencia a la conexion con la base de datos
     *
     */
    public Connection getConnection(){
      return con;
   }
 
   
}
