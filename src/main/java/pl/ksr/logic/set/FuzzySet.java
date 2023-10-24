package pl.ksr.logic.set;

import pl.ksr.logic.function.MembershipFunction;
import pl.ksr.logic.universeOfDiscourse.UniverseOfDiscourse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.function.Function;

public class FuzzySet<T> implements Set<T>,Serializable{
    private UniverseOfDiscourse universeOfDiscourse;
    private MembershipFunction membershipFunction;
    private SerializableFunction <T, Double > extractor;

    public FuzzySet(UniverseOfDiscourse universeOfDiscourse,
                    MembershipFunction membershipFunction, SerializableFunction<T, Double> extractor) {
        this.universeOfDiscourse = universeOfDiscourse;
        this.membershipFunction = membershipFunction;
        this.extractor =  extractor;
    }

    public UniverseOfDiscourse getUniverseOfDiscourse() {
        return universeOfDiscourse;
    }

    public MembershipFunction getMembershipFunction() {
        return membershipFunction;
    }

    public double compute(T x) {
        double value = extractor.apply(x);
        return membershipFunction.compute(value);
    }
    public double getCLM() {
        return membershipFunction.calculateArea();
    }

}
