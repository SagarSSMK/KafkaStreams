package model.streamtablejoin;

import model.windowsaggregartion.SessionTest;

public class LookUpResults {

    private String unitsSold;
    private String unitsCost;
    private String unitPrice;
    private String totalRevenue;
    private String totalCost;
    private String totalProfit;

    @Override
    public String toString() {
        return "LookUpResults{" +
                "unitsSold='" + unitsSold + '\'' +
                ", unitsCost='" + unitsCost + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", totalRevenue='" + totalRevenue + '\'' +
                ", totalCost='" + totalCost + '\'' +
                ", totalProfit='" + totalProfit + '\'' +
                '}';
    }

    public LookUpResults(){}
    public String getUnitsSold() {
        return unitsSold;
    }

    public void setUnitsSold(String unitsSold) {
        this.unitsSold = unitsSold;
    }

    public String getUnitsCost() {
        return unitsCost;
    }

    public void setUnitsCost(String unitsCost) {
        this.unitsCost = unitsCost;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(String totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public String getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(String totalCost) {
        this.totalCost = totalCost;
    }

    public String getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(String totalProfit) {
        this.totalProfit = totalProfit;
    }

    public LookUpResults(Builder builder) {
        this.unitsSold= builder.unitsSold;
        this.unitsCost= builder.unitsCost;
        this.unitPrice= builder.unitPrice;
        this.totalRevenue= builder.totalRevenue;
        this.totalCost= builder.totalCost;
        this.totalProfit= builder.totalProfit;
    }


    public static Builder Builder(){
        return new Builder();
    }


    public static final class Builder{

        private String unitsSold;
        private String unitsCost;
        private String unitPrice;
        private String totalRevenue;
        private String totalCost;
        private String totalProfit;

        Builder(){}

        public Builder(LookUpResults results){
            this.unitsSold= results.unitsSold;
            this.unitsCost= results.unitsCost;
            this.unitPrice= results.unitPrice;
            this.totalRevenue= results.totalRevenue;
            this.totalCost= results.totalCost;
            this.totalProfit= results.totalProfit;
        }


        public Builder unitsSold(String val){
            this.unitsSold=val;
            return this;
        }

        public Builder unitsCost(String val){
            this.unitsCost=val;
            return this;
        }

        public Builder unitPrice(String val){
            this.unitPrice=val;
            return this;
        }

        public Builder totalRevenue(String val){
            this.totalRevenue=val;
            return this;
        }

        public Builder totalCost(String val){
            this.totalCost=val;
            return this;
        }

        public Builder totalProfit(String val){
            this.totalProfit=val;
            return this;
        }

        public LookUpResults build(){
            return  new LookUpResults(this);
        }
    }

}
