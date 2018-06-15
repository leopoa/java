package com.dojo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UsuarioServiceOptionalTest {

    UsuarioServiceOptinal usuario;

    @Before
    public void setUp(){
        usuario = new UsuarioServiceOptinal();
    }

    @Test
    public void findCidadeByUserValid(){
        Cidade cidade = new Cidade("POA");
        Endereco end = new Endereco("99999-999", cidade);
        Usuario user = new Usuario("JOAO", end);

        Assert.assertEquals("POA",  usuario.findCidadeByUser(user));
    }

    @Test
    public void findCidadeByUserUnknow(){
        Usuario user = new Usuario("JOAO", null);
        Assert.assertEquals("desconhecida",  usuario.findCidadeByUser(user));
    }
}
