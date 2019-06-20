package com.my_shop.entity;

public class Address {
    private Integer id;

    private Integer customerId;

    private String province;

    private String city;

    private String distric;

    private String detialAddress;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getDistric() {
        return distric;
    }

    public void setDistric(String distric) {
        this.distric = distric == null ? null : distric.trim();
    }

    public String getDetialAddress() {
        return detialAddress;
    }

    public void setDetialAddress(String detialAddress) {
        this.detialAddress = detialAddress == null ? null : detialAddress.trim();
    }
}