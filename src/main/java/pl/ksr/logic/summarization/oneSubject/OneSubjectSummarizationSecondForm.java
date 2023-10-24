package pl.ksr.logic.summarization.oneSubject;

import pl.ksr.logic.summarization.SummarizationHelper;
import pl.ksr.logic.database.Earthquake;
import pl.ksr.logic.set.Set;
import pl.ksr.logic.variables.LinguisticVariable;
import pl.ksr.logic.variables.RelativeQuantifier;

import java.util.ArrayList;
import java.util.List;

public class OneSubjectSummarizationSecondForm extends OneSubjectSummarization{


    public OneSubjectSummarizationSecondForm(String subject, List<LinguisticVariable> summarizers, List<LinguisticVariable> qualifiers, RelativeQuantifier quantifier, ArrayList<Earthquake> earthquakes) {
        super(subject, summarizers, qualifiers, quantifier, earthquakes);
    }

    @Override
    public double getDegreeOfTruth() {
        Set compoundSummarizer = summarizers.get(0).getFuzzySet().intersection(
                SummarizationHelper.extractFuzzySet(summarizers));
        Set compoundQualifier = qualifiers.get(0).getFuzzySet().intersection(
                SummarizationHelper.extractFuzzySet(qualifiers));
        return quantifier.getFuzzySet().compute(compoundSummarizer.intersection(
                SummarizationHelper.extractFuzzySet(qualifiers)).getCardinality(earthquakes)
                /compoundQualifier.getCardinality(earthquakes));
    }

    @Override
    public String toString() {
        return quantifier.getName() +
                " " +
                subject +
                SummarizationHelper.qualifiersToString(qualifiers) +
                SummarizationHelper.summarizersToString(summarizers);
    }
}
