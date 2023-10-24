package pl.ksr.logic.variables;

import pl.ksr.logic.set.FuzzySet;

public class RelativeQuantifier extends Quantifier{
    public RelativeQuantifier(String name, FuzzySet<Double> fuzzySet) {
        super(name, fuzzySet);
    }
}
