package pl.ksr.logic.function;

import java.io.Serializable;

public class TriangularFunction implements MembershipFunction, Serializable {
    private double x1;
    private double x2;
    private double x3;

    public TriangularFunction(double x1, double x2, double x3) {
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
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
            if (x2 == x1) {
                return 1;
            }
            double a = 1 / (x2 - x3);
            double b = x3 / (x3 - x2);
            return a * x + b;
        } else {
            return 0.0;
        }
    }

    @Override
    public double calculateArea() {
        return Math.abs(x3-x1) / 2.0;
    }

    @Override
    public double getMinArg() {
        return x1;
    }

    @Override
    public double getMaxArg() {
        return x3;
    }
}
