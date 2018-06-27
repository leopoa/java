package com.dojo;

import java.util.*;
import java.util.stream.Collectors;

public class CredentialService {

    public List<Property> getOnlyPropertiesWithKeyPermission(List<Credential> credentials) {

        return credentials
                .stream()
                .map(Credential::getProperties)
                .flatMap(Collection::stream)
                .filter(Property::isKey)
                .collect(Collectors.toList());
    }
}

class Credential{

    public Credential(List<Property> properties){
        this.properties = properties;
    }

    private List<Property> properties;

    public List<Property> getProperties() {
        return properties;
    }
}

class Property {

    private String name;
    private boolean key;

    public Property(String name, boolean isKey){
        this.name = name;
        this.key = isKey;
    }


    public String getName() {
        return name;
    }

    public boolean isKey() {
        return key;
    }
}