package model.aggregation;


import config.Configuration;
import model.Sales;
import model.aggregation.avg.AvgValue;
import model.aggregation.avg.MappedData;

public class HigherAndLowereResults {


    //units sold
    private int minUnitsSold;
    private int maxUnitsSold;

    private int sumUnitsSold;
    private int countUnitsSoldNumber;

    private double avgUnitsSold;

    //unit price
    private double minUnitPrice;
    private double maxUnitPrice;

    private double sumUnitPrice;

    private int countUnitPriceNumber;
    private double avgUnitPrice;

    //unit cost
    private double minUnitCost;
    private double maxUnitCost;

    private double sumUnitCost;
    private int countUnitCostNumber;

    private double avgUnitCost;

    private double sumTotalRevenue;
    private int countTotalRevenueNumber;
    private double avgTotalRevenue;

    private double sumTotalCost;
    private int countTotalCostNumber;
    private double avgTotalCost;

    private double sumTotalProfit;
    private int countTotalProfitNumber;
    private double avgTotalProfit;

    public int getMinUnitsSold() {
        return minUnitsSold;
    }

    public void setMinUnitsSold(int minUnitsSold) {
        this.minUnitsSold = minUnitsSold;
    }

    public int getMaxUnitsSold() {
        return maxUnitsSold;
    }

    public void setMaxUnitsSold(int maxUnitsSold) {
        this.maxUnitsSold = maxUnitsSold;
    }

    public int getSumUnitsSold() {
        return sumUnitsSold;
    }

    public void setSumUnitsSold(int sumUnitsSold) {
        this.sumUnitsSold = sumUnitsSold;
    }

    public int getCountUnitsSoldNumber() {
        return countUnitsSoldNumber;
    }

    public void setCountUnitsSoldNumber(int countUnitsSoldNumber) {
        this.countUnitsSoldNumber = countUnitsSoldNumber;
    }

    public double getAvgUnitsSold() {
        return avgUnitsSold;
    }

    public void setAvgUnitsSold(double avgUnitsSold) {
        this.avgUnitsSold = avgUnitsSold;
    }

    public double getMinUnitPrice() {
        return minUnitPrice;
    }

    public void setMinUnitPrice(double minUnitPrice) {
        this.minUnitPrice = minUnitPrice;
    }

    public double getMaxUnitPrice() {
        return maxUnitPrice;
    }

    public void setMaxUnitPrice(double maxUnitPrice) {
        this.maxUnitPrice = maxUnitPrice;
    }

    public double getSumUnitPrice() {
        return sumUnitPrice;
    }

    public void setSumUnitPrice(double sumUnitPrice) {
        this.sumUnitPrice = sumUnitPrice;
    }

    public int getCountUnitPriceNumber() {
        return countUnitPriceNumber;
    }

    public void setCountUnitPriceNumber(int countUnitPriceNumber) {
        this.countUnitPriceNumber = countUnitPriceNumber;
    }

    public double getAvgUnitPrice() {
        return avgUnitPrice;
    }

    public void setAvgUnitPrice(double avgUnitPrice) {
        this.avgUnitPrice = avgUnitPrice;
    }

    public double getMinUnitCost() {
        return minUnitCost;
    }

    public void setMinUnitCost(double minUnitCost) {
        this.minUnitCost = minUnitCost;
    }

    public double getMaxUnitCost() {
        return maxUnitCost;
    }

    public void setMaxUnitCost(double maxUnitCost) {
        this.maxUnitCost = maxUnitCost;
    }

    public double getSumUnitCost() {
        return sumUnitCost;
    }

    public void setSumUnitCost(double sumUnitCost) {
        this.sumUnitCost = sumUnitCost;
    }

    public int getCountUnitCostNumber() {
        return countUnitCostNumber;
    }

    public void setCountUnitCostNumber(int countUnitCostNumber) {
        this.countUnitCostNumber = countUnitCostNumber;
    }

    public double getAvgUnitCost() {
        return avgUnitCost;
    }

    public void setAvgUnitCost(double avgUnitCost) {
        this.avgUnitCost = avgUnitCost;
    }

    public double getSumTotalRevenue() {
        return sumTotalRevenue;
    }

    public void setSumTotalRevenue(double sumTotalRevenue) {
        this.sumTotalRevenue = sumTotalRevenue;
    }

    public int getCountTotalRevenueNumber() {
        return countTotalRevenueNumber;
    }

    public void setCountTotalRevenueNumber(int countTotalRevenueNumber) {
        this.countTotalRevenueNumber = countTotalRevenueNumber;
    }

