package com.myexperiments.springmvc.controller;

public class Utils {

    public static double getDoubleWithinRange(int leftLimit, int rightLimit) {
        return leftLimit + Math.random() * (rightLimit - leftLimit);
    }

}
