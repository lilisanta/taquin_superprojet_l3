/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import static java.lang.Math.abs;
import java.util.Stack;
import taquinconsole.Case;
import taquinconsole.CaseNumerotee;
import taquinconsole.Plateau;

/**
 *
 * @author guillaume
 */
public class IDAStar implements AlgoIA{
    
    private Stack chemin;
    
    
    @Override
    public Stack aide(Plateau p) {
       int lim = 0;
       double t;
       chemin = new Stack();
        t = recherche(p,0,Double.MAX_VALUE);
        if(t!=0){
            return chemin;
        }else{
            return null;
        }

    }
    /**
     * Calcule le nombre de cases mal placées dans un noeud
     * @param grille Grille de l'état courant
     * @return nombre de cases mal placées
     */
    public int cout(Plateau grille){
        int res = 0;
        Case[][] cases = grille.getCases();
        for(int i = 0; i < cases.length; i++){
            for(int j =0; j < cases[i].length; i++){
                Case courante = cases[i][j];
                if(courante instanceof CaseNumerotee){
                    int diff = ((CaseNumerotee) courante).getNum() - (cases.length*i+j);
                    if(diff!=0){
                        res++;
                    }
                }
            }
        }
        
        
        return res;
    }
    //TODO
    /**
     * 
     * @param noeud
     * @param coutEstimePlusCourt
     * @param limite
     * @return 
     */
    public double recherche(Plateau noeud,int coutEstimePlusCourt, double limite ){
        
        
        return -1.0;
    }
    
}
