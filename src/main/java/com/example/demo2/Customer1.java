package com.example.demo2;

public class Customer1 implements Comparable<Customer1>{
    private String data1;
    private String name1;
    private String imageUrl;

    public Customer1(String data, String name, String imageUrl) {
        this.data1 = data;
        this.name1 = name;
        this.imageUrl = imageUrl;
    }

    public String getData1() {
        return data1;
    }

    public String getName1() {
        return name1;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setData1(String data) {
        this.data1 = data;
    }

    public void setName1(String name) {
        this.name1 = name;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int compareTo(Customer1 other) {
        return this.name1.compareTo(other.getName1());
    }

}
