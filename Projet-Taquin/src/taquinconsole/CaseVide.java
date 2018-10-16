package taquinconsole;


/**
 *Classe représentant une case vide
 */
public class CaseVide extends Case{
    public CaseVide(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    
    @Override
    public String toString(){
        return "| .|";
    }
}
