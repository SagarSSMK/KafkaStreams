package processor;

import model.Sales;

import org.apache.kafka.streams.processor.AbstractProcessor;

public class SalesMethodTransfer extends AbstractProcessor<String, Sales> {

    private String offlineSales;
    private String onlineSales;

    public SalesMethodTransfer(String offlineSales,String onlineSales){
        this.offlineSales=offlineSales;
        this.onlineSales=onlineSales;
    }

    @Override
    public void process(String key, Sales value) {

    }
}
