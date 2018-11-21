package com.myexperiments.springmvc.domain.model;

/*
* It is not necessary to annotate Item with @Document, nor is it necessary to annotate one of its fields with @Id.
* That’s because you will never persist an Item as an independent document. It will always be a member of the Order
* document’s Item list and a nested element in that document.
*/
public class Item {

    private Long id;

    private Order order;

    private String product;

    private double price;

    private int quantity;

    public Order getOrder() {
        return order;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

}
