package reversi;

import reversi.etats.Reversi;
import reversi.joueurs.Joueur;
import reversi.joueurs.JoueurBlanc;
import reversi.joueurs.JoueurNoir;
import reversi.joueurs.TypeJoueur;

import java.util.Iterator;

public class Test {
    public static void testTableau(){
        JoueurBlanc joueur = new JoueurBlanc(TypeJoueur.Humain);
        JoueurNoir joueur2 = new JoueurNoir(TypeJoueur.Humain);
         Reversi r = new Reversi(joueur);
          Partie p1 = new Partie(joueur, joueur2);
          p1.jouer();
        /** r.aff_tableau();
         System.out.println("-------------");
         r.algo();
         Iterator i = r.successeurBlanc();
         while (i.hasNext()){
         Reversi e = (Reversi)i.next();
         e.aff_tableau();
         }**/
    }

    public static void main(String[] args){
        testTableau();
    }

}
