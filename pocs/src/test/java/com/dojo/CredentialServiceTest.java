package com.dojo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

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
}
