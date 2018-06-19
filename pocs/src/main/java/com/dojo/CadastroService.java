package com.dojo;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class CadastroService {

    public String findCidadeByUser(Usuario user){
        return Optional.ofNullable(user)
                .flatMap(Usuario::getEndereco)
                .flatMap(Endereco::getCidade)
                .map(Cidade::getNome)
                .orElse("desconhecida");
    }

    public String findCepByUser(Usuario user){
        return Optional.ofNullable(user)
                .flatMap(Usuario::getEndereco)
                .map(Endereco::getCep)
                .orElseThrow(() -> new IllegalArgumentException());
    }

    public Optional<String> findFirstUserByNameStartWith(List<Usuario> users, String startWith){
        return users
                .stream()
                .sorted(Comparator.comparing(Usuario::getNome))
                .map(Usuario::getNome)
                .filter(u -> u.startsWith(startWith))
                .findFirst();
    }

    public Optional<String> findOlderUser(List<Usuario> users){
        return users
                .stream()
                .sorted((u1,u2) -> Integer.compare(u2.getIdade(), u1.getIdade()))
                .map(Usuario::getNome)
                .findFirst();
    }

    public Optional<String> findYoungUser(List<Usuario> users){
        return users
                .stream()
                .sorted(Comparator.comparingInt(Usuario::getIdade))
                .map(Usuario::getNome)
                .findFirst();
    }
}



class Usuario{

    String nome;
    Integer idade;
    Optional<Endereco> endereco;

    public Usuario(String nome, Integer idade){
        this.nome = nome;
        this.idade = idade;
    }

    public Usuario(String nome, Endereco endereco){
        this.nome = nome;
        this.endereco = Optional.ofNullable(endereco);
    }

    public Optional<Endereco> getEndereco(){
        return endereco;
    }

    public Integer getIdade(){
        return idade;
    }

    public String getNome(){
        return nome;
    }
}

class Endereco{
    String cep;
    Optional<Cidade> cidade;

    public Endereco(){}

    public Endereco(String cep, Cidade cidade){
        this.cep = cep;
        this.cidade = Optional.ofNullable(cidade);
    }

    public Optional<Cidade> getCidade(){
        return cidade;
    }

    public String getCep(){
        return cep;
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
