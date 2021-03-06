package fr.umontpellier.iut.algogen.individus;

import java.security.SecureRandom;
import java.util.ArrayList;

import fr.umontpellier.iut.algogen.Coord;
import fr.umontpellier.iut.algogen.Instance;
import fr.umontpellier.iut.algogen.Solution;
import fr.umontpellier.iut.algogen.outils.PetitPoucet;

/**
 * GDBHSmartCrossing est la classe représentant une fonctionnalité de croisement
 * un peut plus intélligente.
 * 
 * @see IndividuGDBH
 * @version 1.0.2
 */
public class GDBHSmartCrossing extends IndividuGDBH<GDBHSmartCrossing> {

    public GDBHSmartCrossing(Instance instance, ArrayList<Character> trajet) {
        super(instance, trajet);
    }

    public GDBHSmartCrossing(Instance instance) {
        super(instance);
    }

    public GDBHSmartCrossing(Instance instance, Solution solution) {
        super(instance, solution);
    }

    /**
     * Fait le croisement intelligent entre this et individu2 comme dans le texte.
     * 
     * @param individu2 : Un deuxieme individu avec le quel le croisement doit être
     *                  opéré.
     * 
     * @return un individu fils de type {@code GDBHSimple}.
     * 
     * @since 1.0.2
     **/
    public GDBHSmartCrossing calculerCroisement(GDBHSmartCrossing individu2) {
        int p = indexRandom();
        Coord coord2 = calculerSol().get(p);
        Coord coord1 = individu2.calculerSol().get(p);
        Solution transition = PetitPoucet.getPlusCourtChemin(coord1, coord2);
        transition.add(0, coord1);
        ArrayList<Character> trajetCroise = new ArrayList<>(t.subList(0, p));
        trajetCroise.addAll(convertieEnTrajet(transition));
        if (trajetCroise.size() < in.getK())
            trajetCroise.addAll(individu2.t.subList(p, p + in.getK() - trajetCroise.size()));
        GDBHSmartCrossing individuCroise = new GDBHSmartCrossing(in, trajetCroise);
        if (!in.estValide(individuCroise.calculerSol()))
            individuCroise.normaliseTrajet();
        return individuCroise;
    }

    int indexRandom() {
        return new SecureRandom().nextInt(in.getK() / 2);
    }

    /**
     * Cette fonction est la même que {@link GDBHSimple#calculerMutation()}
     * 
     * @return un individu fils muté de type {@code GDBHSimple}.
     * 
     * @see GDBHSimple#calculerMutation()
     **/
    public GDBHSmartCrossing calculerMutation() {
        GDBHSimple gdbhSimple = new GDBHSimple(in, t);
        return new GDBHSmartCrossing(in, gdbhSimple.calculerMutation().t);
    }
}
