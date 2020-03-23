package model.aggregation.avg;

import config.Configuration;
import model.aggregation.HigherAndLowereResults;

public class MappedData {

    private String avgUnitsSold;
    private String avgUnitCost;
    private String avgUnitPrice;
    private double avgTotalRevenue;
    private double avgTotalCost;
    private double avgTotalProfit;


    public String getAvgUnitsSold() {
        return avgUnitsSold;
    }

    public void setAvgUnitsSold(String avgUnitsSold) {
        this.avgUnitsSold = avgUnitsSold;
    }

    public String getAvgUnitCost() {
        return avgUnitCost;
    }

    public void setAvgUnitCost(String avgUnitCost) {
        this.avgUnitCost = avgUnitCost;
    }

    public String getAvgUnitPrice() {
        return avgUnitPrice;
    }

    public void setAvgUnitPrice(String avgUnitPrice) {
        this.avgUnitPrice = avgUnitPrice;
    }

    public double getAvgTotalRevenue() {
        return avgTotalRevenue;
    }

    public void setAvgTotalRevenue(double avgTotalRevenue) {
        this.avgTotalRevenue = avgTotalRevenue;
    }

    public double getAvgTotalCost() {
        return avgTotalCost;
    }

    public void setAvgTotalCost(double avgTotalCost) {
        this.avgTotalCost = avgTotalCost;
    }

    public double getAvgTotalProfit() {
        return avgTotalProfit;
    }

    public void setAvgTotalProfit(double avgTotalProfit) {
        this.avgTotalProfit = avgTotalProfit;
    }

    @Override
    public String toString() {
        return "MappedData{" +
                "avgUnitsSold='" + avgUnitsSold + '\'' +
                ", avgUnitCost='" + avgUnitCost + '\'' +
                ", avgUnitPrice='" + avgUnitPrice + '\'' +
                ", avgTotalRevenue='" + avgTotalRevenue + '\'' +
                ", avgTotalCost='" + avgTotalCost + '\'' +
                ", avgTotalProfit='" + avgTotalProfit + '\'' +
                '}';
    }


    public MappedData(){}