    public double getAvgTotalRevenue() {
        return avgTotalRevenue;
    }

    public void setAvgTotalRevenue(double avgTotalRevenue) {
        this.avgTotalRevenue = avgTotalRevenue;
    }

    public double getSumTotalCost() {
        return sumTotalCost;
    }

    public void setSumTotalCost(double sumTotalCost) {
        this.sumTotalCost = sumTotalCost;
    }

    public int getCountTotalCostNumber() {
        return countTotalCostNumber;
    }

    public void setCountTotalCostNumber(int countTotalCostNumber) {
        this.countTotalCostNumber = countTotalCostNumber;
    }

    public double getAvgTotalCost() {
        return avgTotalCost;
    }

    public void setAvgTotalCost(double avgTotalCost) {
        this.avgTotalCost = avgTotalCost;
    }

    public double getSumTotalProfit() {
        return sumTotalProfit;
    }

    public void setSumTotalProfit(double sumTotalProfit) {
        this.sumTotalProfit = sumTotalProfit;
    }

    public int getCountTotalProfitNumber() {
        return countTotalProfitNumber;
    }

    public void setCountTotalProfitNumber(int countTotalProfitNumber) {
        this.countTotalProfitNumber = countTotalProfitNumber;
    }

    public double getAvgTotalProfit() {
        return avgTotalProfit;
    }

    public void setAvgTotalProfit(double avgTotalProfit) {
        this.avgTotalProfit = avgTotalProfit;
    }

    public HigherAndLowereResults(){}

    public MappedData mappedData(HigherAndLowereResults data){
        MappedData mappedData= new MappedData();
        return mappedData.getMapped(data);
    }

    public HigherAndLowereResults getAvg(HigherAndLowereResults data){
        this.avgUnitsSold= (double) data.getSumUnitsSold()/ data.getCountUnitsSoldNumber();
        this.avgUnitCost= data.getSumUnitCost()/data.getCountUnitCostNumber();
        this.avgUnitPrice=data.getSumUnitPrice()/data.getCountUnitPriceNumber();
        this.avgTotalRevenue=data.getSumTotalRevenue()/data.getCountTotalRevenueNumber();
        this.avgTotalCost=data.getSumTotalCost()/data.getCountTotalCostNumber();
        this.avgTotalProfit=data.getSumTotalProfit()/data.getCountTotalProfitNumber();
        return this;
    }

    public HigherAndLowereResults getAggregation(Sales s){

        this.minUnitsSold=minUnitsSold(s);
        this.maxUnitsSold=maxUnitsSold(s);
        this.minUnitPrice=minUnitPrice(s);
        this.maxUnitPrice=maxUnitPrice(s);
        this.minUnitCost=minUnitCost(s);
        this.maxUnitCost=maxUnitCost(s);
        this.sumUnitsSold=(int)sumUnitsSold(s).getSum();
       // this.countUnitsSoldNumber=sumUnitsSold(s).getCount();
        this.sumUnitPrice=sumUnitPrice(s).getSum();
       // this.countUnitPriceNumber=sumUnitPrice(s).getCount();
        this.sumUnitCost=sumUnitCost(s).getSum();
       // this.countUnitCostNumber=sumUnitCost(s).getCount();
        this.sumTotalRevenue=sumTotalRevenue(s).getSum();
      //  this.countTotalRevenueNumber=sumTotalRevenue(s).getCount();
        this.sumTotalCost=sumTotalCost(s).getSum();
      //  this.countTotalCostNumber=sumTotalCost(s).getCount();
        this.sumTotalProfit=sumTotalProfit(s).getSum();
      //  this.countTotalProfitNumber=sumTotalProfit(s).getCount();
        return new Builder(this).build();
    }

    public AvgValue sumTotalProfit(Sales s){
        this.sumTotalProfit+=s.getTotalProfit();
        this.countTotalProfitNumber++;
        return new AvgValue(this.countTotalProfitNumber,this.sumTotalProfit);
    }


    public AvgValue sumTotalCost(Sales s){
        this.sumTotalCost+=s.getTotalCost();
        this.countTotalCostNumber++;
        return new AvgValue(this.countTotalCostNumber,this.sumTotalCost);
    }


    public AvgValue sumTotalRevenue(Sales s){
        this.sumTotalRevenue+=s.getTotalRevenue();
        this.countTotalRevenueNumber++;
        return new AvgValue(this.countTotalRevenueNumber,this.sumTotalRevenue);
    }


