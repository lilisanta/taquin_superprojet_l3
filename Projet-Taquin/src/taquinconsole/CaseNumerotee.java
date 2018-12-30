package taquinconsole;

/**
 *Cette classe représente une classe portant un numéro (non vide)
 */
public class CaseNumerotee extends Case{
    private int numero;
    /**
     * Constructeur de la case numérotée
     * @param x Position en abscisses de la case
     * @param y Position en ordonnée de la case
     * @param n Numéro de la case
     */
    public CaseNumerotee(int x,int y,int n){
        this.x =x;
        this.y = y;
        this.numero=n;
    }
    
    public CaseNumerotee(Case c){
        this.x = c.getX();
        this.y = c.getY();
        this.numero = ((CaseNumerotee)c).getNum();
    }
    
    public int getNum(){
        return this.numero;
    }
    @Override
    public String toString(){
        if(this.numero>=10){
            return "|"+this.numero+"|";
        }else{
            return "| "+this.numero+"|";
        }
        
    }
}
