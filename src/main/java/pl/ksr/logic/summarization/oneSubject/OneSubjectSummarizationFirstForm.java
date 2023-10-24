package pl.ksr.logic.summarization.oneSubject;

import pl.ksr.logic.summarization.SummarizationHelper;
import pl.ksr.logic.database.Earthquake;
import pl.ksr.logic.set.Set;
import pl.ksr.logic.variables.AbsoluteQuantifier;
import pl.ksr.logic.variables.LinguisticVariable;
import pl.ksr.logic.variables.Quantifier;

import java.util.ArrayList;
import java.util.List;


public class OneSubjectSummarizationFirstForm extends OneSubjectSummarization {
    public OneSubjectSummarizationFirstForm(String subject, List<LinguisticVariable> summarizers,
                                            Quantifier quantifier,
                                            ArrayList<Earthquake> earthquakes) {
        super(subject, summarizers, SummarizationHelper.getAll(earthquakes), quantifier, earthquakes);
    }

    @Override
    public double getDegreeOfTruth() {

        Set compoundSummarizer = summarizers.get(0).getFuzzySet().intersection(
                SummarizationHelper.extractFuzzySet(summarizers));
        if (quantifier.getClass() == AbsoluteQuantifier.class) {
            return quantifier.getFuzzySet().compute(compoundSummarizer.getCardinality(earthquakes));
        } else {
            return quantifier.getFuzzySet().compute(compoundSummarizer.getCardinality(earthquakes)/earthquakes.size());
        }
    }

    @Override
    public String toString() {
        return quantifier.getName() +
                " " +
                subject +
                SummarizationHelper.summarizersToString(summarizers);
    }
}
