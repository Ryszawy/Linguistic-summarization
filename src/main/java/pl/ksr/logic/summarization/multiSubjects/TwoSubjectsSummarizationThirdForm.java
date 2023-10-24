package pl.ksr.logic.summarization.multiSubjects;

import pl.ksr.logic.summarization.SummarizationHelper;
import pl.ksr.logic.database.Earthquake;
import pl.ksr.logic.set.Set;
import pl.ksr.logic.variables.LinguisticVariable;
import pl.ksr.logic.variables.RelativeQuantifier;

import java.util.ArrayList;
import java.util.List;

public class TwoSubjectsSummarizationThirdForm extends TwoSubjectsSummarization {
    private RelativeQuantifier relativeQuantifier;
    private List<LinguisticVariable> qualifiers;

    public TwoSubjectsSummarizationThirdForm(String subject1, String subject2,
                                             List<LinguisticVariable> summarizers,
                                             ArrayList<Earthquake> earthquakes,
                                             RelativeQuantifier relativeQuantifier,
                                             List<LinguisticVariable> qualifiers) {
        super(subject1, subject2, summarizers, earthquakes);
        this.relativeQuantifier = relativeQuantifier;
        this.qualifiers = qualifiers;
    }

    @Override
    public double getDegreeOfTruth() {
        Set compoundSummarizer = summarizers.get(0).getFuzzySet().intersection(
                SummarizationHelper.extractFuzzySet(summarizers));
        double meter = 1.0/ earthquakesSubject1.size() * compoundSummarizer.intersection(
                        SummarizationHelper.extractFuzzySet(qualifiers)).getCardinality(earthquakesSubject1);
        double denominator = meter + 1.0/ earthquakesSubject2.size() *
                compoundSummarizer.getCardinality(earthquakesSubject2);
        return relativeQuantifier.getFuzzySet().compute(meter/denominator);

    }

    @Override
    public String toString() {
        return relativeQuantifier.getName() +
                " " +
                subject1 +
                SummarizationHelper.qualifiersToString(qualifiers) +
                " " +
                "in compare to" +
                " " +
                subject2 +
                SummarizationHelper.summarizersToString(summarizers);
    }
}
