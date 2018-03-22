package reversi;

import reversi.algo.Algo;
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

    public Reversi jeuIA(Reversi r, int profondeur) {
        int max = -1000;
        Algo algo = new Algo();
        Reversi meilleurcoup = r;
        int val;
        r.nettoyer();
        r.algo();
        Iterator<Reversi> i = r.successeur();
        Reversi etat;
        while (i.hasNext()) {
            etat = i.next();
            val = algo.min(etat, profondeur, 0, 0);
            if (val > max) {
                max = val;
                meilleurcoup = etat;
            }
        }
        meilleurcoup.setJoueur(joueurBlanc);
        return meilleurcoup;
    }

    public void jouer(){
        Scanner saisie = new Scanner(System.in);
        boolean continuer = true;
        System.out.println("Début de la partie, veuillez rentrer les coordonnés selon l'axe commençant en haut à gauche");
        r.aff_tableau();
        while(continuer == true) {
            if (r.getJoueur().getAdversaire() == BLANC) {
                System.out.println("Tour de l'adversaire:");
                long millis = System.currentTimeMillis();
                r = jeuIA(r, 5);
                long end = System.currentTimeMillis();
                millis = end - millis;
                r.aff_tableau();
                System.out.println("TEMPS = " + " " + millis);
                System.out.println("L'adversaire a joué en (" +  r.getX() + " ; " +  r.getY() + ")");
            } else {
                System.out.println("Votre tour :");
                System.out.println("x ? ");
                int x = saisie.nextInt();
                System.out.println("y ? ");
                int y = saisie.nextInt();
                r.algo();
                Iterator i = r.successeur();
                while (i.hasNext()) {
                    Reversi e = (Reversi) i.next();
                    if (e.getX() == x && e.getY() == y) {
                        r = e;
                        r.setJoueur(joueurNoir);
                    }
                }
                System.out.println("votre coup");
                r.aff_tableau();
            }
        }
    }
}
