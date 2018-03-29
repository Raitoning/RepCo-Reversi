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
        if (etat.isCorner(etat.getX(), etat.getY())) {
            return -100;
        } else if (etat.isMistake(etat.getX(), etat.getY())) {
            return 100;
        }
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

    public int min(Reversi etat, int profondeur, int alpha, int beta, int eval) {
        if (profondeur == 0) {
            if (eval == 1) {
                return eval0bis(etat);
            } else if (eval == 0) {
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
                beta = Math.min(beta, val);
                if (beta <= alpha) {
                    return val;
                }
            }
        }
        return min;
    }

    public int max(Reversi etat, int profondeur, int alpha, int beta, int eval) {
        if (profondeur == 0) {
            if (eval == 1) {
                return eval0bis(etat);
            } else if (eval == 0) {
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
                alpha = Math.max(alpha, val);
                if (alpha >= beta) {
                    return val;
                }
            }
        }
        return max;
    }

}