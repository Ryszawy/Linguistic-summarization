package pl.ksr.logic.function;

import java.io.Serializable;

public class TrapezoidalFunction implements MembershipFunction, Serializable {
    double x1;
    double x2;
    double x3;
    double x4;

    public TrapezoidalFunction(double x1, double x2, double x3, double x4) {
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.x4 = x4;
    }

    @Override
    public double compute(double x) {
        if (x >= x1 && x <= x2) {
            if (x1 == x2) {
                return 1;
            }
            double a = 1 / (x2 - x1);
            double b = x1 / (x1 - x2);
            return a * x + b;
        } else if (x >= x2 && x <= x3) {
            return 1.0;
        } else if (x >= x3 && x <= x4) {
            if (x3 == x4) {
                return 1;
            }
            double a = 1 / (x3 - x4);
            double b = x4 / (x4 - x3);
            return a * x + b;
        } else {
            return 0.0;
        }
    }

    @Override
    public double calculateArea() {
        return (Math.abs(x4-x1)+Math.abs(x3-x2))/2;
    }

    @Override
    public double getMinArg() {
        return x1;
    }

    @Override
    public double getMaxArg() {
        return x4;
    }
}
