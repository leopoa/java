package com.dojo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class DicitionaryServiceTest {

    DictionaryService service;

    @Before
    public void setUp(){
        service = new DictionaryService();
    }

    @Test
    public void getUniqueWordsInSentence(){
        String sentence = "Teste     azul      teste     preto    ";
        Assert.assertEquals(3,  service.getUniqueWordsInSentence(sentence).size());
    }

    @Test
    public void groupEqualWords(){
        String sentence = "Teste azul teste preto azul TESTE";
        Map<String, List<Dictionary>> map = service.groupEqualWordsAndCount(sentence);

        Assert.assertEquals(2,  map.get("azul").size());
        Assert.assertEquals(1,  map.get("preto").size());
        Assert.assertEquals(3,  map.get("teste").size());
    }


}
