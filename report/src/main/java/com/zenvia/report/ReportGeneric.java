package com.zenvia.report;


import java.util.List;
import java.util.Map;

public class ReportGeneric {

    private String name;
    private List<Map<String,String>> header;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Map<String, String>> getHeader() {
        return header;
    }

    public void setHeader(List<Map<String, String>> header) {
        this.header = header;
    }


}
