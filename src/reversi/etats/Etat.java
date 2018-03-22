package reversi.etats;

import reversi.joueurs.Joueur;

public abstract class Etat {

    protected Joueur joueur;
    protected int plateau[][];
    public Etat(Joueur joueur){
        this.joueur = joueur;
    }

    public Etat(Joueur joueur, int plateau[][]){
        this.joueur = joueur;
        this.plateau = plateau;
    }

    public Etat(Reversi r){
        this.joueur = r.joueur;
        this.plateau = r.plateau;
    }
}
