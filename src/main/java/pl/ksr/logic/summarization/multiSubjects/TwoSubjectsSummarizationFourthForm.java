package pl.ksr.logic.summarization.multiSubjects;

import pl.ksr.logic.summarization.SummarizationHelper;
import pl.ksr.logic.database.Earthquake;
import pl.ksr.logic.set.Set;
import pl.ksr.logic.variables.LinguisticVariable;

import java.util.ArrayList;
import java.util.List;

public class TwoSubjectsSummarizationFourthForm extends TwoSubjectsSummarization {
    public TwoSubjectsSummarizationFourthForm(String subject1, String subject2,
                                              List<LinguisticVariable> summarizers,
                                              ArrayList<Earthquake> earthquakes) {
        super(subject1, subject2, summarizers, earthquakes);
    }

    @Override
    public double getDegreeOfTruth() {
        Set compoundSummarizer = summarizers.get(0).getFuzzySet().intersection(
                SummarizationHelper.extractFuzzySet(summarizers));

        return 1 - compoundSummarizer.getLukasiewiczImplication(
                compoundSummarizer.getCardinality(earthquakesSubject2)/earthquakesSubject2.size(),
                compoundSummarizer.getCardinality(earthquakesSubject1)/earthquakesSubject1.size());
    }
    @Override
    public String toString() {
        return "More" +
                " " +
                subject1 +
                " " +
                "than" +
                subject2 +
                SummarizationHelper.summarizersToString(summarizers);
    }
}
