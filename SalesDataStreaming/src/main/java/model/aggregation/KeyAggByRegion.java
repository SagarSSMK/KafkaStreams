package model.aggregation;

import model.Sales;
import org.apache.hadoop.yarn.webapp.hamlet.HamletSpec;

public class KeyAggByRegion {

    private String region;

    @Override
    public String toString() {
        return "KeyAggByRegion{" +
                "region='" + region + '\'' +
                '}';
    }

    public KeyAggByRegion(Builder builder){
        this.region=builder.region;
    }

    public static Builder newBuilder(){
        return new Builder();
    }

    public static Builder builder(KeyAggByRegion keyAggByRegion){
        return new Builder(keyAggByRegion);
    }

    public static final class Builder{
        private String region;

        public Builder(KeyAggByRegion keyAggByRegion){
            this.region=keyAggByRegion.region;
        }
        public Builder(){}

        public Builder region(String region){
            this.region=region;
            return this;
        }

        public KeyAggByRegion build(){
            return new KeyAggByRegion(this);
        }
    }


}
