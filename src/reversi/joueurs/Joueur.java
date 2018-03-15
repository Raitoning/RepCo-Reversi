package reversi.joueurs;


import reversi.etats.Etat;

import java.util.Scanner;

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
    private Scanner scanner;

    protected static int BLANC = 2;
    protected static int NOIR = 1;

    /** Constructeur.
     *
     * @param type  Type du joueur
     */
    protected Joueur(TypeJoueur type) {

        score = 2;
        this.type = type;

        scanner = new Scanner(System.in);
    }

    // TODO: Ajouter le type de retour etats.Etat et un paramètre de type etats.Etat.
    public Etat jouer(int[][] plateau) {

        int opposedColor;

        // DEBUG: Afficher le type du joueur actuel.
        System.out.println("Type: " + type);

        if(getColor() == 2) {

            opposedColor = 1;
        } else {

            opposedColor = 2;
        }

        if(type == TypeJoueur.Humain) {

            int x;
            int y;
            boolean error = true;
            boolean column = false;
            boolean line = false;
            boolean diagonal = false;

            // DEBUG: Afficher la couleur du joueur actuel
            System.out.println("Couleur: " + getColor());

            // Tant que la position demandée est invalide
            while (error) {

                error = false;
                x = -1;
                y = -1;

                // Tant que la position X est invalide, la redemmander.
                while(x < 0 || x > plateau[0].length) {

                    System.out.println("X:");
                    x = scanner.nextInt();
                }

                // Tant que la position Y est invalide, la redemmander.
                while(y < 0 || y > plateau.length) {

                    System.out.println("Y:");
                    y = scanner.nextInt();
                }

                // DEBUG: Afficher la position choisie par le joueur
                System.out.println("X: " + x + "; Y: " + y);


                // Vérifier la position du pion
                if(y > 0 && y < plateau.length - 1) {

                    if(x > 0 && x < plateau[0].length - 1) {

                        // DEBUG: Afficher les valeurs des cases adjacentes.
//                        System.out.println(plateau[y - 1][x]);
//                        System.out.println(plateau[y + 1][x]);
//                        System.out.println(plateau[y][x - 1]);
//                        System.out.println(plateau[y][x + 1]);

                        // Si la position es déjà occupée par un pion.
                        if(plateau[y][x] != 0) {

                            error = true;
                            System.out.println("Erreur");
                        } else {

                            // Si un pion de la couleur opposée se trouve sur
                            // la même colone;
                            if( (plateau[y - 1][x] == opposedColor) ||
                                    (plateau[y + 1][x] == opposedColor) ) {

                                column = true;

                            }

                            // Si un pion de la couleur opposée se trouve sur
                            // la même ligne;
                            if( (plateau[y][x - 1] == opposedColor) ||
                                    (plateau[y][x + 1] == opposedColor) ) {

                                line = true;
                            }

                            // Si un pion de la couleur opposée se trouve sur
                            // la diagonale;
                            if( (plateau[y - 1][x - 1] == opposedColor)
                                    || (plateau[y + 1][x + 1] == opposedColor)
                                    || (plateau[y + 1][x - 1] == opposedColor)
                                    || (plateau[y - 1][x + 1] == opposedColor)
                                    ) {

                                diagonal = true;
                            }

                            System.out.println("Column: " + column);
                            System.out.println("Line: " + line);
                            System.out.println("Diagonal: " + diagonal);

                            if(column) {


                            }

                            if(!column && !line && !diagonal) {

                                error = true;
                                // DEBUG: La position est invalide
                                System.out.println("Invalide");
                            }
                        }
                    }
                }
            }

        } else {


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

    public abstract int getColor();

    public abstract int getAdversaire();
}
