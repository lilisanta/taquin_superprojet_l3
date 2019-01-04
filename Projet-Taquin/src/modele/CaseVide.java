package modele;


/**
 *Classe représentant une case vide
 */
public class CaseVide extends Case{
    /**
     * Constructeur de la case vide
     * @param x abscisse de la case vide
     * @param y ordonnée de la case vide
     */
    public CaseVide(int x, int y){
        this.x = x;
        this.y = y;
    }
    /**
     * Constructeur par copei de la case vide
     * @param c Case à copier
     */
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
