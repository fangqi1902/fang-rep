package com.sxt.bus.domain;

public class Car {
    private String carnumber;//牌照

    private String cartype;//汽车类型

    private String color;//颜色

    private Double price;//价格

    private Double rentprice;//租金

    private Double deposit;//押金

    private Integer isrenting;//是否已出租

    private String description;//描述

    private String carimg;//图片

    public String getCarnumber() {
        return carnumber;
    }

    public void setCarnumber(String carnumber) {
        this.carnumber = carnumber == null ? null : carnumber.trim();
    }

    public String getCartype() {
        return cartype;
    }

    public void setCartype(String cartype) {
        this.cartype = cartype == null ? null : cartype.trim();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color == null ? null : color.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getRentprice() {
        return rentprice;
    }

    public void setRentprice(Double rentprice) {
        this.rentprice = rentprice;
    }

    public Double getDeposit() {
        return deposit;
    }

    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }

    public Integer getIsrenting() {
        return isrenting;
    }

    public void setIsrenting(Integer isrenting) {
        this.isrenting = isrenting;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getCarimg() {
        return carimg;
    }

    public void setCarimg(String carimg) {
        this.carimg = carimg == null ? null : carimg.trim();
    }
}