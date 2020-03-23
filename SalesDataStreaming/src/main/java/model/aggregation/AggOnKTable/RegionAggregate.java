package model.aggregation.AggOnKTable;

import model.Sales;

public class RegionAggregate {


/*    private double sumUnitsSold;
    private double sumUnitPrice;
    private double sumUnitCost;
    private double sumTotalRevenue;
    private double sumTotalCost;*/
    private double sumTotalProfit;

    public RegionAggregate(Builder builder) {
        this.sumTotalProfit=builder.sumTotalProfit;
    }

    public RegionAggregate add(Sales s){
        this.sumTotalProfit =this.sumTotalProfit+s.getTotalProfit();
        return this;
    }

    public RegionAggregate sub(Sales s){
        this.sumTotalProfit=this.sumTotalProfit-s.getTotalProfit();
        return this;
    }

    public RegionAggregate(){}

    @Override
    public String toString() {
        return "RegionAggregate{" +
                "sumTotalProfit=" + sumTotalProfit +
                '}';
    }

    public static final class Builder{
        private double sumTotalProfit;

        public Builder(RegionAggregate aggregate){

        }
        public Builder sumTotalProfit(double sum){
            this.sumTotalProfit=sum;
            return this;
        }

        public RegionAggregate build(){
            return new RegionAggregate(this);
        }
    }
}
