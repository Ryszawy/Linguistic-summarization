package pl.ksr.logic.variables;

import pl.ksr.logic.database.Earthquake;
import pl.ksr.logic.set.FuzzySet;

import java.io.Serializable;

public class LinguisticVariable<Earthquake> implements Serializable {
    private String name;
    private FuzzySet<Earthquake> fuzzySet;

    public LinguisticVariable(String name, FuzzySet<Earthquake> fuzzySet) {
        this.name = name;
        this.fuzzySet = fuzzySet;
    }

    public String getName() {
        return name;
    }

    public FuzzySet<Earthquake> getFuzzySet() {
        return fuzzySet;
    }

    @Override
    public String toString() {
        return getName();
    }
}
