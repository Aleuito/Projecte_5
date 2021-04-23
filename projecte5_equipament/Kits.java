
package projecte5_equipament;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Kits {
    
    private String id;
    private Esquis esqui;
    private Botes botes;
    private Pals pals;

    /*CONSTRUCTOR*/
    public Kits(String id, Esquis esqui, Botes botes, Pals pals) {
        this.id = id;
        this.esqui = esqui;
        this.botes = botes;
        this.pals = pals;
    }

    /*CONSTRUCTOR*/
    public Kits() {
    }

    /*GETTERS AND SETTERS*/
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Esquis getEsqui() {
        return esqui;
    }

    public void setEsqui(Esquis esqui) {
        this.esqui = esqui;
    }

    public Botes getBotes() {
        return botes;
    }

    public void setBotes(Botes botes) {
        this.botes = botes;
    }

    public Pals getPals() {
        return pals;
    }

    public void setPals(Pals pals) {
        this.pals = pals;
    }
    
    /*METODE QUE GUARDA EN UN ARRAY BIDIMENSIONAL TOTS ELS KITS LLOGATS DE LA BASEE DE DADES*/
    public Kits[] cargar_kit(){
        /*DECLARACIO VARAIBLE*/
        Kits[] kit = new Kits [100];
        
        Esquis esqui = new Esquis();
        Esquis nuevoesqui = new Esquis ("", "", 4, 4);
        
        Pals pal = new Pals();
        Pals nuevopal = new Pals ("", "", 4, 4);
        
        Botes bota = new Botes();
        Botes nuevobota = new Botes ("", "", 4, 4);
        
      
        try{
            /*CONNEXIO BASE DE DADES*/
            ConexioMsql conexion = new ConexioMsql();
            Connection conexio = conexion.getConnection();
            /*CONSULTA*/
            String consulta = "SELECT * FROM kit";
            /*CREEM SENTENCIA*/
            Statement sentencia = conexio.createStatement();
            /*EXECUTA CONSULTA*/
            ResultSet resultat = sentencia.executeQuery(consulta);
            
            int i=0;
            
            /*VARIABLES*/
            String idkit = null; 
            String esquis = null;
            String pals= null;
            String botes = null;
            String preutotal = null;
   
            /*WHILE RECORRER BASE DE DADES I GUARDA ELS VALORS*/
            while(resultat.next()){
                
                idkit = resultat.getString("id_kit");
                esquis = resultat.getString("id_esquis");
                pals = resultat.getString("id_pals");
                botes = resultat.getString("id_botes");
                
                nuevoesqui = esqui.esqui_kit(esquis);
                nuevopal = pal.pal_kit(pals);
                nuevobota = bota.bota_kit(botes);
                
                kit[i] = new Kits (idkit, nuevoesqui, nuevobota, nuevopal);

                i++;
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }        
        return kit;
    }
  
}
