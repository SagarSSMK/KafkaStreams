package mapper.streamjoiner;

import model.Sales;
import model.joiners.CorrelatedSales;
import org.apache.kafka.streams.kstream.ValueJoiner;

public class SalesJoiner implements ValueJoiner<Sales, Sales, CorrelatedSales> {

    @Override
    public CorrelatedSales apply(Sales onlineSales, Sales offlineSales) {
        CorrelatedSales.Builder builder = CorrelatedSales.builder();

        //    long orderIdOffline= offlineSales!=null? offlineSales.getOrderId():0;
        //   long orderIdOnline= onlineSales!=null? onlineSales.getOrderId():0;

        long orderId;
        if (onlineSales != null) {
            orderId = onlineSales.getOrderId();
        } else {
            orderId = offlineSales.getOrderId();
        }
        int onlineUnitsSold = onlineSales != null ? onlineSales.getUnitsSold() : 0;
        double onlineUnitPrice = onlineSales != null ? onlineSales.getUnitPrice() : 0.0;
        int offlineUnitSold = offlineSales != null ? offlineSales.getUnitsSold() : 0;
        double offlineUnitPrice = offlineSales != null ? offlineSales.getUnitPrice() : 0.0;

        double offlineProfit = offlineSales != null ? offlineSales.getTotalProfit() : 0.0;
        double onlineProfit = onlineSales != null ? onlineSales.getTotalProfit() : 0.0;

        double totalPorfit = offlineProfit + onlineProfit;

        CorrelatedSales sales = builder.orderId(orderId).offlineUnitPrice(offlineUnitPrice)
                .offlineUnitSold(offlineUnitSold)
                .onlineUnitPrice(onlineUnitPrice)
                .onlineUnitSold(onlineUnitsSold)
                .totalOfflineOnlineProfit(totalPorfit)
                .build();

        return sales;
    }
}
