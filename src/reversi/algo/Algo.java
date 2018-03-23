package reversi.algo;

import reversi.etats.Reversi;

import java.util.Iterator;

public class Algo {
    public int eval0(Reversi etat) {
        int pionBlanc;
        int pionNoir;
        if (etat.getJoueur().getColor() == 1) {
            pionNoir = etat.compterNoir(etat.getPlateau());
            return pionNoir;
        } else if (etat.getJoueur().getColor() == 2) {
            pionBlanc = etat.compterBlanc(etat.getPlateau());
            return pionBlanc;
        }
        return 0;
    }

    public int eval0bis(Reversi etat) {
        etat.algo();
        return etat.sizeSuccesseur();
    }

    public int min(Reversi etat, int profondeur, int alpha, int beta, int eval) {
        if (profondeur == 0) {
            if (eval == 1) {
                return eval0bis(etat);
            }else if (eval == 0){
                return eval0(etat);
            }
        }
        int min = 10000;
        int max;
        int val;
        etat.algo();
        Iterator<Reversi> i = etat.successeur();
        while (i.hasNext()) {
            Reversi r = i.next();
            val = max(r, profondeur - 1, alpha, beta, eval);
            if (val < min) {
                min = val;
                if (val < beta){
                    beta = val;
                    if (alpha >= beta){
                        return val;
                    }
                }
            }
        }
        return min;
    }

    public int max(Reversi etat, int profondeur, int alpha, int beta, int eval) {
        if (profondeur == 0) {
            if (eval == 1) {
                return eval0bis(etat);
            }else if (eval == 0){
                return eval0(etat);
            }
        }
        int max = -100;
        int min;
        int val;
        etat.algo();
        Iterator<Reversi> i = etat.successeur();
        Reversi r;
        while (i.hasNext()) {
            r = i.next();
            val = min(r, profondeur - 1, alpha, beta, eval);
            if (val > max) {
                max = val;
               if (val > alpha){
                    alpha = val;
                    if (alpha >= beta){
                        return val;
                    }
                }
            }
        }
        return max;
    }

    public int alphabeta(Reversi etat, int profondeur, int alpha, int beta,
                     boolean maximiserJoueur) {

        etat.algo();

        if(profondeur == 0 || !etat.successeur().hasNext()) {

            return eval0(etat);
        }

        int v;

        Iterator<Reversi> iterator = etat.successeur();
        Reversi state;

        if(maximiserJoueur) {

            v = Integer.MIN_VALUE;

            while (iterator.hasNext()) {

                state = iterator.next();

                v = Math.max(v, alphabeta(state, profondeur - 1, alpha,
                        beta, false));

                alpha = Math.max(alpha, v);

                /* Beta coupe */
                if(beta <= alpha) {

                    break;
                }
            }
        } else {

            v = Integer.MIN_VALUE;

            while (iterator.hasNext()) {

                state = iterator.next();

                v = Math.min(v, alphabeta(state, profondeur - 1, alpha,
                        beta, true));

                beta = Math.min(beta, v);

                /* Alpha coupe */
                if(beta <= alpha) {

                    break;
                }
            }

        }

        return v;
    }
}
