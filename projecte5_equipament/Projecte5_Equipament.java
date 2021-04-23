/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projecte5_equipament;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.sql.Statement.RETURN_GENERATED_KEYS;
import java.util.Scanner;

public class Projecte5_Equipament {
    
    static Scanner teclat = new Scanner(System.in);
    /*CRIDA DEL CONSTRUCTOR Kits*/
    static Kits kit = new Kits();
    /*CRIDA DEL CONSTRUCTOR Esquis*/
    static Esquis esquis = new Esquis();
    /*CRIDA DEL CONSTRUCTOR Pals*/
    static Pals pals = new Pals();
    /*CRIDA DEL CONSTRUCTOR Botes*/
    static Botes botes = new Botes();
    /*CREAR UN ARRAY KITS*/
    static Kits[] kits;
    /*CREAR UN ARRAY Esquis*/
    static Esquis[] llista_esquis;
    /*CREAR UN ARRAY Pals*/
    static Pals[] llista_pals;     
    /*CREAR UN ARRAY Botes*/
    static Botes[] llista_botes;

    public static void main(String[] args) {
        int resposta;
        boolean sortir = false;
        /*LLISTAT BOTES*/
        llista_botes = botes.llistar_botes();
        
        /*MENU PRINCIPAL*/
        while (!sortir) {
            System.out.println();
            System.out.println("******LLOGUER D'EQUIPAMENT D'ESQUÍ*******");
            System.out.println("*---------------------------------------*");
            System.out.println("*            Tria una opció:            *");
            System.out.println("*---------------------------------------*");
            System.out.println("* 1. Mostrar Kits llogats               *");
            System.out.println("* 2. Kit més econòmic                   *");
            System.out.println("* 3. Consultes sobre material           *");
            System.out.println("* 4. Llogar un Kit                      *");
            System.out.println("* 5. Sortir                             *");
            System.out.println("*****************************************");

            if (!teclat.hasNextInt()) {
                System.out.println("ERROR! Has d'introduir un número!");
                teclat.next();
            } else {
                resposta = teclat.nextInt();

                switch (resposta) {
                    
                    case 1:
                        /*MOSTREM TOTS ELS KITS LLOGATS DE L'EMPRESA*/ 
                        System.out.println("Has triat: Mostrat Kits llogats.");
                        System.out.println();
                        
                        /*ARRAY KITS*/
                        kits = kit.cargar_kit();
                        
                        System.out.println("KITS LLOGATS:");
                        
                        int num = 1;
                        
                        Esquis materialesquis = new Esquis("", "", 4, 4);
                        Botes materialbotes = new Botes ("", "", 4, 4);
                        Pals materialpals =new Pals ("", "", 4, 4);
                        
                        /*FOR PER RECCORRE EL ARRAY BIDIMENSIONAL ON TENIM GUARDATS EL KITS*/
                        for(int i=0; i<kits.length; i++){
                            if(kits[i]!=null){
                                double preutotal = 0;
                                System.out.println(+num+" ID_Kit: "+kits[i].getId());
                                       
                                /*CRIDEM METODE GUARDAR NOMES ESQUIS*/;
                                materialesquis = esquis.cargar_esquis(kits[i].getEsqui());
                                System.out.println("ESQUI: Nom_Esqui: "+materialesquis.getNom()+" Talla_Esqui: "+materialesquis.getTalla()+" Preu_Esqui: "+materialesquis.getPreu());
                                preutotal = preutotal+materialesquis.getPreu();
                                /*CRIDEM METODE GUARDAR NOMES PALS*/
                                materialpals = pals.cargar_pals(kits[i].getPals());
                                System.out.println("PAL: Nom_Pal: "+materialpals.getNom()+" Talla_Pal: "+materialpals.getTalla()+" Preu_Pal: "+materialpals.getPreu());
                                preutotal = preutotal+materialpals.getPreu();
                                /*CRIDEM METODE GUARDAR NOMES BOTES*/
                                materialbotes = botes.cargar_botes(kits[i].getBotes());
                                System.out.println("BOTA: Nom_Bota: "+materialbotes.getNom()+" Talla_Bota: "+materialbotes.getTalla()+" Preu_Bota: "+materialbotes.getPreu()); 
                                preutotal = preutotal+materialbotes.getPreu();
                                
                                System.out.println("Preu total KIT: "+preutotal);
                                
                                System.out.println();
                                
                                num++;
                            }
                        }
                        
                    break;
                    
                    case 2:
                        /*MOSTREM EL KIT MES ECONOMIC DELS QUE ESTAN LLOGATS*/
                        System.out.println("Has triat: Kit mes economic.");
                        System.out.println();
                        
                        /*ARRAY KITS*/
                        kits = kit.cargar_kit();
                        
                        System.out.println("KITS MES ECONOMIC:");
                        
                        double kitbarato=2000;
                        String idkit = null;
                        Esquis idkitesquis = null;
                        Pals idkitpals = null;
                        Botes idkitbotes = null;
                        
                        Esquis preuesqui;
                        Botes preubote;
                        Pals preupal;
                        
                        /* FOR PER RECORRER ELS KITS LLOGATS, CALCULAR EL PREU DE CADASCU I VEURE QUIN ES EL MES RCONOMIC*/
                        for(int i=0; i<kits.length; i++){
                            
                            double preutotal=0;
                            
                            if(kits[i]!=null){
                                       
                                /*CRIDEM METODE PER VEURE NOMES L'ESQUI DEL KIT I PODER SUMAR EL SEU PREU*/                                
                                preuesqui = esquis.cargar_esquis(kits[i].getEsqui());
                                preutotal = preutotal+preuesqui.getPreu();
                                /*CRIDEM METODE PER VEURE NOMES EL PAL DEL KIT I PODER SUMAR EL SEU PREU*/
                                preupal = pals.cargar_pals(kits[i].getPals());
                                preutotal = preutotal+preupal.getPreu();
                                /*CRIDEM METODE PER VEURE NOMES LES BOTES DEL KIT I PODER SUMAR EL SEU PREU*/
                                preubote = botes.cargar_botes(kits[i].getBotes());
                                preutotal = preutotal+preubote.getPreu();
                                
                                /*COMPROVAR EL RPEU MES ECONOMIC*/
                                if(preutotal<kitbarato){
                                    kitbarato = preutotal;
                                    idkit=kits[i].getId();
                                    idkitesquis=kits[i].getEsqui();
                                    idkitpals=kits[i].getPals();
                                    idkitbotes=kits[i].getBotes();
                                    
                                }
                            }
                        }
                        
                        Esquis soutesqui = new Esquis ("", "", 4, 4);
                        Botes soutbotes = new Botes ("", "", 4, 4);
                        Pals soutpals =new Pals ("", "", 4, 4);
                        
                        double preutotal = 0;
                        
                            System.out.println("ID_Kit: "+idkit);

                            /*CRIDEM METODE AMB L'ESQUI QUE VOLEM IMPRIMER PER PANTALLA DE PARAMETRE*/
                            soutesqui = esquis.cargar_esquis(idkitesquis);
                            System.out.println("ESQUI: Nom_Esqui: "+soutesqui.getNom()+" Talla_Esqui: "+soutesqui.getTalla()+" Preu_Esqui: "+soutesqui.getPreu());
                            preutotal = preutotal+soutesqui.getPreu();
                            /*CRIDEM METODE AMB EL PAL QUE VOLEM IMPRIMER PER PANTALLA DE PARAMETRE*/
                            soutpals = pals.cargar_pals(idkitpals);
                            System.out.println("PAL: Nom_Pal: "+soutpals.getNom()+" Talla_Pal: "+soutpals.getTalla()+" Preu_Pal: "+soutpals.getPreu());
                            preutotal = preutotal+soutpals.getPreu();
                            /*CRIDEM METODE AMB LES BOTES QUE VOLEM IMPRIMER PER PANTALLA DE PARAMETRE*/
                            soutbotes = botes.cargar_botes(idkitbotes);
                            System.out.println("BOTA: Nom_Bota: "+soutbotes.getNom()+" Talla_Bota: "+soutbotes.getTalla()+" Preu_Bota: "+soutbotes.getPreu()); 
                            preutotal = preutotal+soutbotes.getPreu();

                            System.out.println("Preu total KIT: "+preutotal);

                            System.out.println();
                            
                    break;
                    
                    case 3:
                        /*CONSULTAR EN QUANTS KITS ES TROBA UN MATERIAL*/   
                        System.out.println();
                        System.out.println("*----------------------------------------*");
                        System.out.println("* Tria una opció de CONSULTA de material *");
                        System.out.println("*----------------------------------------*");
                        System.out.println("* 1. Consultar per esquis                *");
                        System.out.println("* 2. Consultar per pals                  *");
                        System.out.println("* 3. Consultes per botes                 *");
                        System.out.println("******************************************");
                        
                        if (!teclat.hasNextInt()) {
                            System.out.println("ERROR! Has d'introduir un número!");
                            teclat.next();
                        } else {
                            resposta = teclat.nextInt();

                            switch (resposta) {
                                case 1:
                                    String id_esqui;
                                    
                                    /*LLISTAT ESQUIS*/
                                    llista_esquis = esquis.llistar_esquis();
                                    esquis.mostrar_esquis(llista_esquis);
                                    
                                    /*ELEGEIX ESQUI QUE VOL CONSULTAR*/
                                    System.out.println();
                                    System.out.println("Quin esqui vols consultar? (Escrigui el seu ID)");
                                    teclat.nextLine();
                                    id_esqui=teclat.next();
                                    
                                    System.out.println();
                                    
                                    /*COMPROVEM SI EL ID INTRODUIT EXISTEIX*/
                                    int comprovador_esqui =0;
                                    for(int i=0; i<llista_esquis.length; i++){
                                        if(llista_esquis[i]!=null){
                                            if(id_esqui.equals(llista_esquis[i].getId())){
                                                comprovador_esqui++;
                                            }
                                        }
                                    }
                                    /*COMPROVEM SI EL ID INTRODUIT EXISTEIX*/
                                    if(comprovador_esqui==0){
                                        System.out.println("Aquest ID no correspon a cap esqui.");
                                        break;
                                    }
                                    
                                    int contador = 0;
                                    /*COMPROVEM EN QUANTS KITS ES TROBA EL MATERIAL*/
                                    for(int x=0; x<kits.length; x++){
                                        if(kits[x]!=null){    
                                            if(kits[x].getEsqui().getId().equals(id_esqui)){
                                                contador++;
                                            } 
                                        }
                                    }                                    
                                    if(contador!=0){
                                        System.out.println("L'esqui que has seleccionat amb ID: "+id_esqui+", es troben en "+contador+" Kits.");
                                    }

                                break;
                                
                                case 2:
                                    String id_pal;
                                    
                                    /*LLISTAT PALS*/
                                    llista_pals = pals.llistar_pals();
                                    pals.mostrar_pals(llista_pals);
                                    
                                    /*ELEGEIX PAL QUE VOL CONSULTAR*/
                                    System.out.println();
                                    System.out.println("Quin pal vol consultar? (Escrigui el seu ID)");
                                    teclat.nextLine();
                                    id_pal=teclat.next();
                                    
                                    System.out.println();
                                    
                                    int contador_pals = 0;
                                    
                                    /*COMPROVEM SI EL ID INTRODUIT EXISTEIX*/
                                    int comprovador_pals =0;
                                    for(int i=0; i<llista_pals.length; i++){
                                        if(llista_pals[i]!=null){
                                            if(id_pal.equals(llista_pals[i].getId())){
                                                comprovador_pals++;
                                            }
                                        }
                                    }
                                    
                                    if(comprovador_pals==0){
                                        System.out.println("Aquest ID no correspon a cap pal.");
                                        break;
                                    }
                                    
                                    /*COMPROVEM EN QUANTS KITS ES TROBA EL MATERIAL*/
                                    for(int x=0; x<kits.length; x++){
                                        if(kits[x]!=null){    
                                            if(kits[x].getPals().getId().equals(id_pal)){
                                                contador_pals++;
                                            }
                                        }
                                    }                                    
                                    if(contador_pals!=0){
                                        System.out.println("Els pals que has seleccionat amb ID: "+id_pal+", es troben en "+contador_pals+" Kits.");
                                    }
                                break;
                                
                                case 3:
                                    String id_bota;
                                    
                                    /*LLISTAT PALS*/
                                    llista_botes = botes.llistar_botes();
                                    botes.mostrar_botes(llista_botes);
                                    
                                    /*ELEGEIX ESQUI QUE VOL CONSULTAR*/
                                    System.out.println();
                                    System.out.println("Quina bota vol consultar? (Escrigui el seu ID)");
                                    teclat.nextLine();
                                    id_bota=teclat.next();
                                    
                                    System.out.println();
                                    
                                    int contador_botes = 0;
                                    
                                    /*COMPROVEM SI EL ID INTRODUIT EXISTEIX*/
                                    int comprovador =0;
                                    for(int i=0; i<llista_botes.length; i++){
                                        if(llista_botes[i]!=null){
                                            if(id_bota.equals(llista_botes[i].getId())){
                                                comprovador++;
                                            }
                                        }
                                    }
                                    if(comprovador==0){
                                        System.out.println("Aquest ID no correspon a cap bota.");
                                        break;
                                    }
                                    
                                    /*COMPROVEM EN QUANTS KITS ES TROBA EL MATERIAL*/
                                    for(int x=0; x<kits.length; x++){
                                        if(kits[x]!=null){    
                                            if(kits[x].getBotes().getId().equals(id_bota)){
                                                contador_botes++;
                                            }
                                        }
                                    }
                                    if(contador_botes==0 || contador_botes>0){
                                        System.out.println("Les botes que has seleccionat amb ID: "+id_bota+", es troben en "+contador_botes+" Kits.");
                                    }                                    
                                break;
                                
                            }
                        }
 
                    break;
                    
                    case 4:
                        
                        System.out.println("CREA EL TEU KIT PERSONALITZAT");
                        
                        /*DECLARACIO DE LES VARIABLES ESQUI, BOTA I PAL*/
                        Esquis creacionouesqui = new Esquis("", "", 4, 4);
                        Pals creacionoupal = new Pals("", "", 4, 4);
                        Botes creacionoubota = new Botes("", "", 4, 4); 
                        
                        /*UTILITAZEM ELS METODES PER LLISTAR ELS ESQUIS, MOSTRALS PER PANTALLA I QUE EL CLIENT ELEGEIXI EL QUE VOL*/
                        /*LLISTAT ESQUIS*/
                        llista_esquis = esquis.llistar_esquis();
                        esquis.mostrar_esquis(llista_esquis);
                        System.out.println();
                        creacionouesqui = esquis.crear_esqui();
                        
                        /*UTILITAZEM ELS METODES PER LLISTAR ELS PALS, MOSTRALS PER PANTALLA I QUE EL CLIENT ELEGEIXI EL QUE VOL*/
                        /*LLISTAT PALS*/
                        llista_pals = pals.llistar_pals();
                        pals.mostrar_pals(llista_pals);
                        System.out.println();
                        creacionoupal = pals.crear_pal();
                        
                        /*UTILITAZEM ELS METODES PER LLISTAR LES BOTES, MOSTRALS PER PANTALLA I QUE EL CLIENT ELEGEIXI EL QUE VOL*/
                        /*LLISTAT PALS*/
                        llista_botes = botes.llistar_botes();
                        botes.mostrar_botes(llista_botes);
                        System.out.println();
                        creacionoubota = botes.crear_bota();
                        
                        if (creacionouesqui == null || creacionoupal== null || creacionoubota==null){
                            System.out.println();
                            System.out.println("ERROR: Algun element del KIT no ha estat seleccionat correctament.");
                            break;
                        }
                        
                        double preu_total = 0;
                        
                        /*MOSTREM EL KIT QUE HA PERSONALITZAT*/
                        System.out.println();
                        System.out.println("El KIT que ha personalitzat es:");
                        System.out.println("Esqui: Nom_Esquis: "+creacionouesqui.getNom()+" Talla_Esquis: "+creacionouesqui.getTalla()+" Preu_Esquis: "+creacionouesqui.getPreu());
                        System.out.println("Pals: Nom_Pals: "+creacionoupal.getNom()+" Talla_Pals: "+creacionoupal.getTalla()+" Preu_Pals: "+creacionoupal.getPreu());
                        System.out.println("Botes: Nom_Botes: "+creacionoubota.getNom()+" Talla_Botes: "+creacionoubota.getTalla()+" Preu_Botes: "+creacionoubota.getPreu());
                        preu_total = (int) (preu_total + creacionouesqui.getPreu() + creacionoupal.getPreu() + creacionoubota.getPreu());
                        System.out.println("Preu total Kit: "+ preu_total);
                        
                        /*OBTENIM ELS ID DELS MATERIALS UTILITZATS PEL KIT*/
                        String id_esqui = creacionouesqui.getId();
                        String id_bota = creacionoubota.getId();
                        String id_pal = creacionoupal.getId();
                        
                    try{
                        /*CONNEXIO BASE DE DADES*/
                        ConexioMsql conexion = new ConexioMsql();
                        Connection conexio = conexion.getConnection();
                        /*CONSULTA GUARDA ID A ALA BASE DE DADES*/
                        String consulta = "INSERT INTO kit (id_esquis, id_botes, id_pals) VALUES ('"+id_esqui+"', '"+id_bota+"', '"+id_pal+"')";
                        /*FEM SERVIR EL PREAPRE STATEMENT PER AIXI PODER OBTENIR DESPRES EL ID QUE ENS ATRIBUEIX*/
                        PreparedStatement sentencia = null;
                        
                        sentencia = conexio.prepareStatement(consulta, RETURN_GENERATED_KEYS);
                        /*EXECUTEM LA CONSULTA*/
                        if(sentencia.executeUpdate()!=0){
                            System.out.println();
                            System.out.println("KIT creat correctament.");
                        }else{
                            System.out.println();
                            System.out.println("ERROR: Hi ha hagut algun error en el procès.");
                        }
                        
                        ResultSet rs =sentencia.getGeneratedKeys();
                        /*GUARDEM EL ID ATRIBUIT PER LA BASE DE DADES EN idKit*/
                        String idKit = null;
                        
                        if(rs.next()){
                            idKit = rs.getString(1);
                        }

                        /*CREEM EL KIT AMB LES VARIBLES SELECCIONADES PEL CLIENT*/
                        Kits nuevo_kit = new Kits(idKit, creacionouesqui, creacionoubota, creacionoupal);
  
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    
                    break;
                    
                    case 5:
                        System.out.println("Has triat: Sortir");
                        System.out.println("Adeu!");
                        sortir=true;
                    break;
                    
                    default:
                        System.out.println("ERROR: Opció incorrecta");
                        System.out.println("Torni-ho a provar");
                    break;
                    
                }
            }
    
        }
    }
}
