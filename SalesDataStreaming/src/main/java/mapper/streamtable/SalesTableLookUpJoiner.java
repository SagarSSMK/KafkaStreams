package mapper.streamtable;

import config.Configuration;
import model.Sales;
import model.aggregation.HigherAndLowereResults;
import org.apache.kafka.streams.kstream.ValueJoiner;
import model.streamtablejoin.LookUpResults;

public class SalesTableLookUpJoiner implements ValueJoiner<Sales, HigherAndLowereResults,LookUpResults> {

    @Override
    public LookUpResults apply(Sales sales, HigherAndLowereResults value) {
        LookUpResults.Builder builder= LookUpResults.Builder();


        int count=-0;
        String stringSold= Configuration.LEAST_NUMBERS;
        String stringCost=Configuration.LEAST_NUMBERS;
        String stringPrice=Configuration.LEAST_NUMBERS;
        String stringTotalProfit=Configuration.LEAST_NUMBERS;
        String stringTotalRevenue=Configuration.LEAST_NUMBERS;
        String stringTotalCost=Configuration.LEAST_NUMBERS;

        if(sales==null)
            System.out.println("Sales is null");

        if(value==null){
            count=count+1;
            System.out.println("Values which is High and Low results are null"+count);
        }

        if(null!=sales&& null!=value){
            if(value.getAvgUnitsSold()<sales.getUnitsSold())
                stringSold=Configuration.CONSISTENCY_NUMBERS;
            if(value.getAvgUnitCost()<sales.getUnitCost())
                stringCost=Configuration.CONSISTENCY_NUMBERS;
            if(value.getAvgUnitPrice()<sales.getUnitPrice())
                stringPrice=Configuration.CONSISTENCY_NUMBERS;
            if(value.getAvgUnitPrice()<sales.getTotalProfit())
                stringTotalProfit=Configuration.CONSISTENCY_NUMBERS;
            if(value.getAvgTotalRevenue()<sales.getTotalRevenue())
                stringTotalRevenue=Configuration.CONSISTENCY_NUMBERS;
            if(value.getAvgTotalCost()<sales.getTotalCost())
                stringTotalCost=Configuration.CONSISTENCY_NUMBERS;
        }


        return builder.totalCost(stringTotalCost).totalProfit(stringTotalProfit).unitPrice(stringPrice)
                .totalRevenue(stringTotalRevenue).unitsCost(stringCost).unitsSold(stringSold).build();
    }
}
