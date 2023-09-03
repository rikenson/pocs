package com.tiger.pocs.utils;

public class Utills {

    public static String getClassNameSuffix(String className) {
        if (className.endsWith("Response"))
            return className.replace("Response", "");
        else if (className.endsWith("Request"))
            return className.replace("Request", "");
        return className.replace("Entity", "");
    }
}