    public MappedData getMapped(HigherAndLowereResults mappedData){



        if(mappedData.getAvgUnitPrice()< Configuration.LEAST_NUMBER_NUMERICAL)
            this.setAvgUnitPrice(Configuration.LEAST_NUMBERS);
        if(mappedData.getAvgUnitPrice()>Configuration.LEAST_NUMBER_NUMERICAL && mappedData.getAvgUnitPrice()<Configuration.MODERATE_NUMBER_NUMERICAL)
            this.setAvgUnitPrice(Configuration.MODERATE_NUMBERS);
        if(mappedData.getAvgUnitPrice()>Configuration.MODERATE_NUMBER_NUMERICAL && mappedData.getAvgUnitPrice()<Configuration.CONSISTENCY_NUMBERS_NUMERICAL)
            this.setAvgUnitPrice(Configuration.CONSISTENCY_NUMBERS);
        if(mappedData.getAvgUnitPrice()>Configuration.CONSISTENCY_NUMBERS_NUMERICAL && mappedData.getAvgUnitPrice()<Configuration.HIGHER_NUMBERS_NUMERICAL)
            this.setAvgUnitPrice(Configuration.HIGHER_NUMBERS);
        if(mappedData.getAvgUnitPrice()> Configuration.HIGHER_NUMBERS_NUMERICAL)
            this.setAvgUnitPrice(Configuration.EXTENDED_NUMBERS);


        if(mappedData.getAvgUnitCost()< Configuration.LEAST_NUMBER_NUMERICAL)
            this.setAvgUnitCost(Configuration.LEAST_NUMBERS);
        if(mappedData.getAvgUnitCost()>Configuration.LEAST_NUMBER_NUMERICAL && mappedData.getAvgUnitCost()<Configuration.MODERATE_NUMBER_NUMERICAL)
            this.setAvgUnitCost(Configuration.MODERATE_NUMBERS);
        if(mappedData.getAvgUnitCost()>Configuration.MODERATE_NUMBER_NUMERICAL && mappedData.getAvgUnitCost()<Configuration.CONSISTENCY_NUMBERS_NUMERICAL)
            this.setAvgUnitCost(Configuration.CONSISTENCY_NUMBERS);
        if(mappedData.getAvgUnitCost()>Configuration.CONSISTENCY_NUMBERS_NUMERICAL && mappedData.getAvgUnitCost()<Configuration.HIGHER_NUMBERS_NUMERICAL)
            this.setAvgUnitCost(Configuration.HIGHER_NUMBERS);
        if(mappedData.getAvgUnitCost()> Configuration.HIGHER_NUMBERS_NUMERICAL)
            this.setAvgUnitCost(Configuration.EXTENDED_NUMBERS);

        if(mappedData.getAvgUnitsSold()< Configuration.LEAST_NUMBER_NUMERICAL)
            this.setAvgUnitsSold(Configuration.LEAST_NUMBERS);
        if(mappedData.getAvgUnitsSold()>Configuration.LEAST_NUMBER_NUMERICAL && mappedData.getAvgUnitsSold()<Configuration.MODERATE_NUMBER_NUMERICAL)
            this.setAvgUnitsSold(Configuration.MODERATE_NUMBERS);
        if(mappedData.getAvgUnitsSold()>Configuration.MODERATE_NUMBER_NUMERICAL && mappedData.getAvgUnitsSold()<Configuration.CONSISTENCY_NUMBERS_NUMERICAL)
            this.setAvgUnitsSold(Configuration.CONSISTENCY_NUMBERS);
        if(mappedData.getAvgUnitsSold()>Configuration.CONSISTENCY_NUMBERS_NUMERICAL && mappedData.getAvgUnitsSold()<Configuration.HIGHER_NUMBERS_NUMERICAL)
            this.setAvgUnitsSold(Configuration.HIGHER_NUMBERS);
        if(mappedData.getAvgUnitsSold()> Configuration.HIGHER_NUMBERS_NUMERICAL)
            this.setAvgUnitsSold(Configuration.EXTENDED_NUMBERS);

        this.setAvgTotalCost(mappedData.getAvgTotalCost());
        this.setAvgTotalRevenue(mappedData.getAvgTotalRevenue());
        this.setAvgTotalProfit(mappedData.getAvgTotalProfit());

        return new Builder(this).build();
    }


    public MappedData(Builder builder) {
        this.avgUnitsSold=builder.avgUnitsSold;
        this.avgUnitCost=builder.avgUnitCost;
        this.avgUnitPrice=builder.avgUnitPrice;
        this.avgTotalRevenue=builder.avgTotalRevenue;
        this.avgTotalCost=builder.avgTotalCost;
        this.avgTotalProfit=builder.avgTotalProfit;
    }

    public  final static class Builder{
        private String avgUnitsSold;
        private String avgUnitCost;
        private String avgUnitPrice;
        private double avgTotalRevenue;
        private double avgTotalCost;
        private double avgTotalProfit;

        public Builder(){}

        public Builder(MappedData data){
            this.avgUnitsSold=data.avgUnitsSold;
            this.avgUnitCost=data.avgUnitCost;
            this.avgUnitPrice=data.avgUnitPrice;
            this.avgTotalRevenue=data.avgTotalRevenue;
            this.avgTotalCost=data.avgTotalCost;
            this.avgTotalProfit=data.avgTotalProfit;
        }
        public Builder avgUnitsSold(String val){
            this.avgUnitsSold=val;
            return this;
        }

        public Builder avgUnitCost(String val){
            this.avgUnitCost=val;
            return this;
        }

        public Builder avgUnitPrice(String val){
            this.avgUnitPrice=val;
            return this;
        }

        public Builder avgTotalRevenue(double val){
            this.avgTotalRevenue=val;
            return this;
        }

        public Builder avgTotalCost(double val){
            this.avgTotalCost=val;
            return this;
        }

        public Builder avgTotalProfit(double val){
            this.avgTotalProfit=val;
            return this;
        }

        public MappedData build(){
            return  new MappedData(this);
        }
    }
}