    public AvgValue sumUnitsSold(Sales s){
        this.sumUnitsSold+=s.getUnitsSold();
        this.countUnitsSoldNumber++;
        return new AvgValue(this.countUnitsSoldNumber,this.sumUnitsSold);
    }

    public AvgValue sumUnitPrice(Sales s){
        this.sumUnitPrice+=s.getUnitPrice();
        this.countUnitPriceNumber++;
        return new AvgValue(this.countUnitPriceNumber,this.sumUnitPrice);
    }

    public AvgValue sumUnitCost(Sales s){
        this.sumUnitCost+=s.getUnitCost();
        this.countUnitCostNumber++;
        return new AvgValue(this.countUnitCostNumber,this.sumUnitCost);
    }


    public HigherAndLowereResults sumUnitPrice(double unitPrice){
        this.sumUnitPrice+=unitPrice;
        this.countUnitPriceNumber++;
        return this;
    }

    public HigherAndLowereResults sumUnitCost(double unitCost){
        this.sumUnitCost+=unitCost;
        this.countUnitCostNumber++;
        return this;
    }

    public HigherAndLowereResults sumTotalCost(double totalCost){
        this.sumTotalCost+=totalCost;
        this.countTotalCostNumber++;
        return this;
    }

    public int minUnitsSold(Sales s){
        if(this.minUnitsSold==0)
            this.minUnitsSold=s.getUnitsSold();
        if(this.minUnitsSold!=0 && this.minUnitsSold>s.getUnitsSold())
            this.minUnitsSold=s.getUnitsSold();
        return minUnitsSold;

    }

    public int maxUnitsSold(Sales s){
        if(this.maxUnitsSold==0)
            this.maxUnitsSold=s.getUnitsSold();
        if(this.maxUnitsSold!=0 && this.maxUnitsSold<s.getUnitsSold())
            this.maxUnitsSold=s.getUnitsSold();
        return this.maxUnitsSold;
    }

    public double minUnitPrice(Sales s){
        if(this.minUnitPrice==0)
            this.minUnitPrice=s.getUnitPrice();
        if(this.minUnitPrice!=0 && this.minUnitPrice>s.getUnitPrice())
            this.minUnitPrice=s.getUnitPrice();
        return this.minUnitPrice;
    }

    public double maxUnitPrice(Sales s){
        if(this.maxUnitPrice==0)
            this.maxUnitPrice=s.getUnitPrice();
        if(this.maxUnitPrice!=0 && this.maxUnitPrice<s.getUnitPrice())
            this.maxUnitPrice=s.getUnitPrice();
        return this.maxUnitPrice;
    }


    public  double minUnitCost(Sales s){
        if(this.minUnitCost==0)
            this.minUnitCost=s.getUnitCost();
        if(this.minUnitCost!=0 && this.minUnitCost<s.getUnitCost())
            this.minUnitCost=s.getUnitCost();
        return this.minUnitCost;
    }

    public double maxUnitCost(Sales s){
        if(this.maxUnitCost==0)
            this.maxUnitCost=s.getUnitCost();
        if(this.maxUnitCost!=0 && this.maxUnitCost<s.getUnitCost())
            this.maxUnitCost=s.getUnitCost();
        return this.maxUnitCost;
    }


    @Override
    public String toString() {
        return "HigherAndLowereResults{" +
                ", minUnitsSold=" + minUnitsSold +
                ", minUnitsSold=" + minUnitsSold +
                ", maxUnitsSold=" + maxUnitsSold +
                ", sumUnitsSold=" + sumUnitsSold +
                ", countUnitsSoldNumber=" + countUnitsSoldNumber +
                ", avgUnitsSold=" + avgUnitsSold +
                ", minUnitPrice=" + minUnitPrice +
                ", maxUnitPrice=" + maxUnitPrice +
                ", sumUnitPrice=" + sumUnitPrice +
                ", countUnitPriceNumber=" + countUnitPriceNumber +
                ", avgUnitPrice=" + avgUnitPrice +
                ", minUnitCost=" + minUnitCost +
                ", maxUnitCost=" + maxUnitCost +
                ", sumUnitCost=" + sumUnitCost +
                ", countUnitCostNumber=" + countUnitCostNumber +
                ", avgUnitCost=" + avgUnitCost +
                ", sumTotalRevenue=" + sumTotalRevenue +
                ", countTotalRevenueNumber=" + countTotalRevenueNumber +
                ", avgTotalRevenue=" + avgTotalRevenue +
                ", sumTotalCost=" + sumTotalCost +
                ", countTotalCostNumber=" + countTotalCostNumber +
                ", avgTotalCost=" + avgTotalCost +
                ", sumTotalProfit=" + sumTotalProfit +
                ", countTotalProfitNumber=" + countTotalProfitNumber +
                ", avgTotalProfit=" + avgTotalProfit +
                '}';
    }

