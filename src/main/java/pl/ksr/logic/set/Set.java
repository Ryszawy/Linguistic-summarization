package pl.ksr.logic.set;

import pl.ksr.logic.universeOfDiscourse.DiscreteUniverseOfDiscourse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@FunctionalInterface
public interface Set<T> extends Serializable {
    double compute(T x);

    default Set<T> intersection(List<Set<T>> sets) {
        return x -> {
            double min = this.compute(x);
            for (Set<T> set : sets) {
                if (set.compute(x) < min) {
                    min = set.compute(x);
                }
            }
            return min;
        };

    }

    default Set<T> union(List<Set<T>> sets) {
        return x -> {
            double max = this.compute(x);
            for (Set<T> set : sets) {
                if (set.compute(x) > max) {
                    max = set.compute(x);
                }
            }
            return max;
        };
    }

    default Set<T> complement(Set<T> set1) {
        return x -> 1 - set1.compute(x);
    }

    default double getLukasiewiczImplication(double a, double b) {
        return Math.min(1, 1 - a + b);
    }

    default double getReichenbachImplication(double a, double b) {
        return 1 - a + a * b;
    }

    default double getCardinality(List<T> list) {
        return list.stream().mapToDouble(this::compute).sum();
    }

    default NonFuzzySet<T> getSupport(List<T> list) {
        DiscreteUniverseOfDiscourse discreteUniverseOfDiscourse =
                new DiscreteUniverseOfDiscourse((ArrayList<Double>) list);
        ArrayList<Double> elements = new ArrayList<>();
        for (T element : list) {
            if (this.compute(element) > 0) {
                elements.add(this.compute(element));
            }
        }
        return new NonFuzzySet<>(discreteUniverseOfDiscourse, elements);
    }

    default NonFuzzySet<T> getAlphaCut(List<T> list, double alpha) {
        DiscreteUniverseOfDiscourse discreteUniverseOfDiscourse =
                new DiscreteUniverseOfDiscourse((ArrayList<Double>) list);
        ArrayList<Double> elements = new ArrayList<>();
        for (T element : list) {
            if (this.compute(element) > alpha) {
                elements.add(this.compute(element));
            }
        }
        return new NonFuzzySet<>(discreteUniverseOfDiscourse, elements);
    }

    default double getDegreeOfFuzziness(List<T> list) {
        return getSupport(list).getElements().size() / (double) list.size();
    }

    default double getHeight(List<T> list) {
        double max = 0;
        for (T value : list) {
            if (this.compute(value) > max) {
                max = this.compute(value);
            }
            if (max == 1) {
                return max;
            }
        }
        return max;
    }

    ;

    default boolean isNormal(List<T> list) {
        if (getHeight(list) == 1) {
            return true;
        } else {
            return false;
        }
    }

    //TODO
    default boolean isConvex(Set<T> set, List<T> list) {
        return true;
    }

    default boolean isEmpty(Set<T> set, List<T> x) {
        if (set.getSupport(x).isEmpty(set, x)) {
            return false;
        } else return true;
    }

}
