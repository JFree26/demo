package com.jfree.demo;

import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        System.out.println(stringToLocalTime("01:01").toString().toString().compareTo(stringToLocalTime("02:00").toString()));
    }

    public static LocalTime stringToLocalTime(String time){
        String[] ti=time.split(":");
        return LocalTime.of(Integer.parseInt(ti[0]),Integer.parseInt(ti[1]));
    }
}
