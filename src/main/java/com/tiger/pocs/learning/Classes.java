package com.tiger.pocs.learning;

import lombok.Getter;
import lombok.Setter;

public class Classes {

    private Classes() {
    }

    @Getter
    @Setter
    public static class GraphModel {

        private String bncId;
        private String lObCode;
        private String lObName;
        private String accountId;
        private String accountType;
    }

    @Getter
    @Setter
    public static class McpModel {
        private String firstname;
        private String lastname;
        private String address;
        private String email;
    }

    @Getter
    @Setter
    public static class CsvModel {
        private String firstname;
        private String lastname;
        private String address;
        private String email;
        private String lObCode;
        private String lObName;
        private String accountId;
        private String accountType;
    }

}


