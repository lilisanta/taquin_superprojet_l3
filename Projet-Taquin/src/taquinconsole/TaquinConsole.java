
package taquinconsole;

import java.io.IOException;
import java.util.Scanner;
import utils.Aide;


public class TaquinConsole {

    /**
     * @param args arguments de la ligne de commande, inutilisés ici
     */
    public static void main(String[] args) throws IOException, Exception {
       Plateau p = GenerateurPlateau.genererPlateauConsole(3);
       System.out.println(" Entrez :\n 'h' pour déplcer vers le haut\n"
               +" 'b'pour déplacer vers le haut \n"
               + " 'g' pour déplacer vers la gauche\n"
               +" 'd' pour déplacer vers la droite \n"
               +" 's' pour sauvegarder la partie (toute sauvegarde écrasera l'ancienne)");
       Scanner in = new Scanner(System.in);
       char commande=0;
        
       while(!p.verifierVictoire()){
           System.out.println(p);
           String tmp;
           do{
           tmp = (in.nextLine());
           }while(tmp.equals(""));
           commande = tmp.charAt(0);
           if(commande == 's'){
               p.sauvegarder("Sauvegarde");
               System.out.println("Sauvegarde réussie");
           }else if(commande == 'c'){
               p = Plateau.charger("Sauvegarde");
               System.out.println("Chargement réussi");
           
           }else if(commande == 'a'){
               p = new Aide("A*").aide(p);
           }else{
                p.deplacement(commande);
           }
          
           
       }
       System.out.println("BRAVO !!! VOUS AVEZ GAGNE");
       
    }
    
}
