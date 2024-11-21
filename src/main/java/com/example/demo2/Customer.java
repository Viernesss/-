package com.example.demo2;

public class Customer implements Comparable<Customer>{
    private String name;
    private String imageUrl1;
    private String imageUrl2;

    public Customer(String name, String imageUrl1, String imageUrl2) {
        this.name = name;
        this.imageUrl1 = imageUrl1;
        this.imageUrl2 = imageUrl2;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl1() {
        return imageUrl1;
    }

    public String getImageUrl2() {
        return imageUrl2;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageUrl1(String imageUrl1) {
        this.imageUrl1 = imageUrl1;
    }

    public void setImageUrl2(String imageUrl2) {
        this.imageUrl2 = imageUrl2;
    }

    public int compareTo(Customer other) {
        return this.name.compareTo(other.getName());
    }
}
