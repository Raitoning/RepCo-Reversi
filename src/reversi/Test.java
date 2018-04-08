package reversi;

import reversi.etats.Reversi;
import reversi.joueurs.JoueurBlanc;
import reversi.joueurs.JoueurNoir;
import reversi.joueurs.TypeJoueur;

import java.util.Scanner;

public class Test {
    public static void testTableau(){
        int hauteur;
        int largeur;
        Scanner saisie = new Scanner(System.in);
        JoueurBlanc joueur = new JoueurBlanc(TypeJoueur.Humain);
        JoueurNoir joueur2 = new JoueurNoir(TypeJoueur.Humain);
        System.out.println("Taille du plateau : ");
        System.out.println("Hauteur ?");
        hauteur = saisie.nextInt();
        System.out.println("Largeur ?");
        largeur = saisie.nextInt();
        Partie p1 = new Partie(joueur, joueur2, hauteur, largeur);
        System.out.println("Choisissez votre mode de jeu ");
        System.out.println("1.Joueur vs Joueur | 2.Joueur vs IA | 3.IA vs IA");
        System.out.println("Tapez 1, 2 ou 3 ");
        int x = saisie.nextInt();
        if (x == 1) {
            p1.joueurvsjoueur();
        }else if (x == 2){
            p1.joueurVSIA();
        }else if (x == 3){
            System.out.println(p1.iaVSia(0, 1));
        }
    }

    public static void main(String[] args){
        testTableau();
    }

}
