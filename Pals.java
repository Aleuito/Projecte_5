
package projecte5_equipament;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.Scanner;

public class Pals {
    
    private String id;
    private String nom;
    private int talla;
    private double preu;

    /*CONSTRUCTOR*/
    public Pals(String id, String nom, int talla, int preu) {
        this.id = id;
        this.nom = nom;
        this.talla = talla;
        this.preu = preu;
    }

    /*CONSTRUCTOR*/
    public Pals() {
    }

    /*CONSTRUCTOR*/
    public Pals(String id) {
        this.id = id;
    }

    /*GETTERS AND SETTERS*/
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getTalla() {
        return talla;
    }

    public void setTalla(int talla) {
        this.talla = talla;
    }

    public double getPreu() {
        return preu;
    }

    public void setPreu(int preu) {
        this.preu = preu;
    }
    
    /*METODE GUARDA EN UN STRING LES CARACTERISTIQUES DELS PALS PASSATS A LA VARIABLE*/
    public Pals cargar_pals(Pals variable){
        /*DECLARACIO VARAIBLE*/
        Pals pals = new Pals ("", "", 4, 4);
        
        try{
            /*CONNEXIO BASE DE DADES*/
            ConexioMsql conexion = new ConexioMsql();
            Connection conexio = conexion.getConnection();
            /*CONSULTA*/
            String consulta = "SELECT * FROM pals WHERE id_pals='"+variable.getId()+"'";
            /*CREEM SENTENCIA*/
            Statement sentencia = conexio.createStatement();
            /*EXECUTA CONSULTA*/
            ResultSet resultat = sentencia.executeQuery(consulta);

            /*WHILE RECORRER BASE DE DADES I GUARDA ELS VALORS*/
            while(resultat.next()){
                
                String idpal = resultat.getString("id_pals");
                String nompal = resultat.getString("nom");
                String tallapal = resultat.getString("talla");
                String preupal = resultat.getString("preu");
                
                pals = new Pals (idpal, nompal, Integer.parseInt(tallapal), Integer.parseInt(preupal));
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }        
        return pals;
    } 
   
    /*METODE GUARDA TOTS ELS PALS QUE TENIM A LA BASE DE DADES EN UN ARRAY BIDIMENSIONAL*/
    public Pals[] llistar_pals(){
        /*DECLARACIO VARAIBLE*/
        Pals[] pals = new Pals [100];
        
        try{
            /*CONNEXIO BASE DE DADES*/
            ConexioMsql conexion = new ConexioMsql();
            Connection conexio = conexion.getConnection();
            /*CONSULTA*/
            String consulta = "SELECT * FROM pals";
            /*CREEM SENTENCIA*/
            Statement sentencia = conexio.createStatement();
            /*EXECUTA CONSULTA*/
            ResultSet resultat = sentencia.executeQuery(consulta);
            
            int i = 0;
            /*WHILE RECORRER BASE DE DADES I GUARDA ELS VALORS*/
            while(resultat.next()){
                
                String idpal = resultat.getString("id_pals");
                String nompal = resultat.getString("nom");
                String tallapal = resultat.getString("talla");
                String preupal = resultat.getString("preu");
                
                pals[i] = new Pals (idpal, nompal, Integer.parseInt(tallapal), Integer.parseInt(preupal));
                
                i++;
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }        
        return pals;
    } 
   
    /*METODE QUE IMPRIMEIX TOTS ELS PALS PER PANTALLA*/
    public void mostrar_pals(Pals[] variable){
       
       int num=1;
       System.out.println();
       System.out.println("LLISTA DELS PALS:");
       
       for(int i=0; i<variable.length; i++){
           
           if(variable[i]!=null){
               
               System.out.print(num);
               System.out.println(" ID_Pal:"+variable[i].getId()+" Nom_Pal:"+variable[i].getNom()+" Talla_Pal:"+variable[i].getTalla()+" Preu_Pal:"+variable[i].getPreu());
           }
           num++;
       }       
   }
   
    /*METODE QUE UTILITZEM PER ELEGIR ELS PALS QUE VOLEM EN EL NOSTRE KIT*/
    public Pals crear_pal(){
        Pals noupal = null;
        Scanner resp = new Scanner (System.in);
        
        String id_pal;
        
        System.out.println("Quins pals vols pel teu KIT? (Escrigui l'ID dels pals)");
        id_pal = resp.nextLine();
        
        Pals nou_pal = new Pals();
        Pals[] crea_pal = new Pals [100];
        
        crea_pal = nou_pal.llistar_pals();
        
        for(int i=0; i<crea_pal.length; i++){
            if (crea_pal[i]!=null){
                if(id_pal.equals(crea_pal[i].getId())){
                    noupal = new Pals (crea_pal[i].getId(), crea_pal[i].getNom(), crea_pal[i].getTalla(), (int) crea_pal[i].getPreu());
                }
            }
        }    
     return noupal;  
    }
    
    public Pals pal_kit(String pal){
        
        Pals palnuevo = new Pals ("", "", 4, 4);
        
        try{
        
            /*CONNEXIO BASE DE DADES*/
            ConexioMsql conexion = new ConexioMsql();
            Connection conexio = conexion.getConnection();

            String consulta1 = "SELECT * FROM pals WHERE id_pals = '"+pal+"'";
            /*CREEM SENTENCIA*/
            Statement sentencia1 = conexio.createStatement();
            /*EXECUTA CONSULTA*/
            ResultSet resultat1 = sentencia1.executeQuery(consulta1);

            while(resultat1.next()){
                String id_pal = resultat1.getString("id_pals");
                String nom_pal = resultat1.getString("nom");
                String talla_pal = resultat1.getString("talla");
                String preu_pal = resultat1.getString("preu");

                palnuevo = new Pals (id_pal, nom_pal, Integer.parseInt(talla_pal), Integer.parseInt(preu_pal));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }        
    return palnuevo;
    }
   
}
