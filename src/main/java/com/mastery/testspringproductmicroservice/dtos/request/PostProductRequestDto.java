package com.mastery.testspringproductmicroservice.dtos.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PostProductRequestDto {
    private String name;
    private String description;
    private BigDecimal price;
    private String photo;
    private Integer categoryId;

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public BigDecimal getPrice() {
//        return price;
//    }
//
//    public void setPrice(BigDecimal price) {
//        this.price = price;
//    }
//
//    public String getPhoto() {
//        return photo;
//    }
//
//    public void setPhoto(String photo) {
//        this.photo = photo;
//    }
//
//    public int getCategoryId() {
//        return categoryId;
//    }
//
//    public void setCategoryId(int categoryId) {
//        this.categoryId = categoryId;
//    }
//
//    @Override
//    public String toString() {
//        return "PostProductRequestDto{" +
//                "name='" + name + '\'' +
//                ", description='" + description + '\'' +
//                ", price=" + price +
//                ", photo='" + photo + '\'' +
//                ", categoryId=" + categoryId +
//                '}';
//    }
}
