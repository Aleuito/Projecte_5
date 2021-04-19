
package projecte5_equipament;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.Scanner;

public class Esquis {
    private String id;
    private String nom;
    private int talla;
    private double preu;
    
    /*CONSTRUCTOR*/
    public Esquis(String id, String nom, int talla, int preu) {
        this.id = id;
        this.nom = nom;
        this.talla = talla;
        this.preu = preu;
    }
    
    /*CONSTRUCTOR*/
    public Esquis(String id) {
        this.id = id;
    }
    
    /*CONSTRUCTOR*/
    public Esquis() {
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
    
    /*METODE GUARDA EN UN STRING LES CARACTERISTIQUES DEL ESQUI PASSAT A VARIABLE*/
    public Esquis cargar_esquis(Esquis variable){
        /*DECLARACIO VARAIBLE*/
        Esquis esquis =new Esquis("", "", 4, 4);
        
        try{
            /*CONNEXIO BASE DE DADES*/
            ConexioMsql conexion = new ConexioMsql();
            Connection conexio = conexion.getConnection();
            /*CONSULTA*/
            String consulta = "SELECT * FROM esquis WHERE id_esquis='"+variable.getId()+"'";
            /*CREEM SENTENCIA*/
            Statement sentencia = conexio.createStatement();
            /*EXECUTA CONSULTA*/
            ResultSet resultat = sentencia.executeQuery(consulta);
           
            /*WHILE RECORRER BASE DE DADES I GUARDA ELS VALORS*/
            while(resultat.next()){
                
                String idesqui = resultat.getString("id_esquis");
                String nomesqui = resultat.getString("nom");
                String tallaesqui = resultat.getString("talla");
                String preuesqui = resultat.getString("preu");
                
                esquis = new Esquis (idesqui, nomesqui, Integer.parseInt(tallaesqui), Integer.parseInt(preuesqui));
               
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }        
        return esquis;
    }
    
    /*METODE GUARDA TOTS ELS ESQUIS QUE TENIM A LA BASE DE DADES EN UN ARRAY BIDIMENSIONAL*/
    public Esquis[] llistar_esquis(){
        /*DECLARACIO VARAIBLE*/
        Esquis[] esquis = new Esquis [100];
        
        try{
            /*CONNEXIO BASE DE DADES*/
            ConexioMsql conexion = new ConexioMsql();
            Connection conexio = conexion.getConnection();
            /*CONSULTA*/
            String consulta = "SELECT * FROM esquis";
            /*CREEM SENTENCIA*/
            Statement sentencia = conexio.createStatement();
            /*EXECUTA CONSULTA*/
            ResultSet resultat = sentencia.executeQuery(consulta);
            
            int i = 0;
            /*WHILE RECORRER BASE DE DADES I GUARDA ELS VALORS*/
            while(resultat.next()){
                
                String idesqui = resultat.getString("id_esquis");
                String nomesqui = resultat.getString("nom");
                String tallaesqui = resultat.getString("talla");
                String preuesqui = resultat.getString("preu");
                
                esquis[i] = new Esquis (idesqui, nomesqui, Integer.parseInt(tallaesqui), Integer.parseInt(preuesqui));
                
                i++;
               
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }        
        return esquis;
    }
    
    /*METODE QUE IMPRIMEIX TOTS ELS ESQUIS PER PANTALLA*/
    public void mostrar_esquis(Esquis[] variable){
       
       int num=1;
       System.out.println();
       System.out.println("LLISTA DELS ESQUIS:");
       
       for(int i=0; i<variable.length; i++){
           
           if(variable[i]!=null){
                System.out.print(num);
                System.out.println(" ID_Esqui:"+variable[i].getId()+" Nom_Esqui:"+variable[i].getNom()+" Talla_Esqui:"+variable[i].getTalla()+" Preu_Esqui:"+variable[i].getPreu());                
           }
           num++;
       }       
   }
    
    /*METODE QUE UTILITZEM PER ELEGIR L'ESQUI QUE VOLEM EN EL NOSTRE KIT*/
    public Esquis crear_esqui(){
        Esquis nouesqui = null;
        Scanner resp = new Scanner (System.in);
        
        String id_esqui;
        
        System.out.println("Quin esquí vols pel teu KIT? (Escrigui l'ID del esqui)");
        id_esqui = resp.nextLine();
        
        Esquis nou_esqui = new Esquis();
        Esquis[] crea_esqui = new Esquis [100];
        
        crea_esqui = nou_esqui.llistar_esquis();
        
        for(int i=0; i<crea_esqui.length; i++){
            if (crea_esqui[i]!=null){
                if(id_esqui.equals(crea_esqui[i].getId())){
                    nouesqui = new Esquis (crea_esqui[i].getId() , crea_esqui[i].getNom(), crea_esqui[i].getTalla(), (int) crea_esqui[i].getPreu());
                }
            }
        }
        
        
     return nouesqui;  
    }
    
    public Esquis esqui_kit(String esquis){
        
        Esquis esquinuevo = new Esquis ("", "", 4, 4);
        
        try{
        
            /*CONNEXIO BASE DE DADES*/
            ConexioMsql conexion = new ConexioMsql();
            Connection conexio = conexion.getConnection();

            String consulta1 = "SELECT * FROM esquis WHERE id_esquis = '"+esquis+"'";
            /*CREEM SENTENCIA*/
            Statement sentencia1 = conexio.createStatement();
            /*EXECUTA CONSULTA*/
            ResultSet resultat1 = sentencia1.executeQuery(consulta1);

            while(resultat1.next()){
                String id_esqui = resultat1.getString("id_esquis");
                String nom_esqui = resultat1.getString("nom");
                String talla_esqui = resultat1.getString("talla");
                String preu_esqui = resultat1.getString("preu");

                esquinuevo = new Esquis (id_esqui, nom_esqui, Integer.parseInt(talla_esqui), Integer.parseInt(preu_esqui));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }        
    return esquinuevo;
    }
}
