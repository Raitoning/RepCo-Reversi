package reversi.etats;

import reversi.Partie;
import reversi.joueurs.Joueur;
import java.util.ArrayList;
import java.util.Iterator;

public class Reversi extends Etat {
    protected ArrayList<Reversi> successeurBlanc;
    protected ArrayList<Reversi> successeurNoir;
    protected int compteurBlanc;
    protected int compteurNoir;
    protected int plateauEtat[][];
    protected int poid[][];
    private int x;
    private int y;
    private boolean erreur;
    public Reversi(Joueur joueur, int hauteur, int largeur){
        super(joueur, hauteur, largeur);
        poid = new int[hauteur][largeur];
        plateau = new int[hauteur][largeur];
        this.hauteur = hauteur;
        this.largeur = largeur;
        init_poid();
        successeurNoir = new ArrayList<Reversi>();
        successeurBlanc = new ArrayList<Reversi>();
        plateau[hauteur / 2 - 1][largeur / 2 - 1] = BLANC;
        plateau[hauteur / 2][largeur / 2] = BLANC;
        plateau[hauteur / 2][largeur / 2 - 1] = NOIR;
        plateau[hauteur / 2 - 1][largeur / 2] = NOIR;
        nettoyer();
    }

    public int compterVide() {
        int compteur = 0;
        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau[i].length; j++) {
                if (plateau[i][j] == 0) {
                    compteur++;
                }
            }
        }
        return compteur;
    }

    public Reversi(Joueur joueur, int plateau[][], boolean nouvelEtat, int hauteur, int largeur){
        super(joueur, plateau);
        this.hauteur = hauteur;
        this.largeur = largeur;
        poid = new int[hauteur][largeur];
        init_poid();
        compteurNoir = compterNoir(plateau);
        compteurBlanc = compterBlanc(plateau);
        if (nouvelEtat){
            successeurNoir = new ArrayList<Reversi>();
            successeurBlanc = new ArrayList<Reversi>();
        }
    }

    public void init_poid(){
        for (int i = 0; i < poid.length; i++){
            for (int j = 0; j < poid[0].length; j++){
                if (isCorner(i, j)){
                    poid[i][j] = 99;
                }else if (isMistake(i, j)){
                    poid[i][j] = -99;
                }else{
                    if (joueur.getColor() == BLANC){
                        poid[i][j] = compterBlanc(plateau);
                    }else{
                        poid[i][j] = compterNoir(plateau);
                    }
                }
            }
        }
    }

    public boolean isCorner(int x, int y){
        if (x == 0 && y == 0){
            return true;
        }else if (x == 0 && y == largeur - 1){
            return true;
        }else if (x == hauteur - 1 && y == 0){
            return true;
        }else if (x == hauteur - 1 && y == largeur - 1){
            return true;
        }
        return false;
    }

    public int getPoid(int x, int y){
        return poid[x][y];
    }

    public boolean isMistake(int x, int y){
        if (x == 1 && y == 0){
            return true;
        }else if (x == 0 && y == 1){
            return true;
        }else if (x == 1 && y == 1){
            return true;
        }else if (x == hauteur - 2 && y == 0){
            return true;
        }else if (x == hauteur - 1 && y == 1){
            return true;
        }else if (x == hauteur - 2 && y == 1){
            return true;
        }else if (x == 0 && y == largeur - 2){
            return true;
        }else if (x == 1 && y == largeur - 1){
            return true;
        }else if (x == 1 && y == largeur - 2){
            return true;
        }else if (x == hauteur - 2 && y == largeur - 1){
            return true;
        }else if (x == hauteur - 1 && y == largeur - 2){
            return true;
        }else if (x == hauteur - 2 && y == largeur - 2){
            return true;
        }
        return false;
    }

    public int compterNoir(int plateau[][]){
        int compteur = 0;
        for (int i = 0; i < plateau.length; i++){
            for (int j = 0; j < plateau[i].length; j++){
                if (plateau[i][j] == NOIR){
                    compteur++;
                }
            }
        }
        return compteur;
    }

    public int compterBlanc(int plateau[][]){
        int compteur = 0;
        for (int i = 0; i < plateau.length; i++){
            for (int j = 0; j < plateau[i].length; j++){
                if (plateau[i][j] == BLANC){
                    compteur++;
                }
            }
        }
        return compteur;
    }

    public void nettoyer(){
        successeurNoir = new ArrayList<Reversi>();
        successeurBlanc = new ArrayList<Reversi>();
    }

    public int[][] getPlateau(){
        return plateau;
    }

    public void setJoueur(Joueur j){
        joueur = j;
    }
    public int getElement(int x, int y){
        if (x >= hauteur || y >= largeur || x < 0 || y < 0){
            return 100;
        }
        return plateau[x][y];
    }

    public void setElement(int x, int y, int n){
        this.x = x;
        this.y = y;
        if (x >= hauteur || y >= largeur || x < 0 || y < 0){
            erreur = true;
        }else{
            this.plateau[x][y] = n;
            if (n == BLANC){
                compteurBlanc++;
            }
            if (n == NOIR){
                compteurNoir++;
            }
        }

    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int sizeSuccesseur(){
        if (joueur.getColor() == BLANC) {
            return successeurBlanc.size();
        }
        return successeurNoir.size();
    }
    /*
     * Methode de retours des différents successeurs pour le joueur noir
     */
    public Iterator<Reversi> successeur(){
        if (joueur.getColor() == BLANC) {
            return successeurBlanc.iterator();
        }
        return successeurNoir.iterator();
    }

    public int successeursize(){
        if (joueur.getColor() == BLANC) {
            return successeurBlanc.size();
        }
        return successeurNoir.size();
    }

    public boolean isExist(int x, int y){
        return x < largeur && y < hauteur;
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

    public int[][] copy_tab(){
        int tab[][] = new int[hauteur][largeur];
        for (int i = 0; i < hauteur; i++){
            for (int j = 0; j < largeur; j++){
                tab[i][j] = plateau[i][j];
            }
        }
        return tab;
    }

    public void mouvement(int direction, int adverse, int posX, int posY){
        plateauEtat = copy_tab();
        Reversi etatAct = new Reversi(joueur, plateauEtat, false, hauteur, largeur);
        while (etatAct.getElement(posX, posY) == adverse){

            etatAct.setElement(posX, posY, joueur.getColor());
            if (direction == BAS) {
                if (etatAct.getElement(posX+1, posY) == joueur.getColor() || etatAct.getElement(posX+1, posY) == 100){
                    erreur = true;
                }
                posX++;
            }else if (direction == HAUT){
                if (etatAct.getElement(posX-1, posY) == joueur.getColor() || etatAct.getElement(posX-1, posY) == 100){
                    erreur = true;
                }
                posX--;
            }else if (direction == DROITE){
                if (etatAct.getElement(posX, posY+1) == joueur.getColor() || etatAct.getElement(posX, posY+1) == 100){
                    erreur = true;
                }
                posY++;
            }else if (direction == GAUCHE){
                if (etatAct.getElement(posX, posY-1) == joueur.getColor() || etatAct.getElement(posX, posY-1) == 100){
                    erreur = true;
                }
                posY--;
            }else if (direction == HAUTDROITE){
                if (etatAct.getElement(posX-1, posY+1) == joueur.getColor() || etatAct.getElement(posX-1, posY+1) == 100){
                    erreur = true;
                }
                posX--; posY++;
            }else if (direction == BASDROITE){
                if (etatAct.getElement(posX+1, posY+1) == joueur.getColor() || etatAct.getElement(posX+1, posY+1) == 100){
                    erreur = true;
                }
                posX++; posY++;
            }else if (direction == BASGAUCHE){
                if (etatAct.getElement(posX+1, posY-1) == joueur.getColor() || etatAct.getElement(posX+1, posY-1) == 100){
                    erreur = true;
                }
                posX++; posY--;
            }else if (direction == HAUTGAUCHE){
                if (etatAct.getElement(posX-1, posY-1) == joueur.getColor() || etatAct.getElement(posX-1, posY-1) == 100){
                    erreur = true;
                }
                posX--; posY--;
            }
        }
        etatAct.setElement(posX, posY, joueur.getColor());
        if (joueur.getColor() == BLANC && !erreur){
            successeurBlanc.add(etatAct);
        }else if (joueur.getColor() == NOIR && !erreur){
            successeurNoir.add(etatAct);
        }
    }

    /*
     * Methode de recherche des jetons adverses autours de la position actuelle
     * Si une piece environnante est trouvée, on envoit cette position à une fonction dédiée
     */
    public void check_around(int x, int y, int adverse) {
        if (getElement(x+1, y) == adverse && isExist(x + 1, y)) {
            erreur = false;
            mouvement(BAS, adverse, x + 1, y);
        }
        if (getElement(x - 1, y) == adverse && isExist(x - 1, y)) {
            erreur = false;
            mouvement(HAUT, adverse, x - 1, y);
        }
        if (getElement(x, y-1) == adverse && isExist(x, y - 1)) {
            erreur = false;
            mouvement(GAUCHE, adverse, x, y - 1);
        }
        if (getElement(x, y+1) == adverse && isExist(x, y + 1)) {
            erreur = false;
            mouvement(DROITE, adverse, x, y + 1);
        }
        if (getElement(x+1, y+1) == adverse && isExist(x + 1, y + 1)) {
            erreur = false;
            mouvement(BASDROITE, adverse, x + 1, y + 1);
        }
        if (getElement(x+1, y-1) == adverse && isExist(x + 1, y - 1)) {
            erreur = false;
            mouvement(BASGAUCHE, adverse, x + 1, y - 1);
        }
        if (getElement(x-1, y+1) == adverse && isExist(x - 1, y + 1)) {
            erreur = false;
            mouvement(HAUTDROITE, adverse, x - 1, y + 1);
        }
        if (getElement(x-1, y-1) == adverse && isExist(x - 1, y - 1)) {
            erreur = false;
            mouvement(HAUTGAUCHE, adverse, x - 1, y - 1);
        }
    }

    /*
     * Algo consitant à parcourir le tableau et a envoyer une détection environnante si on trouve le bon pion
     */
    public void algo() {
        successeurBlanc = new ArrayList<Reversi>();
        successeurNoir = new ArrayList<Reversi>();
        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau[0].length; j++) {
                if (plateau[i][j] == BLANC && joueur.getColor() == BLANC) {
                    check_around(i, j, NOIR);
                } else if (plateau[i][j] == NOIR && joueur.getColor() == NOIR) {
                    check_around(i, j, BLANC);
                }
            }
        }
    }

    public Joueur getJoueur(){
        return joueur;
    }

    public void aff_tableau(){
        for (int i = 0; i < hauteur; i++){
            System.out.print(i + " | ");
            for (int j = 0; j < largeur; j++){
                System.out.print(plateau[i][j] + " | ");
            }
            System.out.println("");
        }
        for (int j = 0; j < largeur; j++) {
            if (j == 0) {
                System.out.print("    " + j);
            } else {
                System.out.print("   " + j);
            }
            if (j == largeur - 1) {
                System.out.println("");
            }
        }
    }
}
