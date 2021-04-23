/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projecte5_equipament;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexioMsql {
    
        // Atributs de la connexió
        private static Connection conn = null;
        private String driver;
        private String url;
        private String usuario;
        private String password;

    // Constructor de la connexió per accedir als mètodes
    public ConexioMsql(){

        String url = "jdbc:mysql://localhost:3306/equipament";
        String driver = "com.mysql.jdbc.Driver";
        String usuario = "root";
        String password = "";

        try{
            Class.forName(driver);
            conn = DriverManager.getConnection(url, usuario, password);
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        
    }
        // Mètode per realitzar la connexió
        public Connection getConnection(){

            if (conn == null){
                new ConexioMsql();
            }

            return conn;
        } // Fin getConn
    
}
