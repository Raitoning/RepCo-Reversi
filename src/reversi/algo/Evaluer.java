package reversi.algo;

import reversi.etats.Reversi;
import reversi.joueurs.JoueurBlanc;
import reversi.joueurs.JoueurNoir;
import reversi.joueurs.TypeJoueur;

import java.util.Iterator;

public class Evaluer {
    Reversi etatStock;
    public Evaluer(){
    }

    public Reversi eval0(Reversi etat, int profondeur, int profAct) {
        if (profAct % 2 == 0) {
            etat.setNoeud(0);
        } else {
            etat.setNoeud(1000);
        }
        int compare = 0;
        Reversi etatAct;
        JoueurNoir jn = new JoueurNoir(TypeJoueur.Ordinateur);
        JoueurBlanc jb = new JoueurBlanc(TypeJoueur.Ordinateur);
        if (profAct == profondeur){
            etat.setNoeud(etat.getCompteurNoir());
        }else {
            etat.algo();
            Iterator<Reversi> i = etat.successeur();
            while (i.hasNext()) {
                Reversi e = i.next();
                if (profAct % 2 == 0) {
                    etatAct = new Reversi(jn, e.getPlateau(), true);
                    compare = eval0(etatAct, profondeur, profAct + 1).getNoeud();
                    if (compare > etat.getNoeud()) {
                        etat.setNoeud(compare);
                    }
                } else if (profAct % 2 == 1) {
                    etatAct = new Reversi(jb, e.getPlateau(), true);
                    compare = eval0(etatAct, profondeur, profAct + 1).getNoeud();
                    if (compare < etat.getNoeud()) {
                        etat.setNoeud(compare);
                    }
                }
            }
        }
        if (profAct == 1){
            etatStock = etat;
        }
        if (profAct == 0){
            return etatStock;
        }
        return etat;
    }
}
