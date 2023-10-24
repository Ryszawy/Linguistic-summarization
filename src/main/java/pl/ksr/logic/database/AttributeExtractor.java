package pl.ksr.logic.database;

import java.time.LocalDateTime;

public class AttributeExtractor {
    public static Double extractTime(Earthquake earthquake) {
        LocalDateTime dateTime = earthquake.getTime();
        return (double) (dateTime.getHour() * 60 + dateTime.getMinute());
    }
    public static Double extractLatitude(Earthquake earthquake) {
        return earthquake.getLatitude();
    }
    public static Double extractLongitude(Earthquake earthquake) {return earthquake.getLongitude();}
    public static Double extractDepth(Earthquake earthquake) {return earthquake.getDepth();}
    public static Double extractMag(Earthquake earthquake) {return earthquake.getMag();}
    public static Double extractGap(Earthquake earthquake) {return earthquake.getGap();}
    public static Double extractDmin(Earthquake earthquake) {return earthquake.getDmin();}
    public static Double extractRMS(Earthquake earthquake) {return earthquake.getRms();}
    public static Double extractHorizontalError(Earthquake earthquake) {return earthquake.getHorizontalError();}
    public static Double extractDepthError(Earthquake earthquake) {return earthquake.getDepthError();}
    public static Double extractMagError(Earthquake earthquake) {return earthquake.getMagError();}

}
