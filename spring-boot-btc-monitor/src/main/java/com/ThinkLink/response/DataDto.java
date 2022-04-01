package com.ThinkLink.response;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class DataDto {
    //@JsonIgnore
    private Integer id;

    String timestamp;

    Long price;

    String coin;

    @JsonIgnore
    String date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "{" +
                "timestamp=" + timestamp +
                ", price=" + price +
                ", coin='" + coin + '\'' +
                '}';
    }
}
