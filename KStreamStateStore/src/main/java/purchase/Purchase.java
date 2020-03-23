package purchase;

import org.apache.flume.annotations.InterfaceAudience;

import java.util.Collections;
import java.util.List;

public class Purchase {

    String name;
    String customerId;
    String item;
    int quantity;
    String creditCardumber;
    double price;
    String zipCode;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getCreditCardumber() {
        return creditCardumber;
    }

    public void setCreditCardumber(String creditCardumber) {
        this.creditCardumber = creditCardumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    Purchase(Builder builder){
        this.name= builder.name;
        this.customerId=builder.customerId;
        this.item=builder.item;
        this.price=builder.price;
        this.creditCardumber=builder.creditCardumber;
        this.zipCode=builder.zipCode;
    }

    public static  Builder newBuilder(){
        return new Builder();
    }
   public static final class Builder{
        String name;
       String customerId;
       String item;
       int quantity;
       double price;
       String creditCardumber;
       String zipCode;

       Builder withName(String val){
           this.name=val;
           return  this;
       }
       Builder withCustomerId(String id){
           this.customerId=id;
           return this;
       }

       Builder withPrice(double price){
           this.price=price;
           return  this;
       }


       Builder withCreditCardNumber(String number){
           this.creditCardumber=Creditcard(number);
           return this;
       }

       Builder withNumberOfItems(String items){
           this.item=items;
           return this;
       }
       Builder withQuantity(int val){
           this.quantity=val;
           return this;
       }

       private String Creditcard(String number) {
           String data = number.split("-")[3];
           String newNumber = data+"XXXX";
           return newNumber;
       }

       Builder withZipCode(String zipCode){
           this.zipCode= zipCode;
           return this;
       }

       Purchase build(){
           return new Purchase(this);
       }

       public Purchase build(String row){
           if(null!=row){
               String[] cols= row.split(",");
               this.name= cols[0];
               this.customerId=cols[1];
               this.item= cols[2];
               this.quantity=Integer.valueOf(cols[3]);
               this.creditCardumber=cols[4];
               this.price=Double.valueOf(cols[5]);
               this.zipCode=cols[6];
               return new Purchase(this);
           }
           return  null;
       }

   }
}
