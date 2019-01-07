/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Stack;
import modele.Plateau;

/**
 *Interface servatn à l'implémentation du patron strategy, et qui permet de définir le comportement des algorithmes de résolution du jeu.
 */
public interface AlgoIA {
    /**
     * méthode donnant la combinaison optimale de mouvements pour terminer la partie
     * @param p Plateau à l'état courant
     * @return Pile d'états successifs à obtenir pour terminer la partie en un nombre minimum de coups
     */
    public Stack aide(Plateau p);
}
