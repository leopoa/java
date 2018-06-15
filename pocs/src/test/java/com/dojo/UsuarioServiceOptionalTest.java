package com.dojo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UsuarioServiceOptionalTest {

    UsuarioServiceOptinal service;

    @Before
    public void setUp(){
        service = new UsuarioServiceOptinal();
    }

    @Test
    public void findCepByUserValid(){
        Cidade cidade = new Cidade("POA");
        Endereco end = new Endereco("99999-999", cidade);
        Usuario user = new Usuario("JOAO", end);

        Assert.assertEquals("POA",  service.findCidadeByUser(user));
    }

    @Test
    public void throwsExceptionThanUserWithoutEndereco() {
        try {
            service.findCepByUser(new Usuario("JOAO", null));
            Assert.fail("Esperado exception ao ter usuario sem endereco cadastrado");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IllegalArgumentException);
        }
    }

    @Test
    public void findCidadeByUserValid(){
        Endereco end = new Endereco("99999-999", null);
        Usuario user = new Usuario("JOAO", end);

        Assert.assertEquals("99999-999",  service.findCepByUser(user));
    }
}
