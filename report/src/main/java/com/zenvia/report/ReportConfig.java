package com.zenvia.report;

import java.util.Optional;


public class ReportConfig {

    private String fileName;
    private String type;

    private ReportConfig(String fileName, String type){
        this.type = type;
        this.fileName = fileName;
    }

    public static Optional<ReportConfig> create(String fileName, String type){
        return Optional.of(new ReportConfig(fileName, type));
    }

    public boolean isValid(){
        return true;
    }

    public String getFileName() {
        return fileName;
    }

    public String getType() {
        return type;
    }
}
