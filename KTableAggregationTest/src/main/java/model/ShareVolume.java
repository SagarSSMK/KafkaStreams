package model;

public class ShareVolume {

    private String symbol;
    private int shares;
    private String industry;

    public ShareVolume(Builder builder){
        symbol=builder.symbol;
        shares= builder.shares;
        industry=builder.industry;
    }

    public static Builder newBuilder(){return new Builder();}

    public static Builder newBuilder(StockTransaction stockTransaction){
        Builder builder= new Builder();
        builder.symbol=stockTransaction.getSymbol();
        builder.shares=stockTransaction.getShares();
        builder.industry=stockTransaction.getIndustry();
        return builder;
    }

    public static Builder newBuilder(ShareVolume shareVolume){
        Builder builder= new Builder();
        builder.symbol=shareVolume.symbol;
        builder.shares=shareVolume.shares;
        builder.industry=shareVolume.industry;
        return builder;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getShares() {
        return shares;
    }

    public String getIndustry() {
        return industry;
    }

    @Override
    public String toString() {
        return "ShareVolume{" +
                "symbol='" + symbol + '\'' +
                ", shares=" + shares +
                ", industry='" + industry + '\'' +
                '}';
    }

    public static ShareVolume sum(ShareVolume sv1,ShareVolume sv2){
        Builder builder= newBuilder(sv1);
        builder.shares=sv1.shares+sv2.shares;
        return builder.build();
    }

    public static final class Builder{
        private String symbol;
        private int shares;
        private String industry;

        public Builder withSymbol(String val){
            symbol=val;
            return this;
        }

        public Builder withShares(int val){
            shares=val;
            return this;
        }

        public Builder withIndustry(String val){
            industry=val;
            return this;
        }

        public ShareVolume build(){
            return new ShareVolume(this);
        }

        public ShareVolume build(String row){
            if(null!=row){
                String[] cols = row.split(",");
                this.symbol=cols[0];
                this.industry=cols[2];
                this.shares=Integer.valueOf(cols[3]);
                return new ShareVolume(this);
            }
            return null;
        }
    }
}
