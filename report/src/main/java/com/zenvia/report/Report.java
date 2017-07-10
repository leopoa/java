package com.zenvia.report;

import java.util.List;
import java.util.Map;

public class Report {

    private String name;
    private String type;
    private String description;
    private String header;

    public Report(Builder builder){
        this.name = builder.getName();
        this.header = builder.getHeader();
        this.description = builder.getDescription();
    }

    public String getName() {
        return name;
    }

    public String getHeader() {
        return header;
    }

    public String getDescription() {
        return description;
    }

    public static class Builder{

        private String name;
        private String header;
        private String description;

        public Builder name(String name){
            if(name == null){
                throw new IllegalArgumentException("Invalid null name");
            }

            this.name = name;
            return this;
        }

        public Builder description(String description){
            this.description = description;
            return this;
        }

        public Report build() {
            return new Report(this);
        }

        public Builder headerDelimited(List<Map<String,String>> headers){
            if(headers == null || headers.size() == 0){
                throw new IllegalArgumentException("Invalid header");
            }

            this.header = buildDelimitedHeaderLine(headers);
            return this;
        }

        public Builder headerPosition(List<Map<String,String>> headers){
            if(headers == null || headers.size() == 0){
                throw new IllegalArgumentException("Invalid header");
            }

            validateHeaderRangeColunm(headers);
            this.header = buildPositionalHeaderLine(headers);
            return this;
        }

        private String buildDelimitedHeaderLine(List<Map<String,String>> headers){
            StringBuilder header = new StringBuilder();
            for (Map<String,String> map: headers) {
                header.append(";").append(map.get("title"));
            }
            return header.toString().replaceFirst(";", "");
        }

        private String buildPositionalHeaderLine(List<Map<String,String>> headers){
            StringBuilder header = new StringBuilder();
            for (Map<String,String> map: headers) {
                header.append(pad(map.get("title"), new Integer(map.get("end")) - new Integer(map.get("start"))));
            }
            return header.toString();
        }

        private boolean validateHeaderRangeColunm(List<Map<String,String>> headers){
            for (Map<String,String> header: headers) {
                int start = header.get("start") == null ? -1 :  new Integer(header.get("start"));
                int end = header.get("end") == null ? -1 :  new Integer(header.get("end"));

                if(start < 0 || end < 0 || start > end){
                    throw new IllegalArgumentException("Invalid header config - Invalid number: COLUNM[" + header.get("colunm")+"]");
                }

                compareIntersectionStartAndEndColunm(header.get("colunm"), start, end, headers);
            }
            return true;
        }

        private boolean compareIntersectionStartAndEndColunm(String colunm, int start, int end, List<Map<String,String>> headers){
            for (Map<String,String> headerCompare: headers) {
                if(colunm.equals(headerCompare.get("colunm"))){
                    continue;
                }

                int startCompare = headerCompare.get("start") == null ? 0 :  new Integer(headerCompare.get("start"));
                int endCompare = headerCompare.get("end") == null ? 0 :  new Integer(headerCompare.get("end"));

                if((start >= startCompare && start < endCompare) || (end > startCompare && end <= endCompare)){
                    throw new IllegalArgumentException("Invalid header config - Conflict columns: COLUNM[" + colunm+"]");
                }
            }
            return true;
        }

        private String fill(int length, String with) {
            StringBuilder sb = new StringBuilder(length);
            while (sb.length() < length) {
                sb.append(with);
            }
            return sb.toString();
        }

        private String pad(String value, int length) {
            return pad(value, length, "*");
        }

        private String pad(String value, int length, String with) {
            return new StringBuilder(length)
                            .append(fill(Math.max(0, length - value.length()), with))
                            .append(value)
                            .toString();
        }

        public String getName() {
            return name;
        }

        public String getHeader() {
            return header;
        }

        public String getDescription() {
            return description;
        }

    }

}
