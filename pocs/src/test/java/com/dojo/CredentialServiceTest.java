package com.dojo;

import com.google.common.collect.Maps;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class CredentialServiceTest {

    CredentialService service;

    @Before
    public void setUp(){
        service = new CredentialService();
    }

    @Test
    public void getOnlyPropertiesWithKeyPermission(){
        Credential oddCredential = new Credential(Arrays.asList(new Property("um", true),
                new Property("tres", false),
                new Property("cinco", true)));

        Credential evenCredential = new Credential(Arrays.asList(new Property("dois", true),
                new Property("quatro", false),
                new Property("seis", true)));

        Assert.assertEquals(4,  service.getOnlyPropertiesWithKeyPermission(Arrays.asList(oddCredential, evenCredential)).size());
    }


    @Test
    public void intersectTwoListProperty(){
        Credential oddCredential = new Credential(Arrays.asList(new Property("um", true),
                new Property("tres", false),
                new Property("cinco", true)));

        Assert.assertEquals(1,  service.getRepeatedPropertiesIntersect(Arrays.asList(oddCredential), Arrays.asList(new Property("um", true))).size());
        Assert.assertEquals(0,  service.getRepeatedPropertiesIntersect(Arrays.asList(oddCredential), Arrays.asList(new Property("um", false))).size());
        Assert.assertEquals(0,  service.getRepeatedPropertiesIntersect(Arrays.asList(oddCredential), Arrays.asList(new Property("dez", true))).size());
    }

    @Test
    public void anyEmptyPropertyByPerson(){
        Map<String, List<Property>> map = new HashMap<>();
        map.put("jose", new ArrayList<>());

        Assert.assertTrue(service.anyEmptyPropertyByPerson(map));
    }
}
