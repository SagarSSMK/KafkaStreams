package model.aggregation.AggOnKTable;


import java.util.Objects;

public class Region {
    private String region;

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public static Builder Builder(){
        return new Builder();
    }
    public Region(Builder builder) {
        this.region=builder.region;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Region)) return false;
        Region region1 = (Region) o;
        return Objects.equals(getRegion(), region1.getRegion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRegion());
    }

    @Override
    public String toString() {
        return "Region{" +
                "region='" + region + '\'' +
                '}';
    }

    public static final class Builder{
        private String region;

        public Builder(){}

        public  Builder(Region key){
            this.region=key.region;

        }
        public Builder region(String val){
            this.region=val;
            return this;
        }

        public  Region build(){
            return  new Region(this);
        }
    }
}
