package reversi.joueurs;

/** Classe JoueurBlanc.
 *
 * Représente le joueur qui joue avec les pions blancs.
 */
public class JoueurBlanc extends Joueur {

    /** Constructeur.
     *
     * @param type  Type du joueur.
     */
    public JoueurBlanc(TypeJoueur type) {

        super(type);
    }

    public int getColor(){
        return 2;
    }

    public int getAdversaire(){
        return 1;
    }
}