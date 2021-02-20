package com.mastery.testspringproductmicroservice.dtos.response;

public class NumberOfProductsInYearDto {
    private String country;
    private int total;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
