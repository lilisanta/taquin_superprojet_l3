
package taquinconsole;

import modele.Plateau;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Aide;


public class TaquinConsole {

    /**
     * @param args arguments de la ligne de commande, inutilisés ici
     */
    public static void main(String[] args)  {
       Plateau p = GenerateurPlateau.genererPlateauConsole(4);
      // p = GenerateurPlateau.chargerPlateau("taquin1");
       System.out.println(" Entrez :\n 'h' pour déplcer vers le haut\n"
               +" 'b'pour déplacer vers le haut \n"
               + " 'g' pour déplacer vers la gauche\n"
               +" 'd' pour déplacer vers la droite \n"
               +" 's' pour sauvegarder la partie \n");
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
               System.out.println("Saisissez le nom de votre sauvegarde");
               p.sauvegarder(in.nextLine());
               System.out.println("Sauvegarde réussie");
           }else if(commande == 'c'){
               System.out.println("Saisissez le nom de votre sauvegarde");
               try {
                   p = Plateau.charger(in.nextLine());
               } catch (IOException ex) {
                   System.out.println("Impossible de charger cette sauvegarde. Elle n'existe peut être pas");
               }
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
