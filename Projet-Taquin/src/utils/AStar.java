/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import taquinconsole.Case;
import taquinconsole.CaseNumerotee;
import taquinconsole.Plateau;

/**
 *
 * @author guillaume
 */
public class AStar implements AlgoIA {

    Set<Plateau> closedSet = new HashSet<Plateau>();
    PriorityQueue<Plateau> openSet = new PriorityQueue<Plateau>(10, new Comparator<Plateau>(){
        @Override
        public int compare(Plateau o1, Plateau o2) {
            if(cout(o1)<cout(o2)){
                return 1;
            }else if(cout(o1)==cout(o2)){
                return 0;
            }else{
                return -1;
            }
        }
        
    });

    Map<Plateau, Plateau> vientDe = new HashMap<Plateau, Plateau>();
    Map<Plateau, Integer> fScore = new HashMap<Plateau, Integer>();
    Map<Plateau, Integer> gScore = new HashMap<Plateau, Integer>();
    private ArrayList<Plateau> chemin;

    /*   private class Node implements Comparable<Node>{
        public Plateau etat;
        public int cout;
        public int heuristique;
        public Node parent;
        public Node(Plateau p, int c, int h,Node n){
            this.etat = p;
            this.cout = c;
            this.heuristique = h;
        }

        @Override
        public int compareTo(Node o) {
            if(this.heuristique < o.heuristique){
                return 1;
            }else if(this.heuristique == o.heuristique){
                return 0;
            }else{
                return -1;
            }
        }

    }*/
    @Override
    public Stack aide(Plateau depart) {
        /*     Node depart = new Node(p,0,0,null);
      List<Node> listeFermee = new LinkedList<Node>();
      PriorityQueue<Node> listeOuverte = new PriorityQueue<Node>();
      listeOuverte.add(depart);
      while(!listeOuverte.isEmpty()){
          Node u = listeOuverte.poll();
          if(u.etat.verifierVictoire()){
              //Plateau final
              Stack<Plateau> res = new Stack<Plateau>();
              res.addAll(chemin);
              return res;
              
          }
          listeOuverte.remove(u);
          listeFermee.add(u);
          
          //Création des noeuds voisins
          Plateau[] etatsPossibles = u.etat.configurationsSuivantesPossibles();
          Node[] noeudsPossibles = new Node[4];
          for(int i=0; i<4;i++){
              Node no = new Node(etatsPossibles[i],u.cout+1,u.heuristique+1+cout(etatsPossibles[i]),u);
              noeudsPossibles[i] = no;
          }
          
          //Evaluation des noeuds
          for(Node n : noeudsPossibles){
              if(listeOuverte.contains(n) ||listeFermee.contains(n)){
                  continue;
              }else{
                  n.cout = u.cout+1;
                  n.heuristique = n.cout+cout(u.etat);
                  listeOuverte.add(n);
                  System.out.println(n.etat);
              }
              listeFermee.add(u);
          }
          
      }
      System.err.println("Plateau impossible");
      return null; */
        fScore.put(depart, cout(depart));
        gScore.put(depart,0);
        openSet.add (depart);
        while (!openSet.isEmpty()) {
          /*  //On récupère le plateau avec le score min
            Entry<Plateau, Integer> courant = null;
            for (Entry<Plateau, Integer> entry : fScore.entrySet()) {
                if (courant == null || courant.getValue() > entry.getValue()) {
                    courant = entry;
                }
            }
            
            Plateau etatCourant = courant.getKey();*/
          Plateau etatCourant = openSet.peek();
            if (etatCourant.verifierVictoire()) {
                //DANS CE CAS, CHEMIN TROUVé
                
                List cheminSolution = reconstituerChemin(etatCourant);
                Stack<Plateau>  res = new Stack<Plateau>();
                res.addAll(cheminSolution);
                return res;

            }
            openSet.remove(etatCourant);
            closedSet.add(etatCourant);
            
            
            
            Plateau[] configsPossibles = etatCourant.configurationsSuivantesPossibles();
            for (Plateau i : configsPossibles) {
               // System.out.println(i);
                if (closedSet.contains(i)) {
                    continue;
                }
                int temp_score = ((gScore.get(etatCourant) != null) ? gScore.get(etatCourant)+1 : Integer.MAX_VALUE) ;
                if (!openSet.contains(i)) {
                    openSet.add(i);
                } else if (temp_score >= gScore.get(i)) {
                    continue;
                }

                vientDe.put(i, etatCourant);
                gScore.put(i, temp_score);
                fScore.put(i, 1 + cout(i));
            }//Fin for noeuds voisins
        }//Fin while
        Stack sc = new Stack();
        sc.add(depart);
        return sc; //Cas où on n'a auxune solution
    }

    /**
     * Calcule le nombre de cases mal placées dans un noeud
     *
     * @param grille Grille de l'état courant
     * @return nombre de cases mal placées
     */
    public int cout(Plateau grille) {
        int res = 0;
        Case[][] cases = grille.getCases();
        for (int i = 0; i < cases.length; i++) {
            for (int j = 0; j < cases[i].length; j++) {
                Case courante = cases[i][j];
                if (courante instanceof CaseNumerotee) {
                    int diff = ((CaseNumerotee) courante).getNum() - (cases.length * i + j);
                    if (diff != 0) {
                        res++;
                    }
                }
            }
        }

        return res;
    }

    private List reconstituerChemin( Plateau courant) {
        List<Plateau> cheminTotal = new ArrayList<Plateau>();
        cheminTotal.add(courant);
        while (vientDe.containsKey(courant)) {
            courant = vientDe.get(courant);
            cheminTotal.add(courant);
        }
        return cheminTotal;

    }
;

}
