public class Reversi extends Etat {
    protected int plateau[][];

    public Reversi(Joueur joueur){
        super(joueur);
        plateau = new int[8][8];
        plateau[3][3] = 2;
        plateau[4][4] = 2;
        plateau[4][3] = 1;
        plateau[3][4] = 1;

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
