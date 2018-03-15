package reversi.joueurs;

public class Test {

    public static void main(String args[]) {

        Joueur j1 = new JoueurBlanc(TypeJoueur.Humain);
        Joueur j2 = new JoueurNoir(TypeJoueur.Ordinateur);

        System.out.println(j1);
        System.out.println(j2);
    }
}
