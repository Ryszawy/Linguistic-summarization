package pl.ksr.logic.function;

import java.io.Serializable;

public interface MembershipFunction {

    double compute(double x);

    double calculateArea();

    double getMinArg();
    double getMaxArg();
}
