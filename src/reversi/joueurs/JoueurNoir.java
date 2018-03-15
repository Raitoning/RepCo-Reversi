package reversi.joueurs;

/** Classe JoueurNoir.
 * Représente le joueur qui joue avec les pions noirs.
 */
public class JoueurNoir extends Joueur {

    /** Constructeur.
     *
     * @param type  Le type du joueur.
     */
    public JoueurNoir(TypeJoueur type) {

        super(type);
    }

    public int getColor(){
        return 1;
    }
}