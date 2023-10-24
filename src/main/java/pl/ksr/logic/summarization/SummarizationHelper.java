package pl.ksr.logic.summarization;

import pl.ksr.logic.database.Earthquake;
import pl.ksr.logic.function.MembershipFunction;
import pl.ksr.logic.function.TrapezoidalFunction;
import pl.ksr.logic.set.FuzzySet;
import pl.ksr.logic.universeOfDiscourse.ContinuousUniverseOfDiscourse;
import pl.ksr.logic.universeOfDiscourse.UniverseOfDiscourse;
import pl.ksr.logic.variables.LinguisticVariable;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SummarizationHelper {

    public static ArrayList<LinguisticVariable> getAll(ArrayList<Earthquake> earthquakes) {
        UniverseOfDiscourse eq = new ContinuousUniverseOfDiscourse
                (0, true,
                        18000, false);
        MembershipFunction mem = new TrapezoidalFunction
                (0, 0, 18000, 18000);

        FuzzySet<Earthquake> fs = new FuzzySet<>(eq, mem, earthquake -> Double.valueOf(earthquakes.contains(earthquake) ? 1 : 0));
        LinguisticVariable all = new LinguisticVariable<>("All", fs);
        ArrayList<LinguisticVariable> qualifiers = new ArrayList<>(Arrays.asList(all));
        return qualifiers;
    }

    public static String summarizersToString(List<LinguisticVariable> summarizers) {
        StringBuilder builder = new StringBuilder();
        builder.append(" ");
        builder.append("have");
        builder.append(" ");
        for (int i = 0; i< summarizers.size();i++) {
            builder.append(summarizers.get(i).getName());
            builder.append(" ");
            if (i == summarizers.size() -1) {
                break;
            }
            builder.append("and have ");
        }
        return builder.toString();
    }

    public static String qualifiersToString(List<LinguisticVariable> qualifiers) {
        StringBuilder builder = new StringBuilder();
        builder.append(" ");
        builder.append("which are");
        builder.append(" ");
        for (int i = 0; i< qualifiers.size();i++) {
            builder.append(qualifiers.get(i).getName());
            builder.append(" ");
            if (i == qualifiers.size() -1) {
                break;
            }
            builder.append("and are ");
        }
        return builder.toString();
    }

    public static List<FuzzySet> extractFuzzySet(List<LinguisticVariable> variables) {
        ArrayList<FuzzySet> variablesFS = new ArrayList<>();
        for (int i =0; i< variables.size(); i++) {
            variablesFS.add(variables.get(i).getFuzzySet());
        }
        return variablesFS;
    }

    public static String format(double x) {
        DecimalFormat df =new DecimalFormat("0.00");
        DecimalFormat df2 = new DecimalFormat(">0.00");
        DecimalFormat df3 = new DecimalFormat("0.00>");

        if (x > 0.00 && x< 0.005) {
            return df2.format(x);
        }
        if (x >= 0.995 && x< 1.00) {
            return df3.format(x);
        }
        return df.format(x);
    }

}
