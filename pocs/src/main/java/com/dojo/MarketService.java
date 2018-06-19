package com.dojo;

import java.math.BigDecimal;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Comparator.comparingDouble;
import static java.util.stream.Collectors.*;

public class MarketService {

    public Map<String, Double> getAverageByType(List<Product> products){

        return products.stream()
                .collect(groupingBy(Product::getType, averagingDouble(Product::getPrice)));
    }

    public Map<String, Optional<Product>> getMaxPriceGroupByType(List<Product> products){
         return products.stream()
                .collect(groupingBy(Product::getType,
                        maxBy(comparingDouble(Product::getPrice))));
    }

    public Map<String, DoubleSummaryStatistics> calculateSummary(List<Product> products){
        return products.stream()
                .collect(groupingBy(Product::getType,
                        summarizingDouble(Product::getPrice)));
    }

    public Optional<Double> sumPriceProduct(List<Product> products){
        return products.stream()
                .map(Product::getPrice)
                .reduce(Double::sum);
    }

}

class Product{

    private String name;
    private String type;
    private Double price;

    public Product(String name, String type, Double price){
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public String getType(){
        return type;
    }

    public String getName(){
        return name;
    }

    public Double getPrice(){
        return price;
    }
}