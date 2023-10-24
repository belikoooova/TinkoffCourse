package edu.hw3;

import edu.hw3.task6.Stock;
import edu.hw3.task6.StockMarket;
import edu.hw3.task6.PrioritizedStockMarket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task6Test {
    @Test
    @DisplayName("One element")
    void oneElementTest() {
        // given
        StockMarket stockMarket = new PrioritizedStockMarket();
        stockMarket.add(new Stock(100, "Tinkoff", "TCSGDR"));

        // when
        var result = stockMarket.mostValuableStock();

        // then
        assertThat(result.getTitle()).isEqualTo("Tinkoff");
    }

    @Test
    @DisplayName("Few elements")
    void fewElementsTest() {
        // given
        StockMarket stockMarket = new PrioritizedStockMarket();
        stockMarket.add(new Stock(50, "Yandex", "YNDX"));
        stockMarket.add(new Stock(100, "Tinkoff", "TCSGDR"));
        stockMarket.add(new Stock(70, "Alpha", "ACBr"));

        // when
        var result = stockMarket.mostValuableStock();

        // then
        assertThat(result.getTitle()).isEqualTo("Tinkoff");
    }

    @Test
    @DisplayName("Few elements with deletion")
    void fewElementsWithDeletionTest() {
        // given
        StockMarket stockMarket = new PrioritizedStockMarket();
        stockMarket.add(new Stock(50, "Yandex", "YNDX"));
        stockMarket.add(new Stock(100, "Tinkoff", "TCSGDR"));
        stockMarket.add(new Stock(70, "Alpha", "ACBr"));

        // when
        stockMarket.remove(stockMarket.mostValuableStock());
        var result = stockMarket.mostValuableStock();

        // then
        assertThat(result.getTitle()).isEqualTo("Alpha");
    }

    @Test
    @DisplayName("Few elements with same price")
    void fewElementsWitSamePrice() {
        // given
        StockMarket stockMarket = new PrioritizedStockMarket();
        stockMarket.add(new Stock(100, "Yandex", "YNDX"));
        stockMarket.add(new Stock(100, "Tinkoff", "TCSGDR"));
        stockMarket.add(new Stock(100, "Alpha", "ACBr"));

        // when
        var result = stockMarket.mostValuableStock();

        // then
        assertThat(result.getTitle()).isEqualTo("Yandex");
    }
}
