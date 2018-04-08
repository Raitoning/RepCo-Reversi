package reversi.etats;

import reversi.Partie;
import reversi.joueurs.Joueur;

public abstract class Etat {

    protected Joueur joueur;
    protected int plateau[][];
    protected static int BLANC = 2;
    protected static int NOIR = 1;
    protected static int HAUT = 3;
    protected static int BAS = 4;
    protected static int GAUCHE = 5;
    protected static int DROITE = 6;
    protected static int HAUTGAUCHE = 7;
    protected static int BASGAUCHE = 8;
    protected static int BASDROITE= 9;
    protected static int HAUTDROITE = 10;
    protected int hauteur;
    protected int largeur;
    public Etat(Joueur joueur, int hauteur, int largeur){
        this.joueur = joueur;
    }

    public Etat(Joueur joueur, int plateau[][]){
        this.joueur = joueur;
        this.plateau = plateau;
    }

}
