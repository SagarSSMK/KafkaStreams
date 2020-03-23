package model.aggregation.AggOnKTable;

import model.aggregation.avg.MappedData;

public class AvgUnitsValue {
    private double sumTotalRevenue;
    private double sumTotalCost;
    private double sumTotalProfit;

    @Override
    public String toString() {
        return "AvgUnitsValue{" +
                "sumTotalRevenue=" + sumTotalRevenue +
                ", sumTotalCost=" + sumTotalCost +
                ", sumTotalProfit=" + sumTotalProfit +
                '}';
    }

    public double getSumTotalRevenue() {
        return sumTotalRevenue;
    }

    public void setSumTotalRevenue(double sumTotalRevenue) {
        this.sumTotalRevenue = sumTotalRevenue;
    }

    public double getSumTotalCost() {
        return sumTotalCost;
    }

    public void setSumTotalCost(double sumTotalCost) {
        this.sumTotalCost = sumTotalCost;
    }

    public double getSumTotalProfit() {
        return sumTotalProfit;
    }

    public void setSumTotalProfit(double sumTotalProfit) {
        this.sumTotalProfit = sumTotalProfit;
    }

    public AvgUnitsValue aggregate(MappedData mappedData){
        this.sumTotalRevenue=+mappedData.getAvgTotalRevenue();
        this.sumTotalCost=+mappedData.getAvgTotalCost();
        this.sumTotalProfit=+mappedData.getAvgTotalProfit();
        return new Builder(this).build();

    }

    public AvgUnitsValue subtract(MappedData mappedData){

        this.sumTotalRevenue=-mappedData.getAvgTotalRevenue();
        this.sumTotalCost=-mappedData.getAvgTotalCost();
        this.sumTotalProfit=-mappedData.getAvgTotalProfit();
        return new Builder(this).build();
    }

    public AvgUnitsValue(){}

    public AvgUnitsValue(Builder builder) {
        this.sumTotalRevenue=builder.sumTotalRevenue;
        this.sumTotalCost=builder.sumTotalCost;
        this.sumTotalProfit=builder.sumTotalProfit;
    }

    public static final class Builder{
        private double sumTotalRevenue;
        private double sumTotalCost;
        private double sumTotalProfit;

        public Builder(){}
        public  Builder(AvgUnitsValue value){
            this.sumTotalRevenue=value.sumTotalRevenue;
            this.sumTotalCost=value.sumTotalCost;
            this.sumTotalProfit=value.sumTotalProfit;

        }
        public Builder sumTotalRevenue(double val){
            this.sumTotalRevenue=val;
            return  this;
        }
        public Builder sumTotalCost(double val){
            this.sumTotalCost=val;
            return  this;
        }
        public Builder sumTotalProfit(double val){
            this.sumTotalProfit=val;
            return  this;
        }

        public  AvgUnitsValue build(){
            return new AvgUnitsValue(this);
        }
    }
}
