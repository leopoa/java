package com.dojo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Optional;

public class UsuarioOptionalTest {

    UsuarioOptinal usuario;

    @Before
    public void setUp(){
        usuario = new UsuarioOptinal();
    }

    @Test
    public void findCidadeByUserValid(){
        Cidade cidade = new Cidade("POA");
        Endereco end = new Endereco("99999-999", Optional.ofNullable(cidade));
        Usuario user = new Usuario("JOAO", Optional.ofNullable(end));

        Assert.assertEquals("POA",  usuario.findCidadeByUser(user));
    }

    @Test
    public void findCidadeByUserUnknow(){
        Usuario user = new Usuario("JOAO", Optional.empty());
        Assert.assertEquals("desconhecida",  usuario.findCidadeByUser(user));
    }
}
