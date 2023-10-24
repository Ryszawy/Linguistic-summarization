package pl.ksr.logic.summarization.oneSubject;

import pl.ksr.logic.summarization.SummarizationHelper;
import pl.ksr.logic.database.Earthquake;
import pl.ksr.logic.set.Set;
import pl.ksr.logic.variables.LinguisticVariable;
import pl.ksr.logic.variables.Quantifier;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static pl.ksr.logic.summarization.SummarizationHelper.format;

public abstract class OneSubjectSummarization {

    protected String subject;

    protected List<LinguisticVariable> summarizers;

    protected List<LinguisticVariable> qualifiers;

    protected Quantifier quantifier;

    protected ArrayList<Earthquake> earthquakes;

    public OneSubjectSummarization(String subject, List<LinguisticVariable> summarizers,
                                   List<LinguisticVariable> qualifiers, Quantifier quantifier,
                                   ArrayList<Earthquake> earthquakes) {
        this.subject = subject;
        this.summarizers = summarizers;
        this.qualifiers = qualifiers;
        this.quantifier = quantifier;
        this.earthquakes = earthquakes;
    }

    public abstract double getDegreeOfTruth();

    public double getDegreeOfImprecision() {
        double result = 1;
        for (LinguisticVariable summarizer : summarizers) {
            result *= summarizer.getFuzzySet().getDegreeOfFuzziness(earthquakes);
        }
        return 1 - Math.pow(result, 1.0/summarizers.size());
    }

    public double getDegreeOfCovering() {
        Set compoundSummarizer = summarizers.get(0).getFuzzySet().intersection(
                SummarizationHelper.extractFuzzySet(summarizers));
        Set compoundQualifier = qualifiers.get(0).getFuzzySet().intersection(
                SummarizationHelper.extractFuzzySet(qualifiers));

        return compoundSummarizer.intersection(
                SummarizationHelper.extractFuzzySet(qualifiers)
        ).getSupport(earthquakes).getElements().size()/
                (double) compoundQualifier.getSupport(earthquakes).getElements().size();
    }

    ;

    public double getDegreeOfAppropriateness() {
        double r ;
        double result =1;
        for (LinguisticVariable summarizer: summarizers) {
            r = summarizer.getFuzzySet().getSupport(earthquakes).getElements().size() / (double) earthquakes.size();
            result *= r - getDegreeOfCovering();
        }
        return Math.abs(result);
    }

    ;

    public double getLengthOfSummary() {
        return 2 * Math.pow(0.5, summarizers.size());
    }

    ;

    public double getDegreeOfQuantifierImprecision() {
       double max = quantifier.getFuzzySet().getMembershipFunction().getMaxArg();
       double min = quantifier.getFuzzySet().getMembershipFunction().getMinArg();
            return 1 -(max - min)/ quantifier.getFuzzySet().getUniverseOfDiscourse().size();
    }

    ;

    public double getDegreeOfQuantifierCardinality() {
            return 1 - quantifier.getFuzzySet().getCLM()/quantifier.getFuzzySet().getUniverseOfDiscourse().size();
    }

    ;

    public double getDegreeOfSummarizerCardinality() {
        double result = 1;
        for (LinguisticVariable summarizer: summarizers) {
            result *= summarizer.getFuzzySet().getCardinality(earthquakes)/earthquakes.size();
        }
        return 1- Math.pow(result, 1.0/summarizers.size());
    }

    ;

    public double getDegreeOfQualifierImprecision() {
        double result = 1;
        for (LinguisticVariable qualifier: qualifiers) {
            result *= qualifier.getFuzzySet().getDegreeOfFuzziness(earthquakes);
        }
        return 1 - Math.pow(result,qualifiers.size());
    }

    ;

    public double getDegreeOfQualifierCardinality() {
        double result = 1;
        for (LinguisticVariable qualifier: qualifiers) {
            result *= qualifier.getFuzzySet().getCardinality(earthquakes)/earthquakes.size();
        }
        return 1- Math.pow(result, 1.0/qualifiers.size());
    }


    public double getLengthOfQualifier() {
        return 2.0 * Math.pow(0.5, qualifiers.size());
    }


    public double getTheOptimalSummary(ArrayList<Double> measures, ArrayList<Double> weights) {
        double result = 0;
        for (int i = 0; i < 11; i++) {
            result += measures.get(i) * weights.get(i);
        }
        return result;
    }

    public ArrayList<String> generate(ArrayList<Double> weights) {
        ArrayList<Double> measures =  new ArrayList<Double>(Arrays.asList(
                this.getDegreeOfTruth(),
                this.getDegreeOfImprecision(),
                this.getDegreeOfCovering(),
                this.getDegreeOfAppropriateness(),
                this.getLengthOfSummary(),
                this.getDegreeOfQuantifierImprecision(),
                this.getDegreeOfQuantifierCardinality(),
                this.getDegreeOfSummarizerCardinality(),
                this.getDegreeOfQualifierImprecision(),
                this.getDegreeOfQualifierCardinality(),
                this.getLengthOfQualifier()));
        DecimalFormat df =new DecimalFormat("0.00");
        DecimalFormat df2 = new DecimalFormat(">0.00");



     return new ArrayList<String>(Arrays.asList(
             format(measures.get(0)),
             format(measures.get(1)),
             format(measures.get(2)),
             format(measures.get(3)),
             format(measures.get(4)),
             format(measures.get(5)),
             format(measures.get(6)),
             format(measures.get(7)),
             format(measures.get(8)),
             format(measures.get(9)),
             format(measures.get(10)),
             format(this.getTheOptimalSummary(measures,weights))
            ));
    }


}
