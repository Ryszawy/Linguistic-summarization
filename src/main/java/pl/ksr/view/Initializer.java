package pl.ksr.view;

import pl.ksr.logic.database.AttributeExtractor;
import pl.ksr.logic.database.Earthquake;
import pl.ksr.logic.function.CompoundFunction;
import pl.ksr.logic.function.GaussianFunction;
import pl.ksr.logic.function.MembershipFunction;
import pl.ksr.logic.function.TrapezoidalFunction;
import pl.ksr.logic.set.FuzzySet;
import pl.ksr.logic.universeOfDiscourse.ContinuousUniverseOfDiscourse;
import pl.ksr.logic.universeOfDiscourse.UniverseOfDiscourse;
import pl.ksr.logic.variables.AbsoluteQuantifier;
import pl.ksr.logic.variables.LinguisticVariable;
import pl.ksr.logic.variables.Quantifier;
import pl.ksr.logic.variables.RelativeQuantifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Initializer {
    public static UniverseOfDiscourse ABSOLUTE =
            new ContinuousUniverseOfDiscourse(0, true, 15000, true);
    public static UniverseOfDiscourse RELATIVE = new ContinuousUniverseOfDiscourse(0, true,
            1, true);

    public static UniverseOfDiscourse MINUTES = new ContinuousUniverseOfDiscourse(0, true,
            1440, false);
    public static UniverseOfDiscourse CLIMATE_ZONE = new ContinuousUniverseOfDiscourse(-90, true,
            90, false);

    public static UniverseOfDiscourse GROUP_OF_CONTINENTS = new ContinuousUniverseOfDiscourse(-180, true,
            180, false);
    public static UniverseOfDiscourse DEPTH = new ContinuousUniverseOfDiscourse(-5, true,
            700, true);
    public static UniverseOfDiscourse MAGNITUDE = new ContinuousUniverseOfDiscourse(1, true,
            10, true);

    public static UniverseOfDiscourse GAP = new ContinuousUniverseOfDiscourse(0, true,
            360, true);

    public static UniverseOfDiscourse DMIN = new ContinuousUniverseOfDiscourse(0, true,
            105, true);

    public static UniverseOfDiscourse RMS = new ContinuousUniverseOfDiscourse(0, true,
            50, true);

    public static UniverseOfDiscourse D_ERROR = new ContinuousUniverseOfDiscourse(0, true,
            800, true);

    public static UniverseOfDiscourse MAGNITUDE_ERROR = new ContinuousUniverseOfDiscourse(0, true,
            5, true);

   public static UniverseOfDiscourse HORIZONTAL_ERROR = new ContinuousUniverseOfDiscourse(0, true,
            100, true);

   public static List<RelativeQuantifier> ADDED_RELATIVE_QUANTIFIERS = new ArrayList<>();
    public static List<AbsoluteQuantifier> ADDED_ABSOLUTE_QUANTIFIERS = new ArrayList<>();
   public static ArrayList<LinguisticVariable> ADDED_VARIABLES = new ArrayList<>();

   public static ArrayList<Double> WEIGHTS = new ArrayList<>
           (Arrays.asList(0.2,0.08,0.08,0.08,0.08,0.08,0.08,0.08,0.08,0.08,0.08));




    public static List<LinguisticVariable> initializeLinguisticVariables() {
        //Part of a day

        //night
        MembershipFunction night1Mf = new TrapezoidalFunction(0, 0, 180, 420);
        MembershipFunction night2Mf = new TrapezoidalFunction(1260, 1380, 1440, 1440);
        ArrayList<MembershipFunction> nightFunctions = new ArrayList<>(Arrays.asList(night1Mf, night2Mf));
        MembershipFunction nightMF = new CompoundFunction(nightFunctions);
        FuzzySet<Earthquake> nightFS = new FuzzySet<>(MINUTES, nightMF, AttributeExtractor::extractTime);
        LinguisticVariable night = new LinguisticVariable("Part of a day: night", nightFS);
        //Morning
        MembershipFunction morningMF = new TrapezoidalFunction(240, 360, 540, 660);
        FuzzySet<Earthquake> morningFS = new FuzzySet<>(MINUTES, morningMF, AttributeExtractor::extractTime);
        LinguisticVariable morning = new LinguisticVariable("Part of a day: morning", morningFS);
        //Midday
        MembershipFunction middayMF = new TrapezoidalFunction(540, 660, 780, 900);
        FuzzySet<Earthquake> middayFS = new FuzzySet<>(MINUTES, middayMF, AttributeExtractor::extractTime);
        LinguisticVariable midday = new LinguisticVariable("Part of a day: midday", middayFS);
        //Afternoon
        MembershipFunction afternoonMF = new TrapezoidalFunction(780, 900, 1020, 1140);
        FuzzySet<Earthquake> afternoonFS = new FuzzySet<>(MINUTES, afternoonMF, AttributeExtractor::extractTime);
        LinguisticVariable afternoon = new LinguisticVariable("Part of a day: afternoon", afternoonFS);
        //Evening
        MembershipFunction eveningMF = new TrapezoidalFunction(780, 900, 1020, 1140);
        FuzzySet<Earthquake> eveningFS = new FuzzySet<>(MINUTES, eveningMF, AttributeExtractor::extractTime);
        LinguisticVariable evening = new LinguisticVariable("Part of a day: evening", eveningFS);

        //Climate Zone

        //polar
        MembershipFunction polar1Mf = new TrapezoidalFunction(-90, -90, -70, -60);
        MembershipFunction polar2Mf = new TrapezoidalFunction(60, 70, 90, 90);
        ArrayList<MembershipFunction> polarFunctions = new ArrayList<>(Arrays.asList(polar1Mf, polar2Mf));
        MembershipFunction polarMF = new CompoundFunction(polarFunctions);
        FuzzySet<Earthquake> polarFS = new FuzzySet<>(CLIMATE_ZONE, polarMF, AttributeExtractor::extractLatitude);
        LinguisticVariable polar = new LinguisticVariable("Climate zone: polar zone", polarFS);
        //temperate
        MembershipFunction temperate1Mf = new TrapezoidalFunction(-67, -63, -50, -40);
        MembershipFunction temperate2Mf = new TrapezoidalFunction(40, 50, 63, 67);
        ArrayList<MembershipFunction> temperateFunctions = new ArrayList<>(Arrays.asList(temperate1Mf, temperate2Mf));
        MembershipFunction temperateMF = new CompoundFunction(temperateFunctions);
        FuzzySet<Earthquake> temperateFS = new FuzzySet<>(CLIMATE_ZONE, temperateMF, AttributeExtractor::extractLatitude);
        LinguisticVariable temperate = new LinguisticVariable("Climate zone: temperate zone", temperateFS);
        //subtropical
        MembershipFunction subtropical1Mf = new TrapezoidalFunction(-47, -43, -29, -25);
        MembershipFunction subtropical2Mf = new TrapezoidalFunction(25, 29, 43, 47);
        ArrayList<MembershipFunction> subtropicalFunctions = new ArrayList<>(Arrays.asList(subtropical1Mf, subtropical2Mf));
        MembershipFunction subtropicalMF = new CompoundFunction(subtropicalFunctions);
        FuzzySet<Earthquake> subtropicalFS = new FuzzySet<>(CLIMATE_ZONE, subtropicalMF, AttributeExtractor::extractLatitude);
        LinguisticVariable subtropical = new LinguisticVariable("Climate zone: subtropical zone", subtropicalFS);
        //Tropical
        MembershipFunction tropicalMF = new TrapezoidalFunction(-28, -26, 26, 28);
        FuzzySet<Earthquake> tropicalFS = new FuzzySet<>(CLIMATE_ZONE, tropicalMF, AttributeExtractor::extractLatitude);
        LinguisticVariable tropical = new LinguisticVariable("Climate zone: tropical zone", tropicalFS);

        //Group of continents

        //oceania
        MembershipFunction oceania1Mf = new TrapezoidalFunction(-180, -180, -170, -150);
        MembershipFunction oceania2Mf = new TrapezoidalFunction(150, 170, 180, 180);
        ArrayList<MembershipFunction> oceaniaFunctions = new ArrayList<>(Arrays.asList(oceania1Mf, oceania2Mf));
        MembershipFunction oceaniaMF = new CompoundFunction(oceaniaFunctions);
        FuzzySet<Earthquake> oceaniaFS = new FuzzySet<>(GROUP_OF_CONTINENTS, oceaniaMF, AttributeExtractor::extractLongitude);
        LinguisticVariable oceania = new LinguisticVariable("Group of continents: Oceania", oceaniaFS);
        //America
        MembershipFunction americaMF = new TrapezoidalFunction(-170, -150, -70, -30);
        FuzzySet<Earthquake> americaFS = new FuzzySet<>(GROUP_OF_CONTINENTS, americaMF, AttributeExtractor::extractLongitude);
        LinguisticVariable america = new LinguisticVariable("Group of continents: America", americaFS);
        //Africa and Europe
        MembershipFunction africaEuropeMF = new TrapezoidalFunction(-50, -30, 30, 50);
        FuzzySet<Earthquake> africaEuropeFS = new FuzzySet<>(GROUP_OF_CONTINENTS, africaEuropeMF, AttributeExtractor::extractLongitude);
        LinguisticVariable africaEurope = new LinguisticVariable("Group of continents: Africa and Europe", africaEuropeFS);
        //Asia
        MembershipFunction asiaMF = new TrapezoidalFunction(20, 40, 150, 170);
        FuzzySet<Earthquake> asiaFS = new FuzzySet<>(GROUP_OF_CONTINENTS, asiaMF, AttributeExtractor::extractLongitude);
        LinguisticVariable asia = new LinguisticVariable("Group of continents: Asia", asiaFS);

        //Depth

        //Shallow
        MembershipFunction shallowMF = new TrapezoidalFunction(-5, -5, 50, 100);
        FuzzySet<Earthquake> shallowFS = new FuzzySet<>(DEPTH, shallowMF, AttributeExtractor::extractDepth);
        LinguisticVariable shallow = new LinguisticVariable("Depth of an earthquake: shallow", shallowFS);
        //Intermediate
        MembershipFunction intermediateMF = new TrapezoidalFunction(50, 100, 230, 330);
        FuzzySet<Earthquake> intermediateFS = new FuzzySet<>(DEPTH, intermediateMF, AttributeExtractor::extractDepth);
        LinguisticVariable intermediate = new LinguisticVariable("Depth of an earthquake: intermediate", intermediateFS);
        //Deep
        MembershipFunction deepMF = new TrapezoidalFunction(260, 300, 700, 700);
        FuzzySet<Earthquake> deepFS = new FuzzySet<>(DEPTH, deepMF, AttributeExtractor::extractDepth);
        LinguisticVariable deep = new LinguisticVariable("Depth of an earthquake: deep", deepFS);

        //Magnitude

        //Micro
        MembershipFunction microMF = new TrapezoidalFunction(1, 1, 1.6, 2);
        FuzzySet<Earthquake> microFS = new FuzzySet<>(MAGNITUDE, microMF, AttributeExtractor::extractMag);
        LinguisticVariable micro = new LinguisticVariable("Earthquake’s magnitude: micro", microFS);
        //Minor
        MembershipFunction minorMF = new TrapezoidalFunction(1.6, 2, 3, 4);
        FuzzySet<Earthquake> minorFS = new FuzzySet<>(MAGNITUDE, minorMF, AttributeExtractor::extractMag);
        LinguisticVariable minor = new LinguisticVariable("Earthquake’s magnitude: minor", minorFS);
        //Light
        MembershipFunction lightMF = new TrapezoidalFunction(3.4, 3.6, 4.6, 5);
        FuzzySet<Earthquake> lightFS = new FuzzySet<>(MAGNITUDE, lightMF, AttributeExtractor::extractMag);
        LinguisticVariable light = new LinguisticVariable("Earthquake’s magnitude: light", lightFS);
        //Moderate
        MembershipFunction moderateMF = new TrapezoidalFunction(4.7, 4.9, 5.9, 6.1);
        FuzzySet<Earthquake> moderateFS = new FuzzySet<>(MAGNITUDE, moderateMF, AttributeExtractor::extractMag);
        LinguisticVariable moderate = new LinguisticVariable("Earthquake’s magnitude: moderate", moderateFS);
        //Strong
        MembershipFunction strongMF = new TrapezoidalFunction(5.8, 6.2, 7, 7.2);
        FuzzySet<Earthquake> strongFS = new FuzzySet<>(MAGNITUDE, strongMF, AttributeExtractor::extractMag);
        LinguisticVariable strong = new LinguisticVariable("Earthquake’s magnitude: strong", strongFS);
        //Major
        MembershipFunction majorMF = new TrapezoidalFunction(6.9, 7.3, 8, 8.2);
        FuzzySet<Earthquake> majorFS = new FuzzySet<>(MAGNITUDE, majorMF, AttributeExtractor::extractMag);
        LinguisticVariable major = new LinguisticVariable("Earthquake’s magnitude: major", majorFS);
        //Great
        MembershipFunction greatMF = new TrapezoidalFunction(7.9, 8.3, 10, 10);
        FuzzySet<Earthquake> greatFS = new FuzzySet<>(MAGNITUDE, greatMF, AttributeExtractor::extractMag);
        LinguisticVariable great = new LinguisticVariable("Earthquake’s magnitude: great", greatFS);

        //Gap

        //A
        MembershipFunction aCoverageMF = new TrapezoidalFunction(0, 0, 180, 200);
        FuzzySet<Earthquake> aCoverageFS = new FuzzySet<>(GAP, aCoverageMF, AttributeExtractor::extractGap);
        LinguisticVariable aCoverage = new LinguisticVariable("Groups of quality: A coverage group", aCoverageFS);
        //B
        MembershipFunction bCoverageMF = new TrapezoidalFunction(180, 200, 250, 270);
        FuzzySet<Earthquake> bCoverageFS = new FuzzySet<>(GAP, bCoverageMF, AttributeExtractor::extractGap);
        LinguisticVariable bCoverage = new LinguisticVariable("Groups of quality: B coverage group", bCoverageFS);
        //C
        MembershipFunction cCoverageMF = new TrapezoidalFunction(250, 270, 360, 360);
        FuzzySet<Earthquake> cCoverageFS = new FuzzySet<>(GAP, cCoverageMF, AttributeExtractor::extractGap);
        LinguisticVariable cCoverage = new LinguisticVariable("Groups of quality: C coverage group", cCoverageFS);

        //Dmin

        //Crust
        MembershipFunction crustMF = new TrapezoidalFunction(0, 0, 5, 15);
        FuzzySet<Earthquake> crustFS = new FuzzySet<>(DMIN, crustMF, AttributeExtractor::extractDmin);
        LinguisticVariable crust = new LinguisticVariable("Origin: Crust", crustFS);
        //Upper Mantle
        MembershipFunction upperMantleMF = new TrapezoidalFunction(9, 11, 19, 21);
        FuzzySet<Earthquake> upperMantleFS = new FuzzySet<>(DMIN, upperMantleMF, AttributeExtractor::extractDmin);
        LinguisticVariable upperMantle = new LinguisticVariable("Origin: Upper Mantle", upperMantleFS);
        //Transition Zone
        MembershipFunction transitionZoneMF = new TrapezoidalFunction(19, 21, 30, 34);
        FuzzySet<Earthquake> transitionZoneFS = new FuzzySet<>(DMIN, transitionZoneMF, AttributeExtractor::extractDmin);
        LinguisticVariable transitionZone = new LinguisticVariable("Origin: Transition Zone", transitionZoneFS);
        //Lower Mantle
        MembershipFunction lowerMantleMF = new TrapezoidalFunction(31, 33, 105, 105);
        FuzzySet<Earthquake> lowerMantleFS = new FuzzySet<>(DMIN, lowerMantleMF, AttributeExtractor::extractDmin);
        LinguisticVariable lowerMantle = new LinguisticVariable("Origin: Lower Mantle", lowerMantleFS);

        //Time travel residual

        //Low
        MembershipFunction lowMF = new GaussianFunction(1 / Math.sqrt(2 * Math.PI), 0);
        FuzzySet<Earthquake> lowFS = new FuzzySet<>(RMS, lowMF, AttributeExtractor::extractRMS);
        LinguisticVariable low = new LinguisticVariable("Time travel residual: low", lowFS);
        //Average
        MembershipFunction averageMF = new GaussianFunction(1 / Math.sqrt(2 * Math.PI), 0.9);
        FuzzySet<Earthquake> averageFS = new FuzzySet<>(RMS, averageMF, AttributeExtractor::extractRMS);
        LinguisticVariable average = new LinguisticVariable("Time travel residual: average", averageFS);
        //Large
        MembershipFunction largeMF = new GaussianFunction(1 / Math.sqrt(2 * Math.PI), 1.8);
        FuzzySet<Earthquake> largeFS = new FuzzySet<>(RMS, largeMF, AttributeExtractor::extractRMS);
        LinguisticVariable large = new LinguisticVariable("Time travel residual: large", largeFS);
        //Gross
        MembershipFunction grossMF = new TrapezoidalFunction(2.15, 2.35, 50, 50);
        FuzzySet<Earthquake> grossFS = new FuzzySet<>(RMS, grossMF, AttributeExtractor::extractRMS);
        LinguisticVariable gross = new LinguisticVariable("Time travel residual: gross", grossFS);

        //Depth error

        //Trivial
        MembershipFunction trivialMF = new TrapezoidalFunction(0, 0, 9, 11);
        FuzzySet<Earthquake> trivialFS = new FuzzySet<>(D_ERROR, trivialMF, AttributeExtractor::extractDepthError);
        LinguisticVariable trivial = new LinguisticVariable("Depth error: trivial", trivialFS);
        //Reasonable
        MembershipFunction reasonableMF = new TrapezoidalFunction(8, 12, 20, 22);
        FuzzySet<Earthquake> reasonableFS = new FuzzySet<>(D_ERROR, reasonableMF, AttributeExtractor::extractDepthError);
        LinguisticVariable reasonable = new LinguisticVariable("Depth error: reasonable", reasonableFS);
        //Important
        MembershipFunction importantMF = new TrapezoidalFunction(19, 23, 60, 62);
        FuzzySet<Earthquake> importantFS = new FuzzySet<>(D_ERROR, importantMF, AttributeExtractor::extractDepthError);
        LinguisticVariable important = new LinguisticVariable("Depth error: important", importantFS);
        //Huge
        MembershipFunction hugeMF = new TrapezoidalFunction(60, 62, 800, 800);
        FuzzySet<Earthquake> hugeFS = new FuzzySet<>(D_ERROR, hugeMF, AttributeExtractor::extractDepthError);
        LinguisticVariable huge = new LinguisticVariable("Depth error: huge", hugeFS);

        //Horizontal error

        //Small
        MembershipFunction smallMF = new TrapezoidalFunction(0, 0, 1, 2);
        FuzzySet<Earthquake> smallFS = new FuzzySet<>(HORIZONTAL_ERROR, smallMF, AttributeExtractor::extractHorizontalError);
        LinguisticVariable small = new LinguisticVariable("Horizontal error: small", smallFS);
        //Normal
        MembershipFunction normalMF = new TrapezoidalFunction(1, 2, 9, 14);
        FuzzySet<Earthquake> normalFS = new FuzzySet<>(HORIZONTAL_ERROR, normalMF, AttributeExtractor::extractHorizontalError);
        LinguisticVariable normal = new LinguisticVariable("Horizontal error: normal", normalFS);
        //Significant
        MembershipFunction significantMF = new TrapezoidalFunction(11, 12, 20, 22);
        FuzzySet<Earthquake> significantFS = new FuzzySet<>(HORIZONTAL_ERROR, significantMF, AttributeExtractor::extractHorizontalError);
        LinguisticVariable significant = new LinguisticVariable("Horizontal error: significant", significantFS);
        //Critical
        MembershipFunction criticalMF = new TrapezoidalFunction(20, 22, 100, 100);
        FuzzySet<Earthquake> criticalFS = new FuzzySet<>(HORIZONTAL_ERROR, criticalMF, AttributeExtractor::extractHorizontalError);
        LinguisticVariable critical = new LinguisticVariable("Horizontal error: critical", criticalFS);

        //Magnitude error

        //Minimal
        MembershipFunction minimalMF = new GaussianFunction(1 / Math.sqrt(2 * Math.PI), 0);
        FuzzySet<Earthquake> minimalFS = new FuzzySet<>(MAGNITUDE_ERROR, minimalMF, AttributeExtractor::extractMagError);
        LinguisticVariable minimal = new LinguisticVariable("Magnitude error: minimal", minimalFS);
        //Mean
        MembershipFunction meanMF = new GaussianFunction(1 / Math.sqrt(2 * Math.PI), 0.9);
        FuzzySet<Earthquake> meanFS = new FuzzySet<>(MAGNITUDE_ERROR, meanMF, AttributeExtractor::extractMagError);
        LinguisticVariable mean = new LinguisticVariable("Magnitude error: mean", meanFS);
        //Ample
        MembershipFunction ampleMF = new GaussianFunction(1 / Math.sqrt(2 * Math.PI), 1.8);
        FuzzySet<Earthquake> ampleFS = new FuzzySet<>(MAGNITUDE_ERROR, ampleMF, AttributeExtractor::extractMagError);
        LinguisticVariable ample = new LinguisticVariable("Magnitude error: ample", ampleFS);
        //Massive
        MembershipFunction massiveMF = new TrapezoidalFunction(2.1, 2.3, 5, 5);
        FuzzySet<Earthquake> massiveFS = new FuzzySet<>(MAGNITUDE_ERROR, massiveMF, AttributeExtractor::extractMagError);
        LinguisticVariable massive = new LinguisticVariable("Magnitude error: massive", massiveFS);


        return new ArrayList<>(Arrays.asList(night, morning, midday, afternoon, evening, polar, temperate, subtropical,
                tropical, oceania, america, africaEurope, asia, shallow, intermediate, deep, micro, minor, light,
                moderate, strong, major, great, aCoverage, bCoverage, cCoverage, crust, upperMantle, transitionZone,
                lowerMantle, low, average, large, gross, trivial, reasonable, important, huge, small, normal, significant,
                critical, minimal, mean, ample, massive));

    }

    public static List<RelativeQuantifier> initializeRelativeQualifiers() {
         //Almost None
        MembershipFunction almostNoneMF = new TrapezoidalFunction(0, 0, 0.14, 0.18);
        FuzzySet<Double> almostNoneFS = new FuzzySet<>(RELATIVE, almostNoneMF, x -> x);
        RelativeQuantifier almostNone = new RelativeQuantifier("Almost none", almostNoneFS);
        //Some
        MembershipFunction someMF = new TrapezoidalFunction(0.14, 0.18, 0.33, 0.37);
        FuzzySet<Double> someFS = new FuzzySet<>(RELATIVE, someMF, x -> x);
        RelativeQuantifier some = new RelativeQuantifier("Some", someFS);
        //About half
        MembershipFunction aboutHalfMF = new TrapezoidalFunction(0.33, 0.37, 0.52, 0.56);
        FuzzySet<Double> aboutHalfFS = new FuzzySet<>(RELATIVE, aboutHalfMF, x -> x);
        RelativeQuantifier aboutHalf = new RelativeQuantifier("About half", aboutHalfFS);
        //Over half
        MembershipFunction overHalfMF = new TrapezoidalFunction(0.52, 0.56, 0.71, 0.75);
        FuzzySet<Double> overHalfFS = new FuzzySet<>(RELATIVE, overHalfMF, x -> x);
        RelativeQuantifier overHalf = new RelativeQuantifier("Over half", overHalfFS);
        //Over three quaters
        MembershipFunction overThreeQuatersMF = new TrapezoidalFunction(0.71, 0.75, 0.90, 0.94);
        FuzzySet<Double> overThreeQuatersFS = new FuzzySet<>(RELATIVE, overThreeQuatersMF, x -> x);
        RelativeQuantifier overThreeQuaters = new RelativeQuantifier("Over three quaters", overThreeQuatersFS);
        //Almost Every
        MembershipFunction almostEveryMF = new TrapezoidalFunction(0.90, 0.94, 1, 1);
        FuzzySet<Double> almostEveryFS = new FuzzySet<>(RELATIVE, almostEveryMF, x -> x);
        RelativeQuantifier almostEvery = new RelativeQuantifier("Almost Every", almostEveryFS);

        return new ArrayList<>(Arrays.asList(almostNone, some, aboutHalf, overHalf, overThreeQuaters, almostEvery));
    }
    public static List<AbsoluteQuantifier> initializeAbsoluteQualifiers() {
        //Less than 200
        MembershipFunction lessthan200MF = new TrapezoidalFunction(0, 0, 200, 300);
        FuzzySet<Double> lessthan200FS = new FuzzySet<>(ABSOLUTE, lessthan200MF, x -> x);
        AbsoluteQuantifier lessthan200 = new AbsoluteQuantifier("Less than 200", lessthan200FS);
        //Less than 700
        MembershipFunction lessthan700MF = new TrapezoidalFunction(200, 300, 700, 900);
        FuzzySet<Double> lessthan700FS = new FuzzySet<>(ABSOLUTE, lessthan700MF, x -> x);
        AbsoluteQuantifier lessthan700 = new AbsoluteQuantifier("Less than 700", lessthan700FS);
        //About 1000
        MembershipFunction about1000MF = new TrapezoidalFunction(600, 1000, 1800, 2200);
        FuzzySet<Double> about1000FS = new FuzzySet<>(ABSOLUTE, about1000MF, x -> x);
        AbsoluteQuantifier about1000 = new AbsoluteQuantifier("About 1000", about1000FS);
        //About 2000
        MembershipFunction about2000MF = new TrapezoidalFunction(1900, 2100, 2700, 3100);
        FuzzySet<Double> about2000FS = new FuzzySet<>(ABSOLUTE, about2000MF, x -> x);
        AbsoluteQuantifier about2000 = new AbsoluteQuantifier("About 2000", about2000FS);
        //More than 3000
        MembershipFunction morethan3000MF = new TrapezoidalFunction(2800, 3000, 5200, 5600);
        FuzzySet<Double> morethan3000FS = new FuzzySet<>(ABSOLUTE, morethan3000MF, x -> x);
        AbsoluteQuantifier morethan3000 = new AbsoluteQuantifier("More than 3000", morethan3000FS);
        //Above 6000
        MembershipFunction above6000MF = new TrapezoidalFunction(5200, 5600, 15000, 15000);
        FuzzySet<Double> above6000FS = new FuzzySet<>(ABSOLUTE, above6000MF, x -> x);
        AbsoluteQuantifier above6000 = new AbsoluteQuantifier("Above 6000", above6000FS);

        return new ArrayList<>(Arrays.asList(lessthan200, lessthan700, about1000, about2000, morethan3000, above6000));
    }

    public static ArrayList<String> listOfAttributes() {
        ArrayList<String> variables = new ArrayList<>();
        variables.add("time");
        variables.add("latitude");
        variables.add("longitude");
        variables.add("depth");
        variables.add("mag");
        variables.add("gap");
        variables.add("dmin");
        variables.add("RMS");
        variables.add("horizontalError");
        variables.add("depthError");
        variables.add("magnitudeError");
        return variables;
    }
}
