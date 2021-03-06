package reversi;

import reversi.etats.Reversi;
import reversi.joueurs.Joueur;
import reversi.joueurs.JoueurBlanc;
import reversi.joueurs.JoueurNoir;
import java.util.Iterator;
import java.util.Scanner;

public class Partie {
    private Reversi r;
    private JoueurBlanc joueurBlanc;
    private JoueurNoir joueurNoir;
    protected static int BLANC = 2;
    protected static int NOIR = 1;

    public Partie(JoueurBlanc j1, JoueurNoir j2){
        r = new Reversi(j1);
        joueurBlanc = j1;
        joueurNoir = j2;
    }

    public void jouer(){
        Scanner saisie = new Scanner(System.in);
        boolean continuer = true;
        while(continuer == true){
            r.aff_tableau();
            System.out.println("Joueur : " + r.getJoueur().getColor());
            System.out.println("x ? ");
            int x = saisie.nextInt();
            System.out.println("y ? ");
            int y = saisie.nextInt();
            r.algo();
            Iterator i = r.successeur();
            while (i.hasNext()) {
                Reversi e = (Reversi) i.next();
                if (e.getX() == x && e.getY() == y) {
                    if (r.getJoueur().getAdversaire() == BLANC) {
                        if (r.successeursize() == 0){
                            continuer = false;
                            System.out.println("fin de partie");
                        }
                        r = new Reversi(joueurBlanc, e.getPlateau(), true);
                        r.nettoyer();
                    } else if (r.getJoueur().getAdversaire() == NOIR) {
                        r = new Reversi(joueurNoir, e.getPlateau(), true);
                        r.nettoyer();
                    }
                }
            }
        }
    }
}
