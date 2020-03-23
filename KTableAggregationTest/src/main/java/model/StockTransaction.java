package model;

import java.util.Date;
import java.util.Objects;

public class StockTransaction {

    private String symbol;
    private String sector;
    private String industry;
    private int shares;
    private double sharePrice;
    private String customerId;
    //private Date transactionTimestamp;
    private boolean purchase;

    public String getSymbol() {
        return symbol;
    }

    public String getSector() {
        return sector;
    }

    public String getIndustry() {
        return industry;
    }

    public int getShares() {
        return shares;
    }

    public double getSharePrice() {
        return sharePrice;
    }

    public String getCustomerId() {
        return customerId;
    }

   /* public long getTransactionTimestamp() {
        return transactionTimestamp.getTime();
    }*/

    public boolean isPurchase() {
        return purchase;
    }

    @Override
    public String toString() {
        return "StockTransaction{" +
                "symbol='" + symbol + '\'' +
                ", sector='" + sector + '\'' +
                ", industry='" + industry + '\'' +
                ", shares=" + shares +
                ", sharePrice=" + sharePrice +
                ", customerId='" + customerId + '\'' +
               // ", transactionTimestamp=" + transactionTimestamp +
                ", purchase=" + purchase +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StockTransaction)) return false;
        StockTransaction that = (StockTransaction) o;
        return getShares() == that.getShares() &&
                Double.compare(that.getSharePrice(), getSharePrice()) == 0 &&
                isPurchase() == that.isPurchase() &&
                Objects.equals(getSymbol(), that.getSymbol()) &&
                Objects.equals(getSector(), that.getSector()) &&
                Objects.equals(getIndustry(), that.getIndustry()) &&
                Objects.equals(getCustomerId(), that.getCustomerId())/* &&
                Objects.equals(getTransactionTimestamp(), that.getTransactionTimestamp())*/;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSymbol(), getSector(), getIndustry(), getShares(), getSharePrice(), getCustomerId()/*, getTransactionTimestamp()*/, isPurchase());
    }

    private StockTransaction(Builder builder){
        symbol=builder.symbol;
        sector=builder.sector;
        industry= builder.industry;
        shares= builder.shares;
        sharePrice= builder.sharePrice;
        customerId= builder.customerId;
      //  transactionTimestamp= builder.transactionTimestamp;
        purchase=builder.purchase;

    }

    public static Builder newBuilder(){return new Builder();}

    public static Builder newBuilder(Builder copy){
        Builder builder= new Builder();
        builder.symbol=copy.symbol;
        builder.sector=copy.sector;
        builder.industry=copy.industry;
        builder.shares= copy.shares;
        builder.sharePrice= copy.sharePrice;
        builder.customerId=copy.customerId;
      //  builder.transactionTimestamp=copy.transactionTimestamp;
        builder.purchase=copy.purchase;
        return builder;
    }


    public static final class Builder{

        private String symbol;
        private String sector;
        private String industry;
        private int shares;
        private double sharePrice;
        private String customerId;
       // private Date transactionTimestamp;
        private boolean purchase;

        private Builder(){}

        public Builder withSymbol(String val){
            symbol=val;
            return Builder.this;
        }

        public Builder withSector(String val){
            sector =val;
            return Builder.this;
        }

        public Builder withIndustry(String val){
            industry=val;
            return this;
        }

        public Builder withShares(int val){
            shares=val;
            return this;
        }

        public Builder withSharePrice(double val){
            sharePrice=val;
            return Builder.this;
        }

        public Builder withCustomerId(String val){
            customerId=val;
            return Builder.this;
        }

       /* public Builder withTransactionTimeStamp(Date val){
            transactionTimestamp=val;
            return Builder.this;
        }*/

        public Builder withPurchase(boolean val){
            purchase=val;
            return Builder.this;
        }

        public StockTransaction build(){
            return new StockTransaction(Builder.this);
        }
        public StockTransaction build(String row){

            if(null!=row){
                String[] cols = row.split(",");
                this.symbol=cols[0];
                this.sector=cols[1];
                this.industry=cols[2];
                this.shares=Integer.valueOf(cols[3]);
                this.sharePrice=Double.valueOf(cols[4]);
                this.customerId=cols[5];
                this.purchase=Boolean.getBoolean(cols[6]);
                return new StockTransaction(this);
            }
            return null;
        }

    }
}
