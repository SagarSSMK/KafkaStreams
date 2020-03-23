package model.windowsaggregartion;

public class SessionAggregation {

   // private long timeInMs;
    private int count;

    public SessionAggregation(){}

    public SessionAggregation(Builder builder) {
     //   this.timeInMs=builder.timeInMs;
        this.count=builder.count;
    }

 /*   public long getTimeInMs() {
        return timeInMs;
    }

    public void setTimeInMs(long timeInMs) {
        this.timeInMs = timeInMs;
    }*/

    @Override
    public String toString() {
        return "SessionAggregation{" +
                "count=" + count +
                '}';
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public static SessionTest.Builder Builder(){
        return new SessionTest.Builder();
    }

    public SessionAggregation add(SessionTest test){
        this.count=this.count+test.getValue();
        return this;
    }


    public SessionAggregation addAgg(SessionAggregation sessionAggregation){
        this.count=this.count+sessionAggregation.count;
        return this;
    }

    public static final class Builder{
     //   private long timeInMs;
        private int count;

        public Builder(SessionAggregation sessionAggregation){
          //  this.timeInMs=sessionAggregation.timeInMs;
            this.count=sessionAggregation.count;
        }

   /*     public Builder timeInMs(long val){
        //    this.timeInMs=val;
            return this;
        }*/

        public Builder count(int val){
            this.count=val;
            return this;
        }

        public SessionAggregation build(){
            return new SessionAggregation(this);
        }

    }
}
