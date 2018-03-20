package reversi.etats;

import reversi.joueurs.Joueur;
import java.util.ArrayList;
import java.util.Iterator;

public class Reversi extends Etat {
    protected ArrayList<Reversi> successeurBlanc;
    protected ArrayList<Reversi> successeurNoir;
    protected int compteurBlanc;
    protected int compteurNoir;
    protected int plateauEtat[][];
    private int x;
    private int noeud;
    private int y;
    private boolean erreur;
    protected static int BLANC = 2;
    protected static int NOIR = 1;
    private int hauteur;
    private int largeur;


    public Reversi(Joueur joueur){
        super(joueur);
        plateau = new int[8][8];
        hauteur = 8;
        largeur = 8;
        successeurNoir = new ArrayList<Reversi>();
        successeurBlanc = new ArrayList<Reversi>();
        plateau[3][3] = BLANC;
        plateau[4][4] = BLANC;
        plateau[4][3] = NOIR;
        plateau[3][4] = NOIR;
        compteurBlanc = 2;
        compteurNoir = 2;
        nettoyer();
    }

    public Reversi(Joueur joueur, int plateau[][], boolean nouvelEtat){
        super(joueur, plateau);
        largeur = 8;
        hauteur = 8;
        compteurNoir = compterNoir(plateau);
        if (nouvelEtat == true){
            successeurNoir = new ArrayList<Reversi>();
            successeurBlanc = new ArrayList<Reversi>();
        }
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

    public void setNoeud(int val){
        noeud = val;
    }

    public int getNoeud(){
        return noeud;
    }

    public void nettoyer(){
        successeurBlanc.clear();
        successeurNoir.clear();
    }

    public int getCompteurBlanc(){
        return compteurBlanc;
    }

    public int getCompteurNoir(){
        return compteurNoir;
    }
    public int[][] getPlateau(){
        return plateau;
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
        if (x < largeur && y < hauteur){
            return true;
        }else {
            return false;
        }
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
        int tab[][] = new int[8][8];
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                tab[i][j] = plateau[i][j];
            }
        }
        return tab;
    }

    /*
     * Methode de mouvement vers le bas, tant qu'on trouve des pionts opposés on convertis
     * On stock dans l'arraylist d'état un nouvel état
     */
    public void mouvement_sud(int adverse, int posX, int posY){
        plateauEtat = copy_tab();
        Reversi etatAct = new Reversi(joueur, plateauEtat, false);
        while (etatAct.getElement(posX, posY) == adverse){
            if (etatAct.getElement(posX+1, posY) == joueur.getColor()){
                erreur = true;
            }
            etatAct.setElement(posX, posY, joueur.getColor());
            posX++;
        }
        etatAct.setElement(posX, posY, joueur.getColor());
        if (joueur.getColor() == BLANC && erreur == false){
           successeurBlanc.add(etatAct);
        }else if (joueur.getColor() == NOIR && erreur == false){
            successeurNoir.add(etatAct);
        }
    }

    public void mouvement_nord(int adverse, int posX, int posY){
        plateauEtat = copy_tab();
        Reversi etatAct = new Reversi(joueur, plateauEtat, false);
        while (etatAct.getElement(posX, posY) == adverse){
            if (etatAct.getElement(posX-1, posY) == joueur.getColor()){
                erreur = true;
            }
            etatAct.setElement(posX, posY, joueur.getColor());
            posX--;
        }
        etatAct.setElement(posX, posY, joueur.getColor());
        if (joueur.getColor() == BLANC && erreur == false){
            successeurBlanc.add(etatAct);
        }else if (joueur.getColor() == NOIR && erreur == false){
            successeurNoir.add(etatAct);
        }
    }

    public void mouvement_ouest(int adverse, int posX, int posY){
        plateauEtat = copy_tab();
        // int plateauAct[][] = Arrays.copyOf(plateau, 8);
        Reversi etatAct = new Reversi(joueur, plateauEtat, false);
        while (etatAct.getElement(posX, posY) == adverse && isExist(posX, posY-1)){
               if (etatAct.getElement(posX, posY - 1) == joueur.getColor()) {
                    erreur = true;
                }
                etatAct.setElement(posX, posY, joueur.getColor());
                posY--;
        }
        etatAct.setElement(posX, posY, joueur.getColor());
        if (joueur.getColor() == BLANC && erreur == false){
            successeurBlanc.add(etatAct);
        }else if (joueur.getColor() == NOIR && erreur == false){
            successeurNoir.add(etatAct);
        }
    }

    public void mouvement_est(int adverse, int posX, int posY){
        plateauEtat = copy_tab();
        Reversi etatAct = new Reversi(joueur, plateauEtat, false);
        while (etatAct.getElement(posX, posY) == adverse){
            if (etatAct.getElement(posX, posY+1) == joueur.getColor()){
                erreur = true;
            }
            etatAct.setElement(posX, posY, joueur.getColor());
            posY++;
        }
        etatAct.setElement(posX, posY, joueur.getColor());
        if (joueur.getColor() == BLANC && erreur == false){
            successeurBlanc.add(etatAct);
        }else if (joueur.getColor() == NOIR && erreur == false){
            successeurNoir.add(etatAct);
        }
    }

    public void mouvement_nest(int adverse, int posX, int posY){
        plateauEtat = copy_tab();
        Reversi etatAct = new Reversi(joueur, plateauEtat, false);
        while (etatAct.getElement(posX+1, posY+1) == adverse){
            if (etatAct.getElement(posX, posY) == joueur.getColor()){
                erreur = true;
            }
            etatAct.setElement(posX, posY, joueur.getColor());
            posX++;
            posY++;
        }
        etatAct.setElement(posX, posY, joueur.getColor());
        if (joueur.getColor() == BLANC && erreur == false){
            successeurBlanc.add(etatAct);
        }else if (joueur.getColor() == NOIR && erreur == false){
            successeurNoir.add(etatAct);
        }
    }

    public void mouvement_nouest(int adverse, int posX, int posY){
        plateauEtat = copy_tab();
        Reversi etatAct = new Reversi(joueur, plateauEtat, false);
        while (etatAct.getElement(posX, posY) == adverse){
            if (etatAct.getElement(posX+1, posY-1) == joueur.getColor()){
                erreur = true;
            }
            etatAct.setElement(posX, posY, joueur.getColor());
            posX++;
            posY--;
        }
        etatAct.setElement(posX, posY, joueur.getColor());
        if (joueur.getColor() == BLANC && erreur == false){
            successeurBlanc.add(etatAct);
        }else if (joueur.getColor() == NOIR && erreur == false){
            successeurNoir.add(etatAct);
        }
    }
    public void mouvement_souest(int adverse, int posX, int posY){
        plateauEtat = copy_tab();
        Reversi etatAct = new Reversi(joueur, plateauEtat, false);
        while (etatAct.getElement(posX, posY) == adverse){
            if (etatAct.getElement(posX-1, posY-1) == joueur.getColor()){
                erreur = true;
            }
            etatAct.setElement(posX, posY, joueur.getColor());
            posX--;
            posY--;
        }
        etatAct.setElement(posX, posY, joueur.getColor());
        if (joueur.getColor() == BLANC && erreur == false){
            successeurBlanc.add(etatAct);
        }else if (joueur.getColor() == NOIR && erreur == false){
            successeurNoir.add(etatAct);
        }
    }

    public void mouvement_sest(int adverse, int posX, int posY){
        plateauEtat = copy_tab();
        Reversi etatAct = new Reversi(joueur, plateauEtat, false);
        while (etatAct.getElement(posX, posY) == adverse){
            if (etatAct.getElement(posX-1, posY+1) == joueur.getColor()){
                erreur = true;
            }
            etatAct.setElement(posX, posY, joueur.getColor());
            posX--;
            posY++;
        }
        etatAct.setElement(posX, posY, joueur.getColor());
        if (joueur.getColor() == BLANC && erreur == false){
            successeurBlanc.add(etatAct);
        }else if (joueur.getColor() == NOIR && erreur == false){
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
            mouvement_sud(adverse, x + 1, y);
        }
        if (getElement(x - 1, y) == adverse && isExist(x - 1, y)) {
            erreur = false;
            mouvement_nord(adverse, x - 1, y);
        }
        if (getElement(x, y-1) == adverse && isExist(x, y - 1)) {
            erreur = false;
            mouvement_ouest(adverse, x, y - 1);
        }
        if (getElement(x, y+1) == adverse && isExist(x, y + 1)) {
            erreur = false;
            mouvement_est(adverse, x, y + 1);
        }
        if (getElement(x+1, y+1) == adverse && isExist(x + 1, y + 1)) {
            erreur = false;
            mouvement_nest(adverse, x + 1, y + 1);
        }
        if (getElement(x+1, y-1) == adverse && isExist(x + 1, y - 1)) {
            erreur = false;
            mouvement_nouest(adverse, x + 1, y - 1);
        }
        if (getElement(x-1, y+1) == adverse && isExist(x - 1, y + 1)) {
            erreur = false;
            mouvement_sest(adverse, x - 1, y + 1);
        }
        if (getElement(x-1, y-1) == adverse && isExist(x - 1, y - 1)) {
            erreur = false;
            mouvement_souest(adverse, x - 1, y - 1);
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
        System.out.println("-------");
    }
}
