/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author guillaume
 */
public class DBHelper {
    private static String username = "pware_userprojet";
    private static String pass = "superprojet";
    
    
    public static List<String> getScoresSolo(){
        List<String> res = new ArrayList<String>();
        Connection con;
        try {
            con = DriverManager.getConnection("jdbc:mysql://mysql-pware.alwaysdata.net/pware_scores_projet",username,pass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Scores ORDER BY nbCoups");
            while(rs.next()){
                String pseudo = rs.getString("pseudo");
                String nb = rs.getString("nbCoups");
                String temps = rs.getString("tempsPartie");
                res.add(pseudo);
                res.add(nb);
                res.add(temps);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
            
        }

        
        
        return res;
    }
    
    public static List<String> getScoresCoop(){
        List<String> res = new ArrayList<String>();
        Connection con;
        try {
            con = DriverManager.getConnection("jdbc:mysql://mysql-pware.alwaysdata.net/pware_scores_projet",username,pass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM ScoresCoop ORDER BY nbCoups");
            while(rs.next()){
                String pseudo = rs.getString("pseudo");
                String nb = rs.getString("nbCoups");
                String temps = rs.getString("tempsPartie");
                res.add(pseudo);
                res.add(nb);
                res.add(temps);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
            
        }

        
        
        return res;
    }
    
    public static List<String> getScoresCompet(){
        List<String> res = new ArrayList<String>();
        Connection con;
        try {
            con = DriverManager.getConnection("jdbc:mysql://mysql-pware.alwaysdata.net/pware_scores_projet",username,pass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM ScoresCompet ORDER BY nbCoups");
            while(rs.next()){
                String pseudo = rs.getString("pseudo");
                String nb = rs.getString("nbCoups");
                String temps = rs.getString("tempsPartie");
                res.add(pseudo);
                res.add(nb);
                res.add(temps);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
            
        }

        
        
        return res;
    }
    
    public boolean insertScores(String typePartie,String pseudo, int nbCoups, String temps,String date){
        String nomTable = "";
        switch(typePartie){
            case "solo":
                nomTable = "Scores";
                break;
            case "coop":
                nomTable = "ScoresCoop";
                break;
            case "compet":
                nomTable = "ScoresCompet";
                break;
        }
        
        
        Connection con;
        try {
            con = DriverManager.getConnection("jdbc:mysql://mysql-pware.alwaysdata.net/pware_scores_projet",username,pass);
            Statement st = con.createStatement();
            String requete = "INSERT INTO "+nomTable+" VALUES("+pseudo+","+nbCoups+","+temps+","+date+");";
            st.executeQuery(requete);
            con.close();
        } catch (SQLException ex) {
           System.err.println("Connexion impossible à la base de données");
           ex.printStackTrace();
            
        }
        
        
        
        
        return true;
    }

}
