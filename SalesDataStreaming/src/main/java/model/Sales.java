package model;

public class Sales {

    private String region;
    private String country;
    private String item;
    private String salesChannel;
    private char orderPriority;
    private String orderDate;
    private Long orderId;
    private String shipDate;
    private int unitsSold;
    private double unitPrice;
    private double unitCost;
    private double totalRevenue;
    private double totalCost;
    private double totalProfit;

    public Sales(Builder builder) {
        this.region = builder.region;
        this.country = builder.country;
        this.item = builder.item;
        this.salesChannel = builder.salesChannel;
        this.orderPriority = builder.orderPriority;
        this.orderDate = builder.orderDate;
        this.orderId = builder.orderId;
        this.shipDate = builder.shipDate;
        this.unitsSold = builder.unitsSold;
        this.unitPrice = builder.unitPrice;
        this.unitCost = builder.unitCost;
        this.totalRevenue = builder.totalRevenue;
        this.totalCost = builder.totalCost;
        this.totalProfit =builder.totalProfit;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getSalesChannel() {
        return salesChannel;
    }

    public void setSalesChannel(String salesChannel) {
        this.salesChannel = salesChannel;
    }

    public char getOrderPriority() {
        return orderPriority;
    }

    public void setOrderPriority(char orderPriority) {
        this.orderPriority = orderPriority;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getShipDate() {
        return shipDate;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public int getUnitsSold() {
        return unitsSold;
    }

    public void setUnitsSold(int unitsSold) {
        this.unitsSold = unitsSold;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(double unitCost) {
        this.unitCost = unitCost;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public double getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(double totalProfit) {
        this.totalProfit = totalProfit;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Builder builder(Sales sales){
       return new Builder(sales);
    }

    @Override
    public String toString() {
        return "Sales{" +
                "region='" + region + '\'' +
                ", country='" + country + '\'' +
                ", item='" + item + '\'' +
                ", salesChannel='" + salesChannel + '\'' +
                ", orderPriority=" + orderPriority +
                ", orderDate='" + orderDate + '\'' +
                ", orderId=" + orderId +
                ", shipDate='" + shipDate + '\'' +
                ", unitsSold=" + unitsSold +
                ", unitPrice=" + unitPrice +
                ", unitCost=" + unitCost +
                ", totalRevenue=" + totalRevenue +
                ", totalCost=" + totalCost +
                ", totalProfit=" + totalProfit +
                '}';
    }

    public static final class Builder{
        private String region;
        private String country;
        private String item;
        private String salesChannel;
        private char orderPriority;
        private String orderDate;
        private Long orderId;
        private String shipDate;
        private int unitsSold;
        private double unitPrice;
        private double unitCost;
        private double totalRevenue;
        private double totalCost;
        private double totalProfit;

        Builder(){

        }
        public Builder(Sales sales) {
            this.region = sales.region;
            this.country = sales.country;
            this.item = sales.item;
            this.salesChannel = sales.salesChannel;
            this.orderPriority = sales.orderPriority;
            this.orderDate = sales.orderDate;
            this.orderId = sales.orderId;
            this.shipDate = sales.shipDate;
            this.unitsSold = sales.unitsSold;
            this.unitPrice = sales.unitPrice;
            this.unitCost = sales.unitCost;
            this.totalRevenue = sales.totalRevenue;
            this.totalCost = sales.totalCost;
            this.totalProfit = sales.totalProfit;
        }

        public Builder withRegion(String region){
            this.region= region;
            return this;
        }

        public Builder withCountry(String country){
            this.country=country;
            return this;
        }

        public Builder withItem(String item){
            this.item=item;
            return this;
        }

        public Builder withSalesChannel(String salesChannel){
            this.salesChannel=salesChannel;
            return this;
        }

        public Builder withOrderPriority(char c){
            this.orderPriority=c;
            return this;
        }

        public Builder withOrderDate(String oDate){
            this.orderDate=oDate;
            return this;
        }

        public Builder withOrderId(Long oId){
            this.orderId= oId;
            return this;
        }

        public Builder withShipDate(String shipDate){
            this.shipDate=shipDate;
            return this;
        }

        public Builder withUnitsSold(int unitsSold){
            this.unitsSold=unitsSold;
            return this;
        }

        public  Builder withUnitPrice(double unitPrice){
            this.unitPrice =unitPrice;
            return this;
        }

        public  Builder withUnitCost(double unitCost){
            this.unitCost= unitCost;
            return this;
        }

        public  Builder withTotalRevenue(double totalRevenue){
            this.totalRevenue= totalRevenue;
            return this;
        }

        public Builder withTotalCost(double totalCost){
            this.totalCost= totalCost;
            return this;
        }

        public Builder withTotalProfit(double totalProfit){
            this.totalProfit= totalProfit;
            return this;
        }

        public Sales build(){
            return new Sales(this);
        }

        public  Sales build(String row){
            if(null!=row){
                String cols[]= row.split(",");

                this.region=cols[0].replace("[","");
                this.country=cols[1].replace(" ","");
                this.item=cols[2].replace(" ","");
                this.salesChannel=cols[3].replace(" ","");
                this.orderPriority=cols[4].replace(" ","").charAt(0);
                this.orderDate=cols[5].replace(" ","");
                this.orderId=Long.parseLong(cols[6].replace(" ",""));
                this.shipDate=cols[7].replace(" ","");
                this.unitsSold=Integer.parseInt(cols[8].replace(" ",""));
                this.unitPrice=Double.parseDouble(cols[9].replace(" ",""));
                this.unitCost=Double.parseDouble(cols[10].replace(" ",""));
                this.totalRevenue=Double.parseDouble(cols[11].replace(" ",""));
                this.totalCost=Double.parseDouble(cols[12].replace(" ",""));
                this.totalProfit=Double.parseDouble(cols[13].replace("]",""));

                return new Sales(this);
            }
            return null;
        }

    }

}
