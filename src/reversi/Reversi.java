package reversi;

import java.util.ArrayList;
import java.util.Iterator;

public class Reversi extends Etat {
    protected ArrayList<Reversi> successeurBlanc;
    protected ArrayList<Reversi> successeurNoir;
    protected static int BLANC = 2;
    protected static int NOIR = 1;


    public Reversi(Joueur joueur){
        super(joueur);
        plateau = new int[8][8];
        successeurNoir = new ArrayList<Reversi>();
        successeurBlanc = new ArrayList<Reversi>();
        plateau[3][3] = BLANC;
        plateau[4][4] = BLANC;
        plateau[4][3] = NOIR;
        plateau[3][4] = NOIR;

    }

    public Reversi(Joueur joueur, int plateau[][]){
        super(joueur, plateau);
    }

    public int[][] getPlateau(){
        return plateau;
    }

    public int getElement(int x, int y){
        return plateau[x][y];
    }

    public void setElement(int x, int y, int n){
        this.plateau[x][y] = n;
    }

    /*
     * Methode de retours des différents successeurs pour le joueur noir
     */
    public Iterator<Reversi> successeurNoir(){
        return successeurNoir.iterator();
    }

    /*
     * Methode de retours des différents successeurs pour le joueur blanc
     */
    public Iterator<Reversi> successeurBlanc(){
        return successeurBlanc.iterator();
    }

    /*
     * Methode de mouvement vers le bas, tant qu'on trouve des pionts opposés on convertis
     * On stock dans l'arraylist d'état un nouvel état
     */
    public void mouvement_bas(int adverse, int posX, int posY){
        int plateauAct[][] = plateau;
        while (plateauAct[posX][posY] == adverse){
            plateauAct[posX][posY] = joueur.getColor();
            posX++;
        }
        plateauAct[posX][posY] = joueur.getColor();
        if (joueur.getColor() == BLANC){
            successeurBlanc.add(new Reversi(joueur, plateauAct));
        }
    }

    /*
     * Methode de recherche des jetons adverses autours de la position actuelle
     * Si une piece environnante est trouvée, on envoit cette position à une fonction dédiée
     */
    public void check_around(int x, int y, int adverse) {
        if (plateau[x + 1][y] == adverse) {
            mouvement_bas(adverse, x + 1, y);
        } else if (plateau[x - 1][y] == adverse) {

        } else if (plateau[x][y + 1] == adverse) {

        } else if (plateau[x][y - 1] == adverse) {

        } else if (plateau[x + 1][y + 1] == adverse) {

        } else if (plateau[x + 1][y - 1] == adverse) {

        } else if (plateau[x - 1][y + 1] == adverse) {

        } else if (plateau[x - 1][y - 1] == adverse) {

        }
    }

    /*
     * Algo consitant à parcourir le tableau et a envoyer une détection environnante si on trouve le bon pion
     */
    public void algo() {
        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau[0].length; j++) {
                if (plateau[i][j] == BLANC && joueur.getColor() == BLANC) {
                    check_around(i, j, NOIR);
                } else if (plateau[i][j] == NOIR && joueur.getColor() == NOIR) {
                    check_around(i, j, BLANC);
                }
            }
        }
        successeurNoir.add(new Reversi(joueur, plateau));
    }

        public boolean equals(Reversi etat){
        int plateauEtat[][] = etat.getPlateau();
        if (plateauEtat.length != 8 || plateauEtat[0].length != 8){
            return false;
        }
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if (plateau[i][j] != plateauEtat[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    public Joueur getJoueur(){
        return joueur;
    }

    public void aff_tableau(){
        for (int i = 0; i < 8; i++){
            System.out.print("| ");
            for (int j = 0; j < 8; j++){
                System.out.print(plateau[i][j] + " | ");
            }
            System.out.println("");
        }
    }


}
