package com.dojo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

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
            service.findCepByUser(new Usuario("JOAO", new Endereco()));
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

    @Test
    public void findFirstUserByNameValid(){
        List<Usuario> users = Arrays.asList(new Usuario("Juca", 30),
                                            new Usuario("Jose", 33),
                                            new Usuario("Joao", 31));

        Assert.assertTrue(service.findFirstUserByNameStartWith(users, "J").isPresent());
        Assert.assertEquals("Joao",  service.findFirstUserByNameStartWith(users, "J").get());
    }

    @Test
    public void findFirstUserByNameEmptyResult(){
        List<Usuario> users = Arrays.asList(new Usuario("Juca", 30));
        Assert.assertFalse(service.findFirstUserByNameStartWith(users, "L").isPresent());
    }

    @Test
    public void findOlderUsuario(){
        List<Usuario> users = Arrays.asList(new Usuario("Juca", 30),
                new Usuario("Jose", 33),
                new Usuario("Joao", 31));

        Assert.assertEquals("Jose",  service.findOlderUser(users).get());
    }

    @Test
    public void findYoungUsuario(){
        List<Usuario> users = Arrays.asList(new Usuario("Juca", 32),
                new Usuario("Jose", 33),
                new Usuario("Joao", 31));

        Assert.assertEquals("Joao",  service.findYoungUser(users).get());
    }
}
