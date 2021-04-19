
package projecte5_equipament;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.Scanner;


public class Botes {
    
    private String id;
    private String nom;
    private int talla;
    private double preu;
    
    /*CONSTRUCTOR*/
    public Botes(String id, String nom, int talla, int preu) {
        this.id = id;
        this.nom = nom;
        this.talla = talla;
        this.preu = preu;
    }

    /*CONSTRUCTOR*/
    public Botes() {
    }

    /*CONSTRUCTOR*/
    public Botes(String id) {
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

    /*METODE GUARDA EN UN STRING LES CARACTERISTIQUES DE LES BOTES PASSADES A VARIABLE*/
    public Botes cargar_botes(Botes variable){
        /*DECLARACIO VARAIBLE*/
        Botes botes = new Botes ("", "", 4, 4);
        
        try{
            /*CONNEXIO BASE DE DADES*/
            ConexioMsql conexion = new ConexioMsql();
            Connection conexio = conexion.getConnection();
            /*CONSULTA*/
            String consulta = "SELECT * FROM botes WHERE id_botes='"+variable.getId()+"'";
            /*CREEM SENTENCIA*/
            Statement sentencia = conexio.createStatement();
            /*EXECUTA CONSULTA*/
            ResultSet resultat = sentencia.executeQuery(consulta);

            /*WHILE RECORRER BASE DE DADES I GUARDA ELS VALORS*/
            while(resultat.next()){
                
                String idbota = resultat.getString("id_botes");
                String nombota = resultat.getString("nom");
                String tallabota = resultat.getString("talla");
                String preubota = resultat.getString("preu");
                
                botes = new Botes (idbota, nombota, Integer.parseInt(tallabota), Integer.parseInt(preubota));
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }        
        return botes;
    }
    
    /*METODE GUARDA TOTES LES BOTES QUE TENIM A LA BASE DE DADES EN UN ARRAY BIDIMENSIONAL*/
    public Botes[] llistar_botes(){
        /*DECLARACIO VARAIBLE*/
        Botes[] botes = new Botes [100];
        
        try{
            /*CONNEXIO BASE DE DADES*/
            ConexioMsql conexion = new ConexioMsql();
            Connection conexio = conexion.getConnection();
            /*CONSULTA*/
            String consulta = "SELECT * FROM botes";
            /*CREEM SENTENCIA*/
            Statement sentencia = conexio.createStatement();
            /*EXECUTA CONSULTA*/
            ResultSet resultat = sentencia.executeQuery(consulta);
            
            int i =0;
            /*WHILE RECORRER BASE DE DADES I GUARDA ELS VALORS*/
            while(resultat.next()){
                
                String idbota = resultat.getString("id_botes");
                String nombota = resultat.getString("nom");
                String tallabota = resultat.getString("talla");
                String preubota = resultat.getString("preu");
                
                botes[i] = new Botes (idbota, nombota, Integer.parseInt(tallabota), Integer.parseInt(preubota));
                
                i++;
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }        
        return botes;
    }
    
    /*METODE QUE IMPRIMEIX TOTES LES BOTES PER PANTALLA*/
    public void mostrar_botes(Botes[] variable){
       
       int num=1;
       System.out.println();
       System.out.println("LLISTA DE LES BOTES:");
       
       for(int i=0; i<variable.length; i++){
           if(variable[i]!=null){
               
               System.out.print(num);
               System.out.println(" ID_Bota:"+variable[i].getId()+" Nom_Bota:"+variable[i].getNom()+" Talla_Bota:"+variable[i].getTalla()+" Preu_Bota:"+variable[i].getPreu());
           }
           num++;
       }       
   }
    
    /*METODE QUE UTILITZEM PER ELEGIR LES BOTES QUE VOLEM EN EL NOSTRE KIT*/
    public Botes crear_bota(){
        Botes noubota = null;
        Scanner resp = new Scanner (System.in);
        
        String id_bota;
        
        System.out.println("Quines botes vols pel teu KIT? (Escrigui l'ID de les botes)");
        id_bota = resp.nextLine();
        
        Botes nou_bota = new Botes();
        Botes[] crea_bota = new Botes [100];
        
        crea_bota = nou_bota.llistar_botes();
        
        for(int i=0; i<crea_bota.length; i++){
            if (crea_bota[i]!=null){
                if(id_bota.equals(crea_bota[i].getId())){
                    noubota = new Botes (crea_bota[i].getId(), crea_bota[i].getNom(), crea_bota[i].getTalla(), (int) crea_bota[i].getPreu());
                }
            }
        }
        
        
     return noubota;  
    }
    
    public Botes bota_kit(String botes){
        
        Botes botanuevo = new Botes ("", "", 4, 4);
        
        try{
        
            /*CONNEXIO BASE DE DADES*/
            ConexioMsql conexion = new ConexioMsql();
            Connection conexio = conexion.getConnection();

            String consulta1 = "SELECT * FROM botes WHERE id_botes = '"+botes+"'";
            /*CREEM SENTENCIA*/
            Statement sentencia1 = conexio.createStatement();
            /*EXECUTA CONSULTA*/
            ResultSet resultat1 = sentencia1.executeQuery(consulta1);

            while(resultat1.next()){
                String id_bota = resultat1.getString("id_botes");
                String nom_bota = resultat1.getString("nom");
                String talla_bota = resultat1.getString("talla");
                String preu_bota = resultat1.getString("preu");

                botanuevo = new Botes (id_bota, nom_bota, Integer.parseInt(talla_bota), Integer.parseInt(preu_bota));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }        
    return botanuevo;
    }
   
}
