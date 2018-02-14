public class Reversi extends Etat {
    protected int plateau[][];

    public Reversi(Joueur joueur){
        super(joueur);
    }

    public int getElement(int x, int y){
        return plateau[x][y];
    }

    public void setElement(int x, int y, int n){
        this.plateau[x][y] = n;
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
                if (i == 3 && j == 3 || i == 4 && j == 4) {
                    plateau[i][j] = 2;
                } else if (i == 3 && j == 4 || i == 4 && j == 3) {
                    plateau[i][j] = 1;
                } else {
                    plateau[i][j] = 0;
                }
            }
        }
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
