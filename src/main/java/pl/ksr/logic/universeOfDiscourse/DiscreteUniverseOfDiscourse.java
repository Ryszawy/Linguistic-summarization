package pl.ksr.logic.universeOfDiscourse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class DiscreteUniverseOfDiscourse implements UniverseOfDiscourse, Serializable {
    private final ArrayList<Double> elements;

    public DiscreteUniverseOfDiscourse(ArrayList<Double> elements) {
        this.elements = elements;
    }

    public ArrayList<Double> getElements() {
        return elements;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        for (int i = 0; i < elements.size(); i++) {
            stringBuilder.append(elements.get(i));
            if (i != elements.size() - 1)
                stringBuilder.append(", ");
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    @Override
    public double size() {
        return elements.size();
    }

    @Override
    public boolean inUniverse(double x) {
        return elements.contains(x);
    }

    @Override
    public double getMinimum() {
        return Collections.min(elements);
    }

    @Override
    public double getMaximum() {
        return Collections.max(elements);
    }
}
