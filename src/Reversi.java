public class Reversi extends Etat {
    protected int plateau[][];

    public Reversi(Joueur joueur){
        super(joueur);
    }

    public int[][] getPlateau(){
        return plateau;
    }

    public boolean compare(int[][] plateau){
        if (this.plateau.equals(plateau)){
            return true;
        }
        return false;
    }

    public Joueur getJoueur(){
        return joueur;
    }

    public void setPlateau(){
        plateau = new int[8][8];
        /*
        Création plateau :
        0 -> Case vide
        1 -> Noir
        2 -> Blanc
         */
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == 4 && j == 4 || i == 5 && j == 5) {
                    plateau[i][j] = 2;
                } else if (i == 4 && j == 5 || i == 5 && j == 4) {
                    plateau[i][j] = 1;
                } else {
                    plateau[i][j] = 0;
                }
            }
        }
    }

    public void aff_tableau(){

    }


}
