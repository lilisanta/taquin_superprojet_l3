
package taquinconsole;

import java.io.IOException;
import java.util.Scanner;


public class TaquinConsole {

    /**
     * @param args arguments de la ligne de commande, inutilisés ici
     */
    public static void main(String[] args) throws IOException {
       Case[][] tab = new Case[4][4];
       for(int i=0; i<4; i++){
           for(int j=0; j<4;j++){
               if(i==2 && j==2){
                   tab[i][j] = new CaseVide(i,j);
               }else{
                   tab[i][j] = new CaseNumerotee(i,j,1);
               }
           }
       }
       Plateau p = GenerateurPlateau.chargerPlateau("taquin1");
       System.out.println(" Entrez : 'h' pour déplcer vers le haut"
               +" 'b'pour déplacer vers le haut "
               + "'g' pour déplacer vers la gauche"
               +" 'd' pour déplacer vers la droite ");
       Scanner in = new Scanner(System.in);
       char commande=0;
        
       while(!p.verifierVictoire()){
           System.out.println(p);
           commande = (char)in.nextLine().charAt(0);
           if(commande == 's'){
               p.sauvegarder();
               System.out.println("Sauvegarde réussie");
           }else if(commande == 'c'){
               p = Plateau.charger(in.nextLine());
           
           }else{
                p.deplacement(commande);
           }
          
           
       }
       System.out.println("BRAVO !!! VOUS AVEZ GAGNE");
       
    }
    
}
