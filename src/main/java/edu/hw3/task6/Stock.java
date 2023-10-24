package edu.hw3.task6;

public class Stock {
    private int price;
    private String title;
    private String ticker;

    public Stock(int price, String title, String ticker) {
        this.price = price;
        this.title = title;
        this.ticker = ticker;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }
}
