package reversi.algo;

import reversi.etats.Reversi;

import java.util.Iterator;

public class Algo {
    public int eval0(Reversi etat) {
        if (etat.getJoueur().getColor() == 1) {
            return etat.compterNoir(etat.getPlateau());
        } else if (etat.getJoueur().getColor() == 2) {
            return etat.compterBlanc(etat.getPlateau());
        }
        return 0;
    }

    public int min(Reversi etat, int profondeur, int alpha, int beta) {
        if (profondeur == 0) {
           return eval0(etat);
        }
        int min = 10000;
        int max;
        int val;
        etat.algo();
        Iterator<Reversi> i = etat.successeur();
        while (i.hasNext()) {
            Reversi r = i.next();
            val = max(r, profondeur - 1, alpha, beta);
            if (val < min) {
                min = val;
               /* if (val < beta){
                    beta = val;
                    if (alpha > beta){
                        return val;
                    }
                }*/
            }
        }
        return min;
    }

    public int max(Reversi etat, int profondeur, int alpha, int beta) {
        if (profondeur == 0) {
            return eval0(etat);
        }
        int max = -100;
        int min;
        int val;
        etat.algo();
        Iterator<Reversi> i = etat.successeur();
        Reversi r;
        while (i.hasNext()) {
            r = i.next();
            val = min(r, profondeur - 1, alpha, beta);
            if (val > max) {
                max = val;
              /*  if (val > alpha){
                    alpha = val;
                    if (alpha > beta){
                        return val;
                    }
                }*/
            }
        }
        return max;
    }
}
