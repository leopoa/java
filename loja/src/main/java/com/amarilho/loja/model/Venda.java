package com.amarilho.loja.model;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Venda {

    public static final int TYPE = 0;
    public static final int ID = 1;
    public static final int ITENS = 2;
    public static final int VENDEDOR = 3;

    public static final String LINE_SPLIT = "ç";
    public static final String LIST_ITEM_SPLIT = ",";
    public static final String ITEM_SPLIT = "-";


    private String type;
    private String id;
    private List<Item> itens;
    private String vendedor;
    private Double total;

    public Venda(){
    }

    public Venda(String line){
        String[] fields = line.split(LINE_SPLIT);

        this.type = fields[TYPE];
        this.id = fields[ID];
        this.vendedor = fields[VENDEDOR];
        this.itens = parserItens(getOnlyItens(fields[ITENS]));
        this.total = sumTotal(this.itens);
    }

    public List<Item> parserItens(String[] listItens){
        ArrayList<Item> itens = new ArrayList<>();

        if(listItens == null) return itens;

        for (String item: listItens) {
            Item newItem = new Item(item.split(ITEM_SPLIT));
            itens.add(newItem);
        }

        return itens;
    }

    public Double sumTotal(List<Item> itens){
        double resultTotal = 0.0;

        if(CollectionUtils.isEmpty(itens)) return resultTotal;

        for (Item item: itens) {
            resultTotal += (item.getPrice() * item.getQtd());
        }
        return resultTotal;
    }

    public String[] getOnlyItens(String itemField){
        if(StringUtils.isEmpty(itemField)) return null;

        return itemField.substring(1, itemField.length()-1).split(LIST_ITEM_SPLIT);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public Double getTotal() {
        return total;
    }
}
