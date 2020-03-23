package model.aggregation.avg;

import model.Sales;

public class AvgValue {

    public int count=0;
    public double sum=0.0;

    public AvgValue(int count,double sum){
        this.sum+=sum;
        this.count+=count;
    }

    public int getCount(){
        return count;
    }

    public double getSum(){
        return sum;
    }


}
