package model.joiners;

public class CorrelatedSales {

    private long orderId;
    private int onlineUnitSold;
    private int offlineUnitSold;
    private double onlineUnitPrice;
    private double offlineUnitPrice;
    private double totalOfflineOnlineProfit;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public int getOnlineUnitSold() {
        return onlineUnitSold;
    }

    public void setOnlineUnitSold(int onlineUnitSold) {
        this.onlineUnitSold = onlineUnitSold;
    }

    public int getOfflineUnitSold() {
        return offlineUnitSold;
    }

    public void setOfflineUnitSold(int offlineUnitSold) {
        this.offlineUnitSold = offlineUnitSold;
    }

    public double getOnlineUnitPrice() {
        return onlineUnitPrice;
    }

    public void setOnlineUnitPrice(double onlineUnitPrice) {
        this.onlineUnitPrice = onlineUnitPrice;
    }

    public double getOfflineUnitPrice() {
        return offlineUnitPrice;
    }

    public void setOfflineUnitPrice(double offlineUnitPrice) {
        this.offlineUnitPrice = offlineUnitPrice;
    }

    public double getTotalOfflineOnlineProfit() {
        return totalOfflineOnlineProfit;
    }

    public void setTotalOfflineOnlineProfit(double totalOfflineOnlineProfit) {
        this.totalOfflineOnlineProfit = totalOfflineOnlineProfit;
    }

    public static Builder newBuilder(CorrelatedSales sales){
        return new Builder(sales);
    }

    public static Builder builder(){
        return new Builder();
    }
    public CorrelatedSales(Builder builder){
        this.orderId=builder.orderId;
        this.onlineUnitSold=builder.onlineUnitSold;
        this.offlineUnitSold=builder.offlineUnitSold;
        this.onlineUnitPrice=builder.onlineUnitPrice;
        this.offlineUnitPrice=builder.offlineUnitPrice;
        this.totalOfflineOnlineProfit=builder.totalOfflineOnlineProfit;
    }

    @Override
    public String toString() {
        return "CorrelatedSales{" +
                "orderId='" + orderId + '\'' +
                ", onlineUnitSold=" + onlineUnitSold +
                ", offlineUnitSold=" + offlineUnitSold +
                ", onlineUnitPrice=" + onlineUnitPrice +
                ", offlineUnitPrice=" + offlineUnitPrice +
                ", totalOfflineOnlineProfit=" + totalOfflineOnlineProfit +
                '}';
    }

    public static final class Builder{
        private long orderId;
        private int onlineUnitSold;
        private int offlineUnitSold;
        private double onlineUnitPrice;
        private double offlineUnitPrice;
        private double totalOfflineOnlineProfit;

        public Builder(){}

        public Builder(CorrelatedSales sales){
            this.orderId=sales.orderId;
            this.onlineUnitSold=sales.onlineUnitSold;
            this.offlineUnitSold=sales.offlineUnitSold;
            this.onlineUnitPrice=sales.onlineUnitPrice;
            this.offlineUnitPrice=sales.offlineUnitPrice;
            this.totalOfflineOnlineProfit=sales.totalOfflineOnlineProfit;
        }
        public Builder orderId(long id){
            this.orderId=id;
            return this;
        }

        public Builder onlineUnitSold(int onlineUnitSold){
            this.onlineUnitSold=onlineUnitSold;
            return this;
        }

        public Builder offlineUnitSold(int offlineUnitSold){
            this.offlineUnitSold=offlineUnitSold;
            return this;
        }

        public Builder onlineUnitPrice(double onlineUnitPrice){
            this.onlineUnitPrice=onlineUnitPrice;
            return this;
        }

        public Builder offlineUnitPrice(double offlineUnitPrice){
            this.offlineUnitPrice=offlineUnitPrice;
            return this;
        }

        public Builder totalOfflineOnlineProfit(double totalOfflineOnlineProfit){
            this.totalOfflineOnlineProfit=totalOfflineOnlineProfit;
            return this;
        }

        public CorrelatedSales build(){
            return  new CorrelatedSales(this);
        }
    }
}
