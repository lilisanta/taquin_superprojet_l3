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

    @Override
    public Stack aide() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int cout(Plateau grille){
        int res = 0;
        Case[][] cases = grille.getCases();
        for(int i = 0; i < cases.length; i++){
            for(int j =0; j < cases[i].length; i++){
                Case courante = cases[i][j];
                if(courante instanceof CaseNumerotee){
                    int diff = ((CaseNumerotee) courante).getNum() - (3*i+j);
                    if(diff!=0){
                        res++;
                    }
                }
            }
        }
        
        
        return res;
    }
    
}
