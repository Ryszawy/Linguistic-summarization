package pl.ksr.logic.variables;

import pl.ksr.logic.set.FuzzySet;

public class AbsoluteQuantifier extends Quantifier{
    public AbsoluteQuantifier(String name, FuzzySet<Double> fuzzySet) {
        super(name, fuzzySet);
    }
}
