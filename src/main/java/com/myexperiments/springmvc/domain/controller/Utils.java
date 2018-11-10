package com.myexperiments.springmvc.domain.controller;

import java.util.Random;

public class Utils {

    public static double getDoubleWithinRange(int leftLimit, int rightLimit) {
        return leftLimit + Math.random() * (rightLimit - leftLimit);
    }

    public static boolean getRandomBoolean() {
        int anInt = new Random().nextInt(1);
        return anInt != 0;
    }

}
