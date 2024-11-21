package com.example.demo2;

public class Customeract {
    private String name2;
    private String adres;
    private String soob;

    public Customeract(String name2, String adres, String soob) {
        this.name2 = name2;
        this.adres = adres;
        this.soob = soob;
    }

    public String getName2() {
        return name2;
    }

    public String getAdres() {
        return adres;
    }

    public String getSoob() {
        return soob;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public void setSoob(String soob) {
        this.soob = soob;
    }
}
