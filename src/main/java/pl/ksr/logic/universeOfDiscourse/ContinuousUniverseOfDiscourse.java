package pl.ksr.logic.universeOfDiscourse;

import java.io.Serializable;

public class ContinuousUniverseOfDiscourse implements UniverseOfDiscourse, Serializable {
    private final double leftSideLimit;
    private final boolean isLeftSideClosed;
    private final double rightSideLimit;
    private final boolean isRightSideClosed;

    public ContinuousUniverseOfDiscourse(double leftSideLimit, boolean isLeftSideClosed,
                                         double rightSideLimit, boolean isRightSideClosed) {
        this.leftSideLimit = leftSideLimit;
        this.isLeftSideClosed = isLeftSideClosed;
        this.rightSideLimit = rightSideLimit;
        this.isRightSideClosed = isRightSideClosed;
    }

    public double getLeftSideLimit() {
        return leftSideLimit;
    }

    public boolean getLeftSideClosed() {
        return isLeftSideClosed;
    }

    public double getRightSideLimit() {
        return rightSideLimit;
    }

    public boolean getRightSideClosed() {
        return isRightSideClosed;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (isLeftSideClosed) {
            stringBuilder.append("[");
        } else {
            stringBuilder.append("(");
        }
        stringBuilder.append(leftSideLimit);
        stringBuilder.append(", ");
        stringBuilder.append(rightSideLimit);
        if (isRightSideClosed) {
            stringBuilder.append("]");
        } else {
            stringBuilder.append(")");
        }
        return stringBuilder.toString();
    }

    @Override
    public double size() {
        return Math.abs(rightSideLimit - leftSideLimit);
    }

    @Override
    public boolean inUniverse(double x) {
        if (rightSideLimit == x  && isRightSideClosed) {
            return true;
        }
        if (leftSideLimit == x  && isLeftSideClosed) {
            return true;
        }
        if (x>=leftSideLimit && x<= rightSideLimit) {
            return true;
        }
        if (x<=leftSideLimit && x>= rightSideLimit) {
            return true;
        }
        return false;
    }

    @Override
    public double getMinimum() {
        return leftSideLimit;
    }

    @Override
    public double getMaximum() {
        return rightSideLimit;
    }
}