    public static Builder newBuilder(){
        return new Builder();
    }
    public static Builder builder(HigherAndLowereResults higherAndLowereResults){
        return new Builder(higherAndLowereResults);
    }
    public HigherAndLowereResults(Builder builder){
        this.minUnitsSold=builder.minUnitsSold;
        this.maxUnitsSold=builder.maxUnitsSold;
        this.minUnitPrice=builder.minUnitPrice;
        this.maxUnitPrice=builder.maxUnitPrice;
        this.minUnitCost=builder.minUnitCost;
        this.maxUnitCost=builder.maxUnitCost;
        this.sumUnitsSold=builder.sumUnitsSold;
        this.countUnitsSoldNumber=builder.countUnitsSoldNumber;
        this.sumUnitPrice=builder.sumUnitPrice;
        this.countUnitPriceNumber=builder.countUnitPriceNumber;
        this.sumUnitCost=builder.sumUnitCost;
        this.countUnitCostNumber=builder.countUnitCostNumber;
        this.sumTotalRevenue=builder.sumTotalRevenue;
        this.countTotalRevenueNumber=builder.countTotalRevenueNumber;
        this.sumTotalCost=builder.sumTotalCost;
        this.countTotalCostNumber=builder.countTotalCostNumber;
        this.sumTotalProfit=builder.sumTotalProfit;
        this.countTotalProfitNumber=builder.countTotalProfitNumber;
        this.avgUnitsSold=builder.avgUnitsSold;
        this.avgUnitPrice=builder.avgUnitPrice;
        this.avgUnitCost=builder.avgUnitCost;
        this.avgTotalRevenue=builder.avgTotalRevenue;
        this.avgTotalCost=builder.avgTotalCost;
        this.avgTotalProfit=builder.avgTotalProfit;
    }

    public static final class  Builder{

        //units sold
        private int minUnitsSold;
        private int maxUnitsSold;
        private int sumUnitsSold;
        private int countUnitsSoldNumber;
        private double avgUnitsSold;

        //unit price
        private double minUnitPrice;
        private double maxUnitPrice;
        private double sumUnitPrice;
        private int countUnitPriceNumber;
        private double avgUnitPrice;


        //unit cost
        private double minUnitCost;
        private double maxUnitCost;
        private double sumUnitCost;
        private int countUnitCostNumber;
        private double avgUnitCost;

        private double sumTotalRevenue;
        private int countTotalRevenueNumber;
        private double avgTotalRevenue;
        private double sumTotalCost;
        private int countTotalCostNumber;
        private double avgTotalCost;
        private double sumTotalProfit;
        private int countTotalProfitNumber;
        private double avgTotalProfit;

        public Builder(){

        }
        public Builder(HigherAndLowereResults builder){
            this.minUnitsSold=builder.minUnitsSold;
            this.maxUnitsSold=builder.maxUnitsSold;
            this.minUnitPrice=builder.minUnitPrice;
            this.maxUnitPrice=builder.maxUnitPrice;
            this.minUnitCost=builder.minUnitCost;
            this.maxUnitCost=builder.maxUnitCost;
            this.sumUnitsSold=builder.sumUnitsSold;
            this.countUnitsSoldNumber=builder.countUnitsSoldNumber;
            this.sumUnitPrice=builder.sumUnitPrice;
            this.countUnitPriceNumber=builder.countUnitPriceNumber;
            this.sumUnitCost=builder.sumUnitCost;
            this.countUnitCostNumber=builder.countUnitCostNumber;
            this.sumTotalRevenue=builder.sumTotalRevenue;
            this.countTotalRevenueNumber=builder.countTotalRevenueNumber;
            this.sumTotalCost=builder.sumTotalCost;
            this.countTotalCostNumber=builder.countTotalCostNumber;
            this.sumTotalProfit=builder.sumTotalProfit;
            this.countTotalProfitNumber=builder.countTotalProfitNumber;
            this.avgUnitsSold=builder.avgUnitsSold;
            this.avgUnitPrice=builder.avgUnitPrice;
            this.avgUnitCost=builder.avgUnitCost;
            this.avgTotalRevenue=builder.avgTotalRevenue;
            this.avgTotalCost=builder.avgTotalCost;
            this.avgTotalProfit=builder.avgTotalProfit;
        }

