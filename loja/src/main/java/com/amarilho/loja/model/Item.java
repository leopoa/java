package com.amarilho.loja.model;

public class Item {

    public static final int ITEM_ID = 0;
    public static final int ITEM_QTD = 1;
    public static final int ITEM_PRICE = 2;

    private String id;
    private Integer qtd;
    private Double price;

    public Item(String[] itensFields){
        this.id = itensFields[ITEM_ID];
        this.qtd = new Integer(itensFields[ITEM_QTD]);
        this.price = new Double(itensFields[ITEM_PRICE]);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getQtd() {
        return qtd;
    }

    public void setQtd(Integer qtd) {
        this.qtd = qtd;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
