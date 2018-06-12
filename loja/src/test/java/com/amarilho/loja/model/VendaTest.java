package com.amarilho.loja.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class VendaTest {

    @Test
    public void parserItens() {
        List<Item> itens = new Venda().parserItens(new String[]{"1-10-100", "2-30-2.50", "3-40-3.10"});
        Assert.assertEquals(3,itens.size());
        Assert.assertEquals("1",itens.get(0).getId());
        Assert.assertEquals("2",itens.get(1).getId());
        Assert.assertEquals("3",itens.get(2).getId());
    }

    @Test
    public void parserItensWhenNullValue() {
        Assert.assertEquals(0,new Venda().parserItens(null).size());

    }

    @Test
    public void getOnlyItens() {
        Assert.assertEquals(3,new Venda().getOnlyItens("[1-10-100,2-30-2.50,3-40-3.10]").length);
    }

    @Test
    public void getOnlyItensWhenNullValue() {
        Assert.assertNull(new Venda().getOnlyItens(null));
    }

    @Test
    public void sumTotalItens() {
        List<Item> listItens = Arrays.asList(
                new Item(new String[]{"1", "1", "10.2"}),
                new Item(new String[]{"2", "2", "10.0"}));
        Assert.assertEquals(new Double("30.2"),new Venda().sumTotal(listItens));
    }

    @Test
    public void sumTotalItensWhenNullItens() {
        Assert.assertEquals(new Double("0.0"),new Venda().sumTotal(null));
    }

    @Test
    public void sumTotalItensWhenEmptyItens() {
        Assert.assertEquals(new Double("0.0"),new Venda().sumTotal(Arrays.asList()));
    }
}