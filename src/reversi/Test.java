package reversi;

import java.util.Iterator;

public class Test {
    public static void testTableau(){
        Joueur joueur = new Joueur(2);
        Reversi r = new Reversi(joueur);

      r.aff_tableau();
      System.out.println("-------------");
      r.algo();
      Iterator i = r.successeurBlanc();
      while (i.hasNext()){
          Reversi e = (Reversi)i.next();
         e.aff_tableau();
      }
    }

    public static void main(String[] args){
        testTableau();
    }

}
