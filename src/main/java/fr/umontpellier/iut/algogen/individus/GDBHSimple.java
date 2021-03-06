package fr.umontpellier.iut.algogen.individus;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;

import fr.umontpellier.iut.algogen.Instance;
import fr.umontpellier.iut.algogen.Solution;

/**
 * GDBHsimple est la classe représentant une fonctionnalité de croisement et de
 * mutation basique.
 * 
 * @see IndividuGDBH
 * @version 1.0.5
 */
public class GDBHSimple extends IndividuGDBH<GDBHSimple> {
    public GDBHSimple(Instance instance, ArrayList<Character> trajet) {
        super(instance, trajet);
    }

    public GDBHSimple(Instance instance) {
        super(instance);
    }

    public GDBHSimple(Instance instance, Solution solution) {
        super(instance, solution);
    }

    /**
     * Les individus {@code this} et {@code individu2} sont croisés avec des
     * partitions de taille aléatoire de façon à générer un individu, puis
     * l'individu résultant est normalisé.
     * 
     * @param individu2 : individu avec le quel le croisement doit être opéré
     * 
     * @return un individu fils de type GDBHSimple.
     * 
     * @since 1.0.5
     * 
     * @see #normaliseTrajet()
     **/
    public GDBHSimple calculerCroisement(GDBHSimple individu2) {
        int indexSeparation = indexRandom();
        ArrayList<Character> trajetFils = new ArrayList<>(t.subList(0, indexSeparation));
        trajetFils.addAll(individu2.t.subList(indexSeparation, t.size()));
        GDBHSimple croise = new GDBHSimple(in, trajetFils);
        croise.normaliseTrajet();
        return croise;
    }

    /**
     * Permute aléatoirement deux index entre eux. Et normalise celui-ci.
     * 
     * @return un individu muté de type GDBHSimple.
     * 
     * @since 1.0.5
     * 
     * @see #normaliseTrajet()
     * @see Collections#swap(java.util.List, int, int)
     **/
    public GDBHSimple calculerMutation() {
        ArrayList<Character> trajetMute = new ArrayList<>(t);
        // TODO: Est-ce que l'on laisse la possibilité de tiré deux fois le même nombre
        // ?
        Collections.swap(trajetMute, indexRandom(), indexRandom());
        GDBHSimple mute = new GDBHSimple(in, trajetMute);
        mute.normaliseTrajet();
        return mute;
    }

    private int indexRandom() {
        return new SecureRandom().nextInt(t.size());
    }

}