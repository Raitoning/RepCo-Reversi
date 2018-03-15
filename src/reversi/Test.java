package reversi;

import reversi.etats.Reversi;
import reversi.joueurs.Joueur;
import reversi.joueurs.JoueurBlanc;
import reversi.joueurs.JoueurNoir;
import reversi.joueurs.TypeJoueur;

import java.util.Iterator;

public class Test {
    public static void testTableau(){
        Joueur joueur = new JoueurBlanc(TypeJoueur.Humain);
        Joueur joueur2 = new JoueurNoir(TypeJoueur.Humain);
        Reversi r = new Reversi(joueur);

      r.aff_tableau();
      System.out.println("-------------");
      r.algo();
      Iterator i = r.successeurBlanc();
      while (i.hasNext()){
          Reversi e = (Reversi)i.next();
         e.aff_tableau();
      }
    }

    public static void main(String[] args){
        testTableau();
    }

}
