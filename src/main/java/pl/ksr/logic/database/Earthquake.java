package pl.ksr.logic.database;

import java.time.LocalDateTime;

public class Earthquake {

    private LocalDateTime time;
    private Double latitude;
    private Double longitude;
    private Double depth;
    private Double mag;
    private Double gap;
    private Double dmin;
    private Double rms;
    private String type;
    private Double horizontalError;
    private Double depthError;
    private Double magError;


    public static Builder builder = new Builder();

    public LocalDateTime getTime() {
        return time;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getDepth() {
        return depth;
    }

    public Double getMag() {
        return mag;
    }

    public Double getGap() {
        return gap;
    }

    public Double getDmin() {
        return dmin;
    }

    public Double getRms() {
        return rms;
    }

    public String getType() {
        return type;
    }

    public Double getHorizontalError() {
        return horizontalError;
    }

    public Double getDepthError() {
        return depthError;
    }

    public Double getMagError() {
        return magError;
    }

    public static Builder getBuilder() {
        return builder;
    }

    @Override
    public String toString() {
        return "Earthquake{" +
                "time=" + time +
                ", latitude=" + latitude +
                ", longtitude=" + longitude +
                ", depth=" + depth +
                ", mag=" + mag +
                ", gap=" + gap +
                ", dmin=" + dmin +
                ", rms=" + rms +
                ", type='" + type + '\'' +
                ", horizontalError=" + horizontalError +
                ", depthError=" + depthError +
                ", magError=" + magError +
                '}';
    }

    public static final class Builder {
        private LocalDateTime time;
        private Double latitude;
        private Double longitude;
        private Double depth;
        private Double mag;
        private Double gap;
        private Double dmin;
        private Double rms;
        private String type;
        private Double horizontalError;
        private Double depthError;
        private Double magError;

        public Earthquake build() {
            Earthquake earthquake = new Earthquake();
            earthquake.time = this.time;
            earthquake.latitude = this.latitude;
            earthquake.longitude = this.longitude;
            earthquake.depth = this.depth;
            earthquake.mag = this.mag;
            earthquake.gap = this.gap;
            earthquake.dmin = this.dmin;
            earthquake.rms = this.rms;
            earthquake.type = this.type;
            earthquake.horizontalError = this.horizontalError;
            earthquake.depthError = this.depthError;
            earthquake.magError = this.magError;
            return earthquake;
        }

        public Builder setTime(LocalDateTime time) {
            this.time = time;
            return this;
        }

        public Builder setLatitude(Double latitude) {
            this.latitude = latitude;
            return this;
        }

        public Builder setLongitude(Double longitude) {
            this.longitude = longitude;
            return this;
        }

        public Builder setDepth(Double depth) {
            this.depth = depth;
            return this;
        }

        public Builder setMag(Double mag) {
            this.mag = mag;
            return this;
        }

        public Builder setGap(Double gap) {
            this.gap = gap;
            return this;
        }

        public Builder setDmin(Double dmin) {
            this.dmin = dmin;
            return this;
        }

        public Builder setRms(Double rms) {
            this.rms = rms;
            return this;
        }

        public Builder setType(String type) {
            if (type.equals("mb")) {
                this.type = "Short-Period";
            } else {
                this.type = "Local";
            }
            return this;
        }

        public Builder setHorizontalError(Double horizontalError) {
            this.horizontalError = horizontalError;
            return this;
        }

        public Builder setDepthError(Double depthError) {
            this.depthError = depthError;
            return this;
        }

        public Builder setMagError(Double magError) {
            this.magError = magError;
            return this;
        }
    }


}
