package com.tiger.pocs.utils;

public class Utils {


    private Utils() {
    }

    public static String toLabel(String className) {
        if (className.endsWith("Response"))
            return className.replace("Response", "");
        else if (className.endsWith("Request"))
            return className.replace("Request", "");
        else if (className.endsWith("Edge"))
            return className.replace("Edge", "");
        return className.replace("Entity", "");
    }


}
