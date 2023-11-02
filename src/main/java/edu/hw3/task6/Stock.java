package edu.hw3.task6;

public record Stock(int price, String title, String ticker) {

    public Stock(int price, String title, String ticker) {
        this.price = price;
        this.title = title;
        this.ticker = ticker;
    }

    public int getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public String getTicker() {
        return ticker;
    }
}
