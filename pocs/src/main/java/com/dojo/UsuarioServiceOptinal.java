package com.dojo;

import java.util.Optional;

public class UsuarioServiceOptinal {

    public String findCidadeByUser(Usuario user){

        return Optional.ofNullable(user)
                .flatMap(Usuario::getEndereco)
                .flatMap(Endereco::getCidade)
                .map(Cidade::getNome)
                .orElse("desconhecida");
    }

}


class Usuario{

    String nome;
    Optional<Endereco> endereco;

    public Usuario(String nome, Endereco endereco){
        this.nome = nome;
        this.endereco = Optional.ofNullable(endereco);
    }

    public Optional<Endereco> getEndereco(){
        return endereco;
    }
}

class Endereco{

    String cep;
    Optional<Cidade> cidade;

    public Endereco(String cep, Cidade cidade){
        this.cep = cep;
        this.cidade = Optional.ofNullable(cidade);
    }

    public Optional<Cidade> getCidade(){
        return cidade;
    }
}

class Cidade{
    String nome;

    public Cidade(String nome){
        this.nome = nome;
    }

    public String getNome(){
        return nome;
    }
}
