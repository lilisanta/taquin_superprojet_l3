package modele;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
/**
 *Classe représentant le plateau de jeu, avec des cases numérotées et une case vide
 */
public class PlateauConsole implements Serializable, Cloneable{
    protected Case[][] cases;
    protected Case caseVide;
    protected char dernierDepalcement = 0;
    /**
     * Constructeur du plateau
     * @param c Tableau de cases qui sert à la création du plateau
     */
    public PlateauConsole(Case[][] c){
        if(c == null) try {
            throw new Exception("Tableau de cases null");
        } catch (Exception ex) {
            Logger.getLogger(PlateauConsole.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.cases = c;
        for(int i=0; i < cases.length; i++){
            for(int j=0; j < cases[i].length;j++){
                if(cases[i][j] instanceof CaseVide){
                    this.caseVide = this.cases[i][j];
                }
            }
        }
    }
    
    public PlateauConsole(PlateauConsole p ){
        int taille = p.getCases().length;
        this.cases = new Case[taille][taille];
        Case[][] c2 = p.getCases();
        for(int i = 0; i< c2.length;i++){
            for(int j = 0; j< c2[i].length;j++){
               // this.cases[i][j] = c2[i][j];
               if(c2[i][j] instanceof CaseNumerotee)
                this.cases[i][j] = new CaseNumerotee(c2[i][j]);
               else
                   this.cases[i][j] = new CaseVide(c2[i][j]);
               this.caseVide = this.cases[p.getCaseVide().getX()][p.getCaseVide().getY()];
            }
        }
        
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
                   this.dernierDepalcement = direction;
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
                   this.dernierDepalcement = direction;
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
                   this.dernierDepalcement = direction;
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
                   this.dernierDepalcement = direction;
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
            for(int j=0; j< this.cases.length;j++){
                if(cases[i][j] instanceof CaseNumerotee){
                    if(!(((CaseNumerotee)cases[i][j]).getNum() == compte)){ //vérification de l'ordre ddes numéros
                        return false;
                    }
                }
                else{
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
    
    public boolean sauvegarder(String filename){
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(new File(filename)));
            oos.writeObject(this);
            oos.close();
        }catch(IOException e){
            e.printStackTrace();
            return false;
        }finally{
            return true;
        }
        
    }
    
    
    public static PlateauConsole charger(String nomFich)throws IOException{
        PlateauConsole p = null;
        Object o;
        ObjectInputStream in = new ObjectInputStream((new FileInputStream(nomFich)));
        try {
            o =  in.readObject();
            p = (PlateauConsole) o;
            in.close();
        } catch (ClassNotFoundException ex) {
            System.err.println("La partie n'a pas pu être chargée");
//Logger.getLogger(Plateau.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return p;
    }

    public Case[][] getCases() {
        return cases;
    }

    public void setCases(Case[][] cases) {
        this.cases = cases;
    }

    public void setCaseVide(Case caseVide) {
        this.caseVide = caseVide;
    }

    public Case getCaseVide() {
        return caseVide;
    }
    
    public PlateauConsole[] configurationsSuivantesPossibles(){
        PlateauConsole[] res= new PlateauConsole[4];
        //Clonage de plateau;
        PlateauConsole pHaut = new PlateauConsole(this);
        PlateauConsole pBas = new PlateauConsole(this);
        PlateauConsole pGauche = new PlateauConsole(this);
        PlateauConsole pDroite = new PlateauConsole(this);
        pHaut.deplacement('h');
        pBas.deplacement('b');
        pGauche.deplacement('g');
        pDroite.deplacement('d');
        res[0] = pHaut;
        res[1] = pBas;
        res[2] = pGauche;
        res[3] = pDroite;
        return res;
        
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Arrays.deepHashCode(this.cases);
        hash = 19 * hash + Objects.hashCode(this.caseVide);
        hash = 19 * hash + this.dernierDepalcement;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PlateauConsole other = (PlateauConsole) obj;
        if (this.dernierDepalcement != other.dernierDepalcement) {
            return false;
        }
        if (!Arrays.deepEquals(this.cases, other.cases)) {
            return false;
        }
        if (!Objects.equals(this.caseVide, other.caseVide)) {
            return false;
        }
        return true;
    }
    
}