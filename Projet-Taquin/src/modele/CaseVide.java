package modele;


/**
 *Classe représentant une case vide
 */
public class CaseVide extends Case{
    public CaseVide(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public CaseVide(Case c){
        this.x = c.getX();
        this.y = c.getY();
    }
    @Override
    public String toString(){
        return "| .|";
    }

    @Override
    public boolean estVide() {
       return true;
    }
}
