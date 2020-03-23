package model.state;

import model.Sales;

public class Occurances {

    String country;
    String region;
    int unitsSOld;
    int occurancesByRegion;
//    int regionOccurances;

    public Occurances(Builder builder){
        this.country=builder.country;
        this.region=builder.region;
        this.unitsSOld=builder.unitsSOld;
        this.occurancesByRegion=builder.occurancesByRegion;
//        this.regionOccurances=builder.regionOccurances;

    }
    @Override
    public String toString() {
        return "Occurances{" +
                "country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", totalCountryOccurances=" + unitsSOld +
                ", occurancesByRegion=" + occurancesByRegion +
//                ", regionOccurances=" + regionOccurances +
                '}';
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getTotalCountryOccurances() {
        return unitsSOld;
    }

    public int getOccurancesByRegion() {
        return occurancesByRegion;
    }

    public void setOccurancesByRegion(int occurancesByRegion) {
        this.occurancesByRegion = occurancesByRegion;
    }

//    public int getRegionOccurances() {
//        return regionOccurances;
//    }
//
//    public void setRegionOccurances(int regionOccurances) {
//        this.regionOccurances = regionOccurances;
//    }

    public static Builder newBuilder(){
        return new Builder();
    }

    public static Builder builder(Sales sales){
        return new Builder(sales);
    }

    public void addCountryOccuraces(int occuracesSoFar) {
        this.unitsSOld+=occuracesSoFar;
    }

    public static final class Builder{

        String country;
        String region;
        int unitsSOld;
        int occurancesByRegion;
        int regionOccurances;

        public Builder(){}

        private Builder(Sales sales){
            this.country= sales.getCountry();
            this.region=sales.getRegion();
            this.unitsSOld=sales.getUnitsSold();
          //  this.regionOccurances=occurances.regionOccurances;
        }

        public Occurances build(){
            return new Occurances(this);
        }
    }
}
