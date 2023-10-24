package pl.ksr.logic.summarization.multiSubjects;

import pl.ksr.logic.database.Earthquake;
import pl.ksr.logic.variables.LinguisticVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class TwoSubjectsSummarization {
    String subject1;
    String subject2;
    List<LinguisticVariable> summarizers;
    ArrayList<Earthquake> earthquakesSubject1;
    ArrayList<Earthquake> earthquakesSubject2;

    public TwoSubjectsSummarization(String subject1, String subject2, List<LinguisticVariable> summarizers, ArrayList<Earthquake> earthquakes) {
        this.subject1 = subject1;
        this.subject2 = subject2;
        this.summarizers = summarizers;
        this.earthquakesSubject1 = (ArrayList<Earthquake>) getEarthquakesSubject1(earthquakes);
        this.earthquakesSubject2 = (ArrayList<Earthquake>) getEarthquakesSubject2(earthquakes);
    }

    public abstract double getDegreeOfTruth();

    public List<Earthquake> getEarthquakesSubject1(ArrayList<Earthquake> earthquakes) {
     return  earthquakes.stream().filter(earthquake ->
             earthquake.getType().equals(subject1.replace(" earthquakes", ""))).collect(Collectors.toList());
    }

    public List<Earthquake> getEarthquakesSubject2(ArrayList<Earthquake> earthquakes) {
        return earthquakes.stream().filter(earthquake ->
                earthquake.getType().equals(subject2.replace(" earthquakes", ""))).collect(Collectors.toList());
    }
}
