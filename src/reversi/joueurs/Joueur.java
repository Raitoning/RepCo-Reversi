package reversi.joueurs;


import reversi.etats.Etat;

/** Classe abstraite Joueur.
 *
 * Un joueur est défini par son score et son type ( Humain ou IA )
 *
 */

public abstract class Joueur {

    /** Le score est représenté par un entier.
     * Le type de joueur (Humain ou IA) est représenté par une énumération.
     */
    private int score;
    private TypeJoueur type;

    /** Constructeur.
     *
     * @param type  Type du joueur
     */
    protected Joueur(TypeJoueur type) {

        score = 2;
        this.type = type;
    }

    // TODO: Ajouter le type de retour etats.Etat et un paramètre de type etats.Etat.
    protected Etat jouer() {

        if (type == TypeJoueur.Humain) {

            System.out.println("Position du piont à ajouter: ");
        } else {

            //TODO: Réaliser le parcours des coups possibles
        }

        return null;
    }

    /** Getter de score
     *
     * @return  Le score du joueur
     */
    public int getScore() {

        return score;
    }

    /** Getter de type
     *
     * @return  Le type du joueur
     */
    public TypeJoueur getType() {

        return type;
    }

    /** Affiche l'état d'un joueur
     *
     * @return Le nom du joueur, son type et son score.
     */
    @Override
    public String toString() {

        return this.getClass().getSimpleName() + " (" + type + "): " + score;
    }
}
