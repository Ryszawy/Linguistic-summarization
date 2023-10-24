package pl.ksr.logic.variables;

import pl.ksr.logic.set.FuzzySet;

import java.io.Serializable;

public abstract class Quantifier<Double> implements Serializable {
    private String name;
    private FuzzySet<Double> fuzzySet;

    public Quantifier(String name, FuzzySet<Double> fuzzySet) {
        this.name = name;
        this.fuzzySet = fuzzySet;
    }

    public String getName() {
        return name;
    }

    public FuzzySet<Double> getFuzzySet() {
        return fuzzySet;
    }

    @Override
    public String toString() {
        return getName();
    }

    public boolean isNormal() {
        return true;
    }

    public boolean isConvex() {
        return true;
    }
}
