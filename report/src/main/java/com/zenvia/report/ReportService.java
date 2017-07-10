package com.zenvia.report;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReportService {

    private static final String POSITIONAL = "position";
    private static final String DELIMITED = "delimit";

    public Report process(String fileName, String type){

        return ReportConfig
                .create(fileName, type)
                .filter(config -> config.isValid())
                .map(config -> readFile(config.getFileName()))
                .filter(generic -> generic.isPresent())
                .map(generic -> transformGenericToReport(generic.get(), type))
                .filter(report -> report.isPresent())
                .map(report -> report.get())
                .orElse(null);

       /* return readFile(fileName)
                    .map(generic -> transformGenericToReport(generic, type))
                    .filter(layout -> layout.isPresent())
                    .map(layout -> layout.get())
                    .orElse(null); */
    }



    public Optional<ReportGeneric> readFile(String nameLayoutFile) {
        try {
            return Optional.of(new ObjectMapper().readValue(Resources.getResource(nameLayoutFile), ReportGeneric.class));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<Report> transformGenericToReport(ReportGeneric generic, String type){
        try{
            if(DELIMITED.equalsIgnoreCase(type)){
                return Optional.of(createDelimitedReport(generic));
            }

            return Optional.of(createPositionalReport(generic));
        }catch (Exception e){
            return Optional.empty();
        }
    }

    private Report createPositionalReport(ReportGeneric generic){
        return new Report.Builder()
                        .name(generic.getName())
                        .headerPosition(generic.getHeader())
                        .build();
    }

    private Report createDelimitedReport(ReportGeneric generic){
        return new Report.Builder()
                .name(generic.getName())
                .headerDelimited(generic.getHeader())
                .build();
    }
}
