package purchase;

import java.util.Objects;

public class RewardAccumulator {

    private String customerId;
    private double purchaseTotal;
    private int currentRewardPoints;
    private int totalRewardPoints;

    RewardAccumulator(String customerId,double purchaseTotal,int rewardPoints){
        this.customerId=customerId;
        this.purchaseTotal=purchaseTotal;
        this.currentRewardPoints=rewardPoints;
        this.totalRewardPoints=rewardPoints;

    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public double getPurchaseTotal() {
        return purchaseTotal;
    }

    public void setPurchaseTotal(double purchaseTotal) {
        this.purchaseTotal = purchaseTotal;
    }

    public int getCurrentRewardPoints() {
        return currentRewardPoints;
    }

    public void setCurrentRewardPoints(int currentRewardPoints) {
        this.currentRewardPoints = currentRewardPoints;
    }

    public int getTotalRewardPoints() {
        return totalRewardPoints;
    }

    public void setTotalRewardPoints(int totalRewardPoints) {
        this.totalRewardPoints = totalRewardPoints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RewardAccumulator)) return false;
        RewardAccumulator that = (RewardAccumulator) o;
        return Double.compare(that.getPurchaseTotal(), getPurchaseTotal()) == 0 &&
                getCurrentRewardPoints() == that.getCurrentRewardPoints() &&
                getTotalRewardPoints() == that.getTotalRewardPoints() &&
                Objects.equals(getCustomerId(), that.getCustomerId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomerId(), getPurchaseTotal(), getCurrentRewardPoints(), getTotalRewardPoints());
    }

    public void addRewardPoints(int previousPoints){
        this.currentRewardPoints += previousPoints;
    }

    public static final class Builder{
        private String customerId;
        private double purchaseTotal;
        private int currentRewardPoints;

        Builder(Purchase purchase){
            this.customerId=purchase.getCustomerId();
            this.purchaseTotal=purchase.getQuantity()* purchase.getPrice();
            this.currentRewardPoints=(int)purchaseTotal;
        }

        RewardAccumulator build(){
            return new RewardAccumulator(customerId,purchaseTotal,currentRewardPoints);
        }

    }
}
