package com.ThinkLink.entities;

import javax.persistence.*;

@Entity
@Table(name = "data")
public class AssetHistoricalData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "timestamp")
    String timestamp;

    @Column(name = "price")
    Long price;

    @Column(name = "coin")
    String coin;

    @Column(name = "date")
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
