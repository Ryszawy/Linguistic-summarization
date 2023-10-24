package pl.ksr.logic.function;

import java.io.Serializable;

public class GaussianFunction implements MembershipFunction, Serializable {
    private double sigma;
    private double mi;

    public GaussianFunction(double sigma, double mi) {
        this.sigma = sigma;
        this.mi = mi;
    }

    @Override
    public double compute(double x) {
        return Math.exp(Math.pow((x - mi) / sigma, 2.0) / (-2.0));
    }

    @Override
    public double calculateArea() {
        double leftSideLimit = compute(getMinArg());
        double rightSideLimit = compute(getMaxArg());
        double area = 0.0;
        final int rectangles = 100000;
        final double width = (leftSideLimit - rightSideLimit) / rectangles;
        for (int i = 0; i < rectangles; i++) {
            final int j = i;
            area += width * compute(Double.valueOf(width * j + leftSideLimit));
        }
        return area;
    }

    @Override
    public double getMinArg() {
        double x = 0;
        while (compute(x) == 0) {
            x += 0.01;

        }
        return x;
    }

    @Override
    public double getMaxArg() {
       double x = getMinArg();
        while (compute(x) > 0) {
            x += 0.01;

        }
        return x;
    }
}
