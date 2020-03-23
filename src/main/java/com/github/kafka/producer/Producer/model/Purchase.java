package com.github.kafka.producer.Producer.model;

import java.util.Date;
import java.util.Objects;

public class Purchase {

    private String firstName;
    private String lastName;
    private String customerId;
    private String creditCardNumber;
    private String itemPurchased;
    private String department;
    private String employeeId;
    private int quantity;
    private double price;
    private Date purchaseDate;
    private String zipCode;
    private String storeId;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getItemPurchased() {
        return itemPurchased;
    }

    public void setItemPurchased(String itemPurchased) {
        this.itemPurchased = itemPurchased;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Purchase)) return false;
        Purchase purchase = (Purchase) o;

        if (quantity != purchase.quantity) return false;
        if (Double.compare(purchase.price, price) != 0) return false;
        if (!Objects.equals(firstName, purchase.firstName)) return false;
        if (!Objects.equals(lastName, purchase.lastName)) return false;
        if (!Objects.equals(customerId, purchase.customerId)) return false;
        if (!Objects.equals(creditCardNumber, purchase.creditCardNumber)) return false;
        if (!Objects.equals(itemPurchased, purchase.itemPurchased)) return false;
        if (!Objects.equals(department, purchase.department)) return false;
        if (!Objects.equals(employeeId, purchase.employeeId)) return false;
        if (!Objects.equals(zipCode, purchase.zipCode)) return false;
        return getStoreId().equals(purchase.getStoreId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(),
                getCustomerId(), getCreditCardNumber(),
                getItemPurchased(), getDepartment(),
                getEmployeeId(), getQuantity(), getPrice(),
                getPurchaseDate(), getZipCode(), getStoreId());
    }

    private Purchase(Builder builder){
        firstName= builder.firstName;
        lastName= builder.lastName;
        customerId= builder.customerId;
        creditCardNumber= builder.creditCardNumber;
        itemPurchased= builder.itemPurchased;
        department= builder.department;
        employeeId = builder.employeeId;
        quantity= builder.quantity;
        price= builder.price;
        purchaseDate= builder.purchaseDate;
        zipCode= builder.zipCode;
        storeId= builder.storeId;
    }

    public static final class Builder{
        private String firstName;
        private String lastName;
        private String customerId;
        private String creditCardNumber;
        private String itemPurchased;
        private String department;
        private String employeeId;
        private int quantity;
        private double price;
        private Date purchaseDate;
        private String zipCode;
        private String storeId;

        private static final String CREDIT_CARD_NUMBER_REPLACEMENT="xxxx-xxxx-xxxx-";

        private Builder(){}

        private Builder firstName(String val){
            firstName=val;
            return this;
        }
        private  Builder lastName(String val){
            lastName=val;
            return  this;
        }

        private Builder customerId(String customerId){
            this.customerId=customerId;
            return  this;
        }

        private  Builder creditCardNumber(String creditCardNumber){
            this.creditCardNumber= creditCardNumber;
            return  this;
        }

        private Builder itemPurchased(String itemPurchased){
            this.itemPurchased= itemPurchased;
            return this;
        }

        private  Builder department(String department){
            this.department= department;
            return  this;
        }

        private Builder employeeId(String employeeId){
            this.employeeId=employeeId;
            return  this;
        }

        private Builder quantity(int quantity){
            this.quantity=quantity;
            return  this;
        }

        private  Builder price(double price){
            this.price=price;
            return this;
        }

        private  Builder purchaseDate(Date purchaseDate){
            this.purchaseDate=purchaseDate;
            return  this;
        }

        private Builder zipCode(String zipCode){
            this.zipCode=zipCode;
            return  this;
        }

        public Purchase build(){
            return new Purchase(this);
        }
    }
}
