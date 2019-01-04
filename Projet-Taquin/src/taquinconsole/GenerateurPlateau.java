package taquinconsole;

import modele.CaseVide;
import modele.Case;
import modele.CaseNumerotee;
import modele.Plateau;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Cette classe sert à la génération d'un plateau en fonction d'une taille
 * donnée, ou d'une taille et d'une image
 */
public class GenerateurPlateau {

    /**
     * Méthode qui génère un plateau pour l'utilisation du jeu de taquin en mode
     * console Elle gère égalemant le cas de l'infaisabilité du plateau
     *
     * @param taille largeur et hauteur du plateau, doit être supérieure à 0
     * @return Plateau nouvellement généré, null si la taille donnée est
     * incorrecte
     */
    public static Plateau genererPlateauConsole(int taille) {

        Plateau p = null; //Plateau résultat
        int comptePermutations = 0; // Si pair, alors plateau valide
        boolean plateauValide = false; // Pour savoir si on doit recréer un plateau
        int[] valeurs = new int[taille * taille]; // Valeurs à mélanger
        int[] valeursPourTrier = new int[taille * taille]; ; // Valeurs pour la création des cases
        valeurs[taille-1] = 0;
        if (taille > 2) {
            while (!plateauValide) {
                for (int i = 0; i < (taille * taille)-1; i++) {
                    valeurs[i] = i+1;
                }

                //On mélange le tableau
                Random rnd = new Random();
               
                for (int i = valeurs.length - 2; i > 0; i--) {
                    int index = rnd.nextInt(i + 1);
                    int tmp = valeurs[index];
                    valeurs[index] = valeurs[i];
                    valeurs[i] = tmp;
                }
                //******
                System.out.println();
                for (int i : valeurs) {
                    valeursPourTrier[i] = valeurs[i];
                    //System.out.print(i + "|");
                }
                
                //*****
                for (int i = 0; i < valeursPourTrier.length - 1; i++) {
                    int min = i;
                    for (int j = i + 1; j < valeursPourTrier.length; j++) {
                        if (valeursPourTrier[j] < valeursPourTrier[min]) {
                            min = j;
                        }
                    }
                    if (min != i) {
                        int tmp = valeursPourTrier[i];
                        valeursPourTrier[i] = valeursPourTrier[min];
                        valeursPourTrier[min] = tmp;
                        comptePermutations++;
                    }
                }
                if (comptePermutations % 2 == 0) {
                    plateauValide = true;
                }
                comptePermutations = 0;
            }

            //On a une liste d'entiers valide;
            int[][] valeursDesCases = new int[taille][taille];

            int k = 0; // Indice d'itération pour les valeurs précedentes
            for (int i = 0; i < taille; i++) {
                for (int j = 0; j < taille; j++) {
                    valeursDesCases[i][j] = valeurs[k];
                    k++;
                }
            }
            Case[][] casesRes = new Case[taille][taille];
            for (int i = 0; i < taille; i++) {
                for (int j = 0; j < taille; j++) {
                    if (valeursDesCases[i][j] != 0) {
                        casesRes[i][j] = new CaseNumerotee( i, j,valeursDesCases[i][j]);
                    } else {
                        casesRes[i][j] = new CaseVide(i, j);
                    }

                }
            }

            p = new Plateau(casesRes);
        } else {
            System.out.println("Plateau trop petit");
        }
        return p;
    }

    /**
     * cette méthode permet de récupérer un plateau depuis un fichier
     *
     * @param fileName chemin du fichier
     * @return Plateau représenté dans le fichier
     * @throws IOException Dans le cas où le fichier spécifié n'existe pas
     */
    public static Plateau chargerPlateau(String fileName) throws IOException {
        Plateau p = null;
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        int taille = Integer.parseInt(in.readLine());
        Case[][] cases = new Case[taille][taille];
        String num;
        for (int i = 0; i < taille; i++) {        // i numéro de ligne et y numéro de colonne
            for (int y = 0; y < taille; y++) {
                num = in.readLine();
                if (num.equals("V")) {
                    cases[i][y] = new CaseVide(i, y);
                } else {
                    cases[i][y] = new CaseNumerotee(i, y, Integer.parseInt(num));
                }

                if (i == taille) {
                    y = 0;
                    i++;
                }
            }
        }
        p = new Plateau(cases);

        return p;
    }
}
