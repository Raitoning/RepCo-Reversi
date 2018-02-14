public class Test {
    public static void testTableau(){
        Joueur joueur = new Joueur();
        Reversi r = new Reversi(joueur);
        r.setPlateau();
        int plateau[][] = new int[8][8];
      /*for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if (i == 4 && j == 4 || i == 5 && j == 5){
                    plateau[i][j] = 2;
                } else if (i == 4 && j == 5 || i == 5 && j == 4){
                    plateau[i][j] = 1;
                } else {
                    plateau[i][j] = 0;
                }
            }
        }
       System.out.println(r.equals(plateau));*/
    }

    public static void main(String[] args){
        testTableau();
    }

}
