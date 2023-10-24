package pl.ksr.logic.set;

import pl.ksr.logic.universeOfDiscourse.UniverseOfDiscourse;

import java.util.ArrayList;

public class NonFuzzySet <T> implements Set<T>{
    UniverseOfDiscourse universeOfDiscourse;
    ArrayList<Double> elements;

    public NonFuzzySet(UniverseOfDiscourse universeOfDiscourse, ArrayList<Double> elements) {
        this.universeOfDiscourse = universeOfDiscourse;
        this.elements = elements;
    }

    public UniverseOfDiscourse getUniverseOfDiscourse() {
        return universeOfDiscourse;
    }

    public ArrayList<Double> getElements() {
        return elements;
    }

    @Override
    public double compute(T x) {
        if (elements.contains(x)) {
            return 1;
        }
        else return 0;
    }
}
