package com.mihir.order_service;

public class Order {
    private String id;
    private String product;
    private int quantity;


    Order(){
    }

    Order(String id, String product, int quantity) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
    }

    public String getId(){
        return this.id;
    }

    public String getProduct(){
        return this.product;
    }
    public int getQuantity(){
        return this.quantity;
    }

    public void setId(String id){
        this.id = id;
    }
    public void setProduct(String product){
        this.product = product;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    @Override
    public String toString() {
        return "Order{id='" + id + "', product='" + product + "', quantity=" + quantity + "}";
    }

}