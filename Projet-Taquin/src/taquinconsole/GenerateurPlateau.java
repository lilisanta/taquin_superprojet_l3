
package taquinconsole;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *Cette classe sert à la génération d'un plateau en fonction d'une taille donnée, ou d'une taille et d'une image
 */
public class GenerateurPlateau {
    
    /**
     * Méthode qui génère un plateau pour l'utilisation du jeu de taquin en mode console
     * Elle gère égalemant le cas de l'infaisabilité du plateau
     * @param taille largeur et hauteur du plateau, doit être supérieure à 0
     * @return Plateau nouvellement généré, null si la taille donnée est incorrecte
     */
 /* TODO Finir la méthode lorsque l'on pourra résoudre le taquin via une IA (nécessaire pour vérifier la validité du plateau) 
    
    
    public static Plateau genererPlateauConsole(int taille) throws Exception{
        Plateau p = null;
        int[] valeurs = new int[taille*taille+10];
        valeurs[0] = 0;
        if(taille>2){
            for(int i=1; i< taille*taille;i++){
                valeurs[i]=i;
                System.out.print(i);
            }
            List valeursPlateau  =Array.asList(valeurs);
            
            for(int i=0; i< taille*taille; i++){
                
            }
            
            
            
        }else{
            throw new Exception("Plateau trop petit");
        }
        return p;
    }*/
    /**
     * cette méthode permet de récupérer un plateau depuis un fichier
     * @param fileName chemin du fichier
     * @return Plateau représenté dans le fichier
     * @throws IOException Dans le cas où le fichier spécifié n'existe pas
     */
    public static Plateau chargerPlateau(String fileName) throws IOException{
        Plateau p=null;
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        int taille = Integer.parseInt(in.readLine());
        Case[][] cases = new Case[taille][taille];
        String num;
        for(int i=0; i< taille;i++){        // i numéro de ligne et y numéro de colonne
            for(int y=0; y < taille;y++){
            num = in.readLine();
            if(num.equals("V")){
                cases[i][y] = new CaseVide(i,y);
            }else{
                 cases[i][y] = new CaseNumerotee(i,y,Integer.parseInt(num));
            }
            
            if(i == taille){
                    y=0;
                    i++;
                }
        }
        }
        p = new Plateau(cases);
        
        
        
        
        return p;
    }
}
