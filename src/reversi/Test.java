package reversi;

import reversi.etats.Reversi;
import reversi.joueurs.JoueurBlanc;
import reversi.joueurs.JoueurNoir;
import reversi.joueurs.TypeJoueur;

import java.util.InputMismatchException;
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
        try{
            hauteur = saisie.nextInt();
        } catch(InputMismatchException nfe){
            throw new InputMismatchException("Veuillez rentrer une valeur entière");
        }
        System.out.println("Largeur ?");
        try{
            largeur = saisie.nextInt();
        } catch(InputMismatchException nfe){
            throw new InputMismatchException("Veuillez rentrer une valeur entière");
        }
        Partie p1 = new Partie(joueur, joueur2, hauteur, largeur);
        System.out.println("Choisissez votre mode de jeu ");
        System.out.println("1.Joueur vs Joueur | 2.Joueur vs IA | 3.IA vs IA");
        System.out.println("Tapez 1, 2 ou 3 ");
        int x = 9;
        while (x != 1 || x != 2 || x != 3) {
            try{
            x = saisie.nextInt();
        } catch(InputMismatchException nfe){
                throw new InputMismatchException("Veuillez rentrer une valeur entière");
            }
            if (x == 1) {
                p1.joueurvsjoueur();
            } else if (x == 2) {
                p1.joueurVSIA();
            } else if (x == 3) {
                int victoire = p1.iaVSia(1, 1);
                if (victoire == 1) {
                    System.out.println("Victoire du joueur noir");
                } else {
                    System.out.println("Victoire du joueur blanc");
                }
            }
        }
    }

    public static void main(String[] args){
        testTableau();
    }

}
