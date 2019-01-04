package modele;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
/**
 *Classe représentant le plateau de jeu, avec des cases numérotées et une case vide, ainsi que le compte du nombre de coups, du temps passé, et de l'image de fond.
 * Cette classe hérite de la classe Plateau.
 */
public class PlateauImage extends Plateau implements Serializable{
    private int nbcoups,temps;
    private String image;
    
    /**
     * Constructeur du plateau avec une image de fond
     * @param c Tableau de cases qui sert à la création du plateau
     * @param coups Nombre de coups déjà réalisés sur le plateau
     * @param time Temsp déjà passé sur cette partie
     * @param imageFond Chemin vers l'image servant de fond au plateau
     */
    public PlateauImage(Case[][] c, int coups, int time, String imageFond){
        super(c);
        nbcoups=coups;
        temps=time;
        image=imageFond;
        
    }
    
    /**
     * Méthode qui se charge du déplacement et de la vérification de sa possibilité
     * @param direction Caractère représentant la direction du déplacement
     * @return true Si le déplacement a pu être effectué
     */
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
    
    /**
     * permet de sauvegarder les informations du plateau dans un fichier
     * @param nomSauvegarde nom du fichier contenant les informations
     * @param coups nombre de coups initié par le joueur
     * @param time temps efféctué au moment de la sauvegarde
     * @return retourne true si la sauvegarde à bien été faite
     */
    public boolean sauvegarder(String nomSauvegarde,int coups, int time){
        try {
            PrintWriter out=new PrintWriter(new FileWriter("media/plateau/"+nomSauvegarde));
            
            out.println(image);
            out.println(coups);
            out.println(time);
            out.println(cases.length);
            for(int i=0; i < this.cases.length; i++){
                for(int j=0; j< this.cases[i].length;j++){
                    if(!cases[i][j].estVide()){
                        out.println(((CaseNumerotee)cases[i][j]).getNum());
                    }else{
                        out.println("V");
                    }
                }
            }
            out.flush();
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }finally{
            return true;
        }
    }
        
    
    /**
     * Méthode permettant de charger un fichier de sauvegarde en mode graphique
     * @param nomFich Nom et chemin du fichier de sauvegarde
     * @return Un plateau à l'état dans lequel on l'a laissé
     * @throws IOException Erreur de lecture
     */
    public static PlateauImage charger(String nomFich)throws IOException{

        PlateauImage p=null;
        
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
        
        p=new PlateauImage(cases,coups,temps,imageFond);
        
        return p;
    }
    /**
     * Getter du nombre de coups effectués
     * @return Nombre de coups effectués
     */
    public int getNbcoups() {
        return nbcoups;
    }
    /**
     * Getter du temps passé sur le plateau
     * @return Temps passé sur le plateau
     */
    public int getTemps() {
        return temps;
    }
    /**
     * Getter du chemin de l'image
     * @return Chemin de l'image
     */
    public String getImage() {
        return image;
    }
    
    
    
    
}
