package com.projectx.utils;

import java.util.Date;

public class Utilities {

    public static String generateTimeStamp() {
        Date date = new Date();
        return date.toString().replace(" ", "_").replace(":", "_");
    }

    public static String generateEmailWithTimeStamp() {
        Date date = new Date();
        String timeStamp = date.toString().replace(" ", "_").replace(":", "_");
        return "rv" + timeStamp + "@gmail.com";
    }
}
