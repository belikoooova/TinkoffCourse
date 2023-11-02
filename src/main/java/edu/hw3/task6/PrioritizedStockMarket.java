package edu.hw3.task6;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PrioritizedStockMarket implements StockMarket {
    private PriorityQueue<Stock> queue = new PriorityQueue<>(new StockComparator());

    @Override
    public void add(Stock stock) {
        queue.add(stock);
    }

    @Override
    public void remove(Stock stock) {
        queue.remove(stock);
    }

    @Override
    public Stock mostValuableStock() {
        return queue.remove();
    }

    class StockComparator implements Comparator<Stock> {

        @Override
        public int compare(Stock o1, Stock o2) {
            return o2.getPrice() - o1.getPrice();
        }
    }
}