        public Builder avgUnitsSold(int avgUnitsSold){
            this.avgUnitsSold=avgUnitsSold;
            return this;
        }

        public Builder avgUnitPrice(int avgUnitPrice){
            this.avgUnitPrice=avgUnitPrice;
            return this;
        }

        public Builder avgUnitCost(double avgUnitCost){
            this.avgUnitCost=avgUnitCost;
            return this;
        }

        public Builder avgTotalRevenue(double avgTotalRevenue){
            this.avgTotalRevenue=avgTotalRevenue;
            return this;
        }

        public Builder avgTotalCost(double avgTotalCost){
            this.avgTotalCost=avgTotalCost;
            return this;
        }

        public Builder avgTotalProfit(double avgTotalProfit){
            this.avgTotalProfit=avgTotalProfit;
            return this;
        }


        public Builder minUnitsSold(int minUnitsSold){
            this.minUnitsSold=minUnitsSold;
            return this;
        }

        public Builder maxUnitsSold(int maxUnitsSold){
            this.maxUnitsSold=maxUnitsSold;
            return this;
        }

        public Builder minUnitPrice(double minUnitPrice){
            this.minUnitPrice=minUnitPrice;
            return this;
        }

        public Builder maxUnitPrice(double maxUnitPrice){
            this.maxUnitPrice=maxUnitPrice;
            return this;
        }


        public Builder minUnitCost(double minUnitCost){
            this.minUnitCost=minUnitCost;
            return this;
        }

        public Builder maxUnitCost(double maxUnitCost){
            this.maxUnitCost=maxUnitCost;
            return this;
        }

        /*
        this.sumUnitsSold=builder.sumUnitsSold;
            this.countUnitsSoldNumber=builder.countUnitsSoldNumber;
            this.sumUnitPrice=builder.sumUnitPrice;
            this.countUnitPriceNumber=builder.countUnitPriceNumber;
            this.sumUnitCost=builder.sumUnitCost;
            this.countUnitCostNumber=builder.countUnitCostNumber;
            this.sumTotalRevenue=builder.sumTotalRevenue;
            this.countTotalRevenueNumber=builder.countTotalRevenueNumber;
            this.sumTotalCost=builder.sumTotalCost;
            this.countTotalCostNumber=builder.countTotalCostNumber;
            this.sumTotalProfit=builder.sumTotalProfit;
            this.countTotalProfitNumber=builder.countTotalProfitNumber;
         */
        public Builder sumTotalCost(double sumTotalCost){
            this.sumTotalCost=sumTotalCost;
            return this;
        }

        public Builder countTotalCostNumber(int countTotalCostNumber){
            this.countTotalCostNumber=countTotalCostNumber;
            return this;
        }

        public Builder sumTotalProfit(double sumTotalProfit){
            this.sumTotalProfit=sumTotalProfit;
            return this;
        }

        public Builder countTotalProfitNumber(int countTotalProfitNumber){
            this.countTotalProfitNumber=countTotalProfitNumber;
            return this;
        }


        public Builder sumUnitsSold(int sumUnitsSold){
            this.sumUnitsSold=sumUnitsSold;
            return this;
        }

        public Builder countUnitsSoldNumber(int countUnitsSoldNumber){
            this.countUnitsSoldNumber=countUnitsSoldNumber;
            return this;
        }

        public Builder sumUnitPrice(double sumUnitPrice){
            this.sumUnitPrice=sumUnitPrice;
            return this;
        }

        public Builder countUnitPriceNumber(int countUnitPriceNumber){
            this.countUnitPriceNumber=countUnitPriceNumber;
            return this;
        }

        public Builder sumUnitCost(double sumUnitCost){
            this.sumUnitCost=sumUnitCost;
            return this;
        }

        public Builder countUnitCostNumber(int countUnitCostNumber){
            this.countUnitCostNumber=countUnitCostNumber;
            return this;
        }

        public Builder sumTotalRevenue(double sumTotalRevenue){
            this.sumTotalRevenue=sumTotalRevenue;
            return this;
        }

        public Builder countTotalRevenueNumber(int countTotalRevenueNumber){
            this.countTotalRevenueNumber=countTotalRevenueNumber;
            return this;
        }
        public HigherAndLowereResults build(){
            return new HigherAndLowereResults(this);
        }
    }
}
