package com.tmt.demographics.utils;

import java.time.LocalDate;

public class Tester {
    public static void main(String[] args) {
        var comparator = LocalDate.of(2023, 03, 2);
        var now = LocalDate.now();
        System.out.println(now.isAfter(comparator) || now.isEqual(comparator));

    }
}
