package taquinconsole;

import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *Classe abstraite représentant une case
 */
public abstract class Case implements Serializable,Cloneable{
    protected int x,y;

   public int getX() {
        return this.x;
    }

   public int getY() {
        return this.y;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Case other = (Case) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
   
   
   
   
}

