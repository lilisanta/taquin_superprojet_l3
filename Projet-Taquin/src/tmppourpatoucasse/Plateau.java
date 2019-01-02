package testgui.plateau;


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
public class Plateau implements Serializable{
    private Case[][] cases;
    private Case caseVide;
    private int nbcoups,temps;
    private String image;
    
    /**
     * Constructeur du plateau
     * @param c Tableau de cases qui sert à la création du plateau
     */
    public Plateau(Case[][] c, int coups, int time, String imageFond){
        if(c == null) try {
            throw new Exception("Tableau de cases null");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        this.cases = c;
        for(int i=0; i < cases.length; i++){
            for(int j=0; j < cases[i].length;j++){
                if(cases[i][j] instanceof CaseVide){
                    this.caseVide = this.cases[i][j];
                }
            }
        }
        nbcoups=coups;
        temps=time;
        image=imageFond;
        
    }
    
    /**
     * Méthode qui se charge du déplacement et de la vérification de sa possibilité
     * @param direction Entier représentant la direction du déplacement
     * @return true Si le déplacement a pu être effectué
     */
    public boolean deplacement(char direction){
        
        //LES CONTROLES SONT A CHANGER
        
      switch(direction){
            case 'h'://haut
                if(caseVide.getX()>0){
                    int num;
                    num = ((CaseNumerotee)cases[this.caseVide.getX()-1][this.caseVide.getY()]).getNum();
                   cases[caseVide.getX()][caseVide.getY()] = new CaseNumerotee(caseVide.getX(),caseVide.getY(),num);
                   cases[this.caseVide.getX()-1][this.caseVide.getY()] = new CaseVide(this.caseVide.getX()-1,this.caseVide.getY());
                   caseVide = cases[this.caseVide.getX()-1][this.caseVide.getY()];
                   return true;
                }
                break;
            case 'b'://bas
                if(caseVide.getX()<cases.length-1){
                   int num;
                    num = ((CaseNumerotee)cases[this.caseVide.getX()+1][this.caseVide.getY()]).getNum();
                   cases[caseVide.getX()][caseVide.getY()] = new CaseNumerotee(caseVide.getX(),caseVide.getY(),num);
                   cases[this.caseVide.getX()+1][this.caseVide.getY()] = new CaseVide(this.caseVide.getX()+1,this.caseVide.getY());
                   caseVide = cases[this.caseVide.getX()+1][this.caseVide.getY()];
                   return true;
                }
                break;
            case 'g'://gauche
                if(caseVide.getY()>0){

                    int num;
                    num = ((CaseNumerotee)cases[this.caseVide.getX()][this.caseVide.getY()-1]).getNum();
                   cases[caseVide.getX()][caseVide.getY()] = new CaseNumerotee(caseVide.getX(),caseVide.getY(),num);
                   cases[this.caseVide.getX()][this.caseVide.getY()-1] = new CaseVide(this.caseVide.getX(),this.caseVide.getY()-1);
                   caseVide = cases[this.caseVide.getX()][this.caseVide.getY()-1];
                   return true;
                }
                break;
            case 'd'://droite
                if(caseVide.getY() < cases[0].length-1){
                    int num;
                    num = ((CaseNumerotee)cases[this.caseVide.getX()][this.caseVide.getY()+1]).getNum();
                   cases[caseVide.getX()][caseVide.getY()] = new CaseNumerotee(caseVide.getX(),caseVide.getY(),num);
                   cases[this.caseVide.getX()][this.caseVide.getY()+1] = new CaseVide(this.caseVide.getX(),this.caseVide.getY()+1);
                   caseVide = cases[this.caseVide.getX()][this.caseVide.getY()+1];
                   return true;
                }
                break;
            default:
                return false;
        }
        return false;
    }
    
    public boolean verifierVictoire(){
        int compte=1;
        for(int i=0; i < this.cases.length; i++){
            for(int j=0; j< this.cases[i].length;j++){
                if(!cases[i][j].estVide()){
                    int num=((CaseNumerotee)cases[i][j]).getNum();
                    if(!(num == compte)){ //vérification de l'ordre ddes numéros
                        if(!(((CaseNumerotee)cases[i][j]).getNum() == compte)){ //vérification de l'ordre ddes numéros
                        return false;
                    }
                    }
                }else{
                    if(!(i == this.cases.length-1 && j == this.cases.length-1)){//si ce n'est pas la dernière case
                        return false;
                    }
                }
                compte++;
            }
        }
        
        
        return true;
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
    
    public boolean sauvegarder(String nomSauvegarde){
        try {
            /*ObjectOutputStream oos = null;
            try {
            oos = new ObjectOutputStream(new FileOutputStream(new File("media/plateau/"+nomSauvegarde)));
            oos.writeObject(this);
            oos.close();
            }catch(IOException e){
            e.printStackTrace();
            return false;
            }finally{
            return true;
            }*/
            
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
        
    
    
    
    public static Plateau charger(String nomFich)throws IOException{
        /*Plateau p = null;
        Object o;
        ObjectInputStream in = new ObjectInputStream((new FileInputStream(nomFich)));
        try {
            o =  in.readObject();
            p = (Plateau) o;
            in.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Plateau.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return p;*/
        
        Plateau p=null;
        
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
        
        p=new Plateau(cases,coups,temps,imageFond);
        
        return p;
    }

    public Case[][] getCases() {
        return cases;
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
