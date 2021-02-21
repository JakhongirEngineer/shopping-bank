package com.mastery.testspringproductmicroservice.dtos.response;

public class NumberOfProductsInYearDtoImpl implements NumberOfProductsInYearDto{
    private String country;
    private Integer total;

    @Override
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
