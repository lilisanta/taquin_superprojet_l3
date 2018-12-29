/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Stack;
import taquinconsole.Plateau;

/**
 *
 * @author guillaume
 */
public class Aide {
    private AlgoIA algo;
    
    
    public Aide(String typeAlgo){
        if(typeAlgo.equals("A*")){
            
        }else if(typeAlgo.equals("IDA*")){
            this.algo = new IDAStar();
        }
    }
    
    public Plateau aide(Plateau p){
        return (Plateau)(this.algo.aide(p).pop());
    }
}
