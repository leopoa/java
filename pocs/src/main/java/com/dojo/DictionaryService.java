package com.dojo;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DictionaryService {

    public List<String> getUniqueWordsInSentence(String sentence){
        return Stream.of(sentence.split(" "))
                .map(word -> word.replaceAll(" ", ""))
                .filter(word -> !word.isEmpty())
                .map(String::toLowerCase)
                .distinct()
                .collect(Collectors.toList());
    }

    public Map<String, List<Dictionary>> groupEqualWordsAndCount(String sentence){
        return Stream.of(sentence.split(" "))
                .map(word -> word.replaceAll(" ", ""))
                .filter(word -> !word.isEmpty())
                .map(String::toLowerCase)
                .map(Dictionary::new)
                .collect(Collectors.groupingBy(Dictionary::getWord));
    }


}

class  Dictionary {

    private String word;

    public Dictionary(String word){
        this.word = word;
    }

    public String getWord() {
        return word;
    }
}
