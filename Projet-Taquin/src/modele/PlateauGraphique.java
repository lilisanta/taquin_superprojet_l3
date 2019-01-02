package modele;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.Serializable;
/**
 *Classe représentant le plateau de jeu, avec des cases numérotées et une case vide
 */
public class PlateauGraphique extends PlateauConsole implements Serializable{
    private int nbcoups,temps;
    private String image;
    
    /**
     * Constructeur du plateau
     * @param c Tableau de cases qui sert à la création du plateau
     */
    public PlateauGraphique(Case[][] c, int coups, int time, String imageFond){
        super(c);
        nbcoups=coups;
        temps=time;
        image=imageFond;
        
    }
    
    @Override
    public boolean deplacement(char direction){
        super.deplacement(direction);
        return false;
    
    }
    
    
    
    @Override
    public String toString(){
        String res="";
        for(int i=0; i < this.cases.length; i++){
            for(int j=0; j < this.cases[i].length;j++){
                res+=this.cases[i][j].toString();
            }
            res+="\n";
        }
        return res;
    }
    
    @Override
    public boolean sauvegarder(String nomSauvegarde){
        try {
            BufferedWriter out=new BufferedWriter(new FileWriter(nomSauvegarde));
            out.write(image);
            out.newLine();
            out.write(nbcoups);
            out.newLine();
            out.write(temps);
            out.newLine();
            out.write(cases.length);
            for(int i=0; i < this.cases.length; i++){
                for(int j=0; j< this.cases[i].length;j++){
                    out.newLine();
                    if(!cases[i][j].estVide()){
                        out.write(((CaseNumerotee)cases[i][j]).getNum());
                    }else{
                        out.write("V");
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }finally{
            return true;
        }
    }
        
    
    
    public static PlateauGraphique charger(String nomFich)throws IOException{

        PlateauGraphique p=null;
        
        BufferedReader in = new BufferedReader(new FileReader(nomFich));
        String imageFond=in.readLine();
        int coups=Integer.parseInt(in.readLine());
        int temps=Integer.parseInt(in.readLine());
        int taille = Integer.parseInt(in.readLine());
        Case[][] cases = new Case[taille][taille];
        String num;
        for(int i=0; i< taille;i++){
            for(int y=0; y < taille;y++){
            num = in.readLine();
            if(num.equals("V")){
                cases[i][y] = new CaseVide(i,y);
            }else{
                int src=i+1;
                 cases[i][y] = new CaseNumerotee(i,y,Integer.parseInt(num));
            }
            
            if(i == taille){
                    y=0;
                    i++;
                }
        }
        }
        
        p=new PlateauGraphique(cases,coups,temps,imageFond);
        
        return p;
    }
    public int getNbcoups() {
        return nbcoups;
    }

    public int getTemps() {
        return temps;
    }

    public String getImage() {
        return image;
    }
    
    
    
    
}
