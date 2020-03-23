package model.aggregation.AggOnKTable;

public class AvgUnitsKey {
    private String avgUnitCost;
 //   private String avgUnitPrice;
  //  private String avgUnitsSold;

    @Override
    public String toString() {
        return "AvgUnitsKey{" +
                "avgUnitCost='" + avgUnitCost + '\'' +
   //             ", avgUnitPrice='" + avgUnitPrice + '\'' +
 //               ", avgUnitsSold='" + avgUnitsSold + '\'' +
                '}';
    }

    public String getAvgUnitCost() {
        return avgUnitCost;
    }

    public void setAvgUnitCost(String avgUnitCost) {
        this.avgUnitCost = avgUnitCost;
    }

  /*  public String getAvgUnitPrice() {
        return avgUnitPrice;
    }

    public void setAvgUnitPrice(String avgUnitPrice) {
        this.avgUnitPrice = avgUnitPrice;
    }
*/
/*    public String getAvgUnitsSold() {
        return avgUnitsSold;
    }

    public void setAvgUnitsSold(String avgUnitsSold) {
        this.avgUnitsSold = avgUnitsSold;
    }*/

    public static Builder Builder(){
        return new Builder();
    }
    public AvgUnitsKey(Builder builder) {
        this.avgUnitCost=builder.avgUnitCost;
     //   this.avgUnitPrice=builder.avgUnitPrice;
       // this.avgUnitsSold=builder.avgUnitsSold;
    }

    public static final class Builder{
        private String avgUnitCost;
      //  private String avgUnitPrice;
      //  private String avgUnitsSold;

        public Builder(){}

        public  Builder(AvgUnitsKey key){
            this.avgUnitCost=key.avgUnitCost;
        //    this.avgUnitPrice=key.avgUnitPrice;
          //  this.avgUnitsSold=key.avgUnitsSold;
        }
        public Builder avgUnitCost(String val){
            this.avgUnitCost=avgUnitCost;
            return this;
        }

  /*      public Builder avgUnitPrice(String val){
            this.avgUnitPrice=avgUnitPrice;
            return this;
        }
*/
       /* public Builder avgUnitsSold(String val){
            this.avgUnitsSold=avgUnitsSold;
            return this;
        }*/

        public  AvgUnitsKey build(){
            return  new AvgUnitsKey(this);
        }
    }
}
