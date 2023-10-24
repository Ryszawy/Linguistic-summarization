package pl.ksr.logic.function;

import java.io.Serializable;
import java.util.ArrayList;

public class CompoundFunction implements MembershipFunction, Serializable {
    private ArrayList<MembershipFunction> functions = new ArrayList<>();

    public CompoundFunction(ArrayList<MembershipFunction> functions) {
        this.functions = functions;
    }

    public ArrayList<MembershipFunction> getFunctions() {
        return functions;
    }

    @Override
    public double compute(double x) {
        double max = 0;
       for (MembershipFunction function: functions) {
           if (function.compute(x) > max) {
               max = function.compute(x);
           }
       }
       return max;
    }

    @Override
    public double calculateArea() {
        double sum = 0;
        for (MembershipFunction function: functions) {
           sum += function.calculateArea();
            }
        return sum;
        }

    @Override
    public double getMinArg() {
        double min = functions.get(0).getMinArg();
        for (int i = 1; i< functions.size(); i++) {
            if (functions.get(i).getMinArg()< min) {
                min = functions.get(i).getMinArg();
            }
        }
        return min;
    }

    @Override
    public double getMaxArg() {
        double max = functions.get(0).getMaxArg();
        for (int i = 1; i< functions.size(); i++) {
            if (functions.get(i).getMaxArg()> max) {
                max = functions.get(i).getMaxArg();
            }
        }
        return max;
    }

}
