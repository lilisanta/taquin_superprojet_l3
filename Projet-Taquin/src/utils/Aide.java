/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Stack;
import modele.Plateau;

/**
 *Classe Helper qui permet d'obtenir l'ensemble des coups à réaliser poru terminer le plateau
 */
public class Aide {
    private AlgoIA algo;
    
    /**
     * Constructeur de la classe Aide
     * @param typeAlgo Type de l'algorithme à utiliser (A* ou IDA*) 
     */
    public Aide(String typeAlgo){
        if(typeAlgo.equals("A*")){
            this.algo = new AStar();
        }else if(typeAlgo.equals("IDA*")){
            this.algo = new IDAStar();
        }
    }
    /**
     * Retourne le plateau du prochain coup à réaliser pour terminer le jeu
     * @param p Plateau actuel
     * @return  Meilleur pateau possible depuis la position actuelle
     */
    public Plateau aide(Plateau p){
        return (Plateau)(this.algo.aide(p).pop());
    }
}
