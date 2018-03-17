package reversi.algo;

import reversi.etats.Reversi;

import java.util.Iterator;

public class evaluer {
    public int eval0(Reversi etat, int profondeur) {
        int noeud = 0;
        int compare = 0;
        etat.algo();
        Iterator<Reversi> i = etat.successeur();
        while (i.hasNext()) {
            Reversi e = i.next();
            if (profondeur % 2 == 0) {
                compare = eval0(e, profondeur + 1);
                if (compare > noeud) {
                    noeud = compare;
                }
            } else if (profondeur % 2 == 1) {
                compare = eval0(e, profondeur + 1);
                if (compare < noeud) {
                    noeud = compare;
                }
            }
        }
        return 0;
    }
}
