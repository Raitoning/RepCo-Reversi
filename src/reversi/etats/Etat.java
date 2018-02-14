package reversi.etats;

import reversi.joueurs.Joueur;

public abstract class Etat {

    protected Joueur joueur;

    public Etat(Joueur joueur){

        this.joueur = joueur;
    }
}
