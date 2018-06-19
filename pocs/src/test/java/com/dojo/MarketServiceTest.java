package com.dojo;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class MarketServiceTest {

    MarketService service;
    List<Product> products;

    @Before
    public void setUp(){
        service  = new MarketService();
        products = Arrays.asList(
                new Product("coca", "drink", new Double(5.50)),
                new Product("cerveja", "drink", new Double(7.10)),
                new Product("arroz", "food", new Double(3.30)),
                new Product("feijao", "food", new Double(4.15))
        );
    }

    @Test
    public void getAverageByType(){
        Map<String, Double> map = service.getAverageByType(products);
        assertEquals(new Double(3.725), map.get("food"));
        assertEquals(new Double(6.3), map.get("drink"));
    }

    @Test
    public void getMaxPriceGroupByType(){
        Map<String, Optional<Product>> map = service.getMaxPriceGroupByType(products);
        assertEquals("cerveja", map.get("drink").get().getName());
        assertEquals("feijao", map.get("food").get().getName());
    }

    @Test
    public void calculateSummary(){
        Map<String, DoubleSummaryStatistics> map = service.calculateSummary(products);
        assertEquals(2, map.get("drink").getCount());
        assertEquals(5.5d , map.get("drink").getMin(), 0.0);
        assertEquals(7.1d, map.get("drink").getMax(), 0.0);
        assertEquals(12.6d, map.get("drink").getSum(), 0.0);
    }

    @Test
    public void sumPriceProduct(){
        assertEquals(20.049999999999997d , service.sumPriceProduct(products).get(), 0.0);
    }

}
