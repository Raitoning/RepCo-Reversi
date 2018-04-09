package reversi;

import reversi.algo.Algo;
import reversi.etats.Reversi;
import reversi.joueurs.JoueurBlanc;
import reversi.joueurs.JoueurNoir;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class Partie {
    private Reversi r;
    private JoueurBlanc joueurBlanc;
    private JoueurNoir joueurNoir;
    private int gagnant;
    private boolean arretBlanc;
    private boolean arretNoir;
    protected static int BLANC = 2;
    protected static int NOIR = 1;
    protected int largeur;
    protected int hauteur;

    public Partie(JoueurBlanc j1, JoueurNoir j2, int hauteur, int largeur){
        r = new Reversi(j2, hauteur, largeur);
        this.hauteur = hauteur;
        this.largeur = largeur;
        joueurBlanc = j1;
        joueurNoir = j2;
        gagnant = 0;
        arretBlanc = false;
        arretNoir = false;
    }

    public Reversi jeuIA(Reversi r, int profondeur, int eval) {
        int max = Integer.MIN_VALUE;
        Algo algo = new Algo();
        Reversi meilleurcoup = r;
        int val = 0;
        r.nettoyer();
        r.algo();
        gagnant();
        Iterator<Reversi> i = r.successeur();
        Reversi etat;
        while (i.hasNext()) {
            etat = i.next();
           val = algo.max(etat, profondeur, Integer.MIN_VALUE, Integer.MAX_VALUE, eval);
            if (val > max) {
                max = val;
                meilleurcoup = etat;
            }
        }
        return meilleurcoup;
    }

    public int getHauteur(){
        return hauteur;
    }

    public int getLargeur(){
        return largeur;
    }

    public void gagnant(){
        int pionBlanc;
        int pionNoir;
        if (r.successeursize() == 0 && r.getJoueur().getColor() == NOIR) {
            arretNoir = true;
        }else if (r.successeursize() > 0 && r.getJoueur().getColor() == NOIR){
            arretNoir = false;
        }
        if (r.successeursize() == 0 && r.getJoueur().getColor() == BLANC) {
            arretBlanc = true;
        }else if (r.successeursize() > 0 && r.getJoueur().getColor() == BLANC){
            arretBlanc = false;
        }
        if (r.compterVide() == 0){
            arretBlanc = true;
            arretNoir = true;
        }
        if (arretBlanc && arretNoir) {
            pionNoir = r.compterNoir(r.getPlateau());
            pionBlanc = r.compterBlanc(r.getPlateau());
            if (pionBlanc > pionNoir) {
                gagnant = 2;
            } else if (pionNoir > pionBlanc) {
                gagnant = 1;
            }else if (pionBlanc == pionNoir){
                gagnant = 3;
            }
        }
    }

    public void joueurVSIA(){
        Scanner saisie = new Scanner(System.in);
        boolean continuer = true;
        System.out.println("Début de la partie, veuillez rentrer les coordonnés selon l'axe commençant en haut à gauche");
        r.aff_tableau();
        while(continuer) {
            if (r.getJoueur().getColor() == BLANC) {
                System.out.println("Tour de l'adversaire:");
                r = jeuIA(r, 3, 1);
                r.aff_tableau();
                System.out.println("L'adversaire a joué en (" +  r.getX() + " ; " +  r.getY() + ")");
                r.setJoueur(joueurNoir);
            } else {
                int x;
                int y;
                System.out.println("Votre tour :");
                System.out.println("x ? ");
                try{
                    x = saisie.nextInt();
                } catch(InputMismatchException nfe){
                    throw new InputMismatchException("Veuillez rentrer une valeur entière");
                }
                System.out.println("y ? ");
                try{
                    y = saisie.nextInt();
                } catch(InputMismatchException nfe){
                    throw new InputMismatchException("Veuillez rentrer une valeur entière");
                }
                r.algo();
                Iterator i = r.successeur();
                while (i.hasNext()) {
                    Reversi e = (Reversi) i.next();
                    if (e.getX() == x && e.getY() == y) {
                        r = e;
                        r.setJoueur(joueurBlanc);
                    }
                }
                System.out.println("votre coup");
                r.aff_tableau();
            }
        }
    }

    public void joueurvsjoueur(){
        Scanner saisie = new Scanner(System.in);
        boolean continuer = true;
        while(continuer == true){
            r.aff_tableau();
            System.out.println("Tour du joueur : " + r.getJoueur().getColor());
            System.out.println("x ? ");
            int x;
            int y;
            try{
                x = saisie.nextInt();
            } catch(InputMismatchException nfe){
                throw new InputMismatchException("Veuillez rentrer une valeur entière");
            }
            System.out.println("y ? ");
            try{
                 y = saisie.nextInt();
            } catch(InputMismatchException nfe){
                throw new InputMismatchException("Veuillez rentrer une valeur entière");
            }
            r.algo();
            Iterator i = r.successeur();
            while (i.hasNext()) {
                Reversi e = (Reversi) i.next();
                if (e.getX() == x && e.getY() == y) {
                    if (r.getJoueur().getAdversaire() == BLANC) {
                        r = e;
                        r.setJoueur(joueurBlanc);
                    } else if (r.getJoueur().getAdversaire() == NOIR) {
                        r = e;
                        r.setJoueur(joueurNoir);
                    }
                }
            }
        }
    }

    public int iaVSia(int eval0a, int eval0b){
        r.aff_tableau();
        while(gagnant == 0) {
            if (r.getJoueur().getColor() == NOIR) {
                System.out.println("Tour du joueur noir:");
                r = jeuIA(r, 3, eval0a);
                r.aff_tableau();
                r.setJoueur(joueurBlanc);
                System.out.println("Le joueur noir a joué en (" +  r.getX() + " ; " +  r.getY() + ")");
            } else if (r.getJoueur().getColor() == BLANC){
                System.out.println("Tour du joueur blanc:");
                r = jeuIA(r, 3, eval0b);
                r.aff_tableau();
                r.setJoueur(joueurNoir);
                System.out.println("Le joueur blanc a joué en (" +  r.getX() + " ; " +  r.getY() + ")");
            }
        }
        r.aff_tableau();
        if (gagnant == 2){
            return -1;
        }else if (gagnant == 1){
            return 1;
        }else {
            return 0;
        }
    }
}
