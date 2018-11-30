/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import taquinconsole.Case;
import taquinconsole.CaseNumerotee;
import taquinconsole.Plateau;
import static java.lang.Math.abs;
/**
 *
 * @author guillaume
 */
public class Heuristique {
    private Plateau grille;
    
    public Heuristique(Plateau g){
        this.grille = g;
    }
    
    public int cout(){
        int res = 0;
        Case[][] cases = grille.getCases();
        for(int i = 0; i < cases.length; i++){
            for(int j =0; j < cases[i].length; i++){
                Case courante = cases[i][j];
                if(courante instanceof CaseNumerotee){
                    int diff = ((CaseNumerotee) courante).getNum() - (3*i+j);
                    if(diff!=0){
                        res+=(abs(diff-((CaseNumerotee)courante).getNum()));
                    }
                }
            }
        }
        
        
        return res;
    }
}
