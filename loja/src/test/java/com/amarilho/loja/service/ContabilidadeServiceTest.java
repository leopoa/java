package com.amarilho.loja.service;

import com.amarilho.loja.model.Venda;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;

public class ContabilidadeServiceTest {

    private ContabilidadeService service;

    @Before
    public void setUp(){
        service = new ContabilidadeService(new FileService());

        service.setExtensao(".dat");
        service.setPathIn("src/test/resources/data/in/");
        service.setPathOut("src/test/resources/data/in/");
        service.setVendedor("001");
        service.setCliente("002");
        service.setVenda("003");
    }

    @Test
    public void init() {
        Assert.assertTrue(service.init());
    }

    @Test
    public void summarizeVendasOrderByTotal() {
        List<Venda> result = service.summarizeVendasOrderByTotal(Paths.get("src/test/resources/dados.dat"));
        Assert.assertEquals(2, result.size());
    }

    @Test
    public void summarizeTotalByType() {
        Assert.assertEquals(new Long(2), service.summarizeTotalByType(Paths.get("src/test/resources/dados.dat"),"001"));
    }


}