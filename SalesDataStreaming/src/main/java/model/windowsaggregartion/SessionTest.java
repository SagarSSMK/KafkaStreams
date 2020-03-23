package model.windowsaggregartion;


public class SessionTest {

    private String region;
    private long timeInMs;
    private int value;

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public long getTimeInMs() {
        return timeInMs;
    }

    public void setTimeInMs(long timeInMs) {
        this.timeInMs = timeInMs;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public SessionTest(){ }

    @Override
    public String toString() {
        return "SessionTest{" +
                "region='" + region + '\'' +
                ", timeInMs=" + timeInMs +
                ", value=" + value +
                '}';
    }

    public static Builder Builder(){
        return new Builder();
    }

    public SessionTest(Builder builder) {
        this.region=builder.region;
        this.timeInMs=builder.timeInMs;
        this.value=builder.value;
    }

    public static final class Builder{
        private String region;
        private long timeInMs;
        private int value;

        public Builder(){}

        public Builder(SessionTest sessionAggregation){
            this.region=sessionAggregation.region;
            this.timeInMs=sessionAggregation.timeInMs;
            this.value=sessionAggregation.value;
        }
        public Builder region(String val){
            this.region=val;
            return this;
        }

        public Builder timeInMs(long val){
            this.timeInMs=val;
            return this;
        }

        public Builder value(int val){
            this.value=val;
            return this;
        }

        public SessionTest build(){
            return new SessionTest(this);
        }
    }
}
