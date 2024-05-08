package com.saf.framework;


import java.util.Random;

public class Test {


    public static void main(String[] args) {

        Random rnd = new Random();
        int randomNumber = rnd.nextInt(99999999);

        // this will convert any number sequence into 6 character.
        System.out.println(randomNumber);


    }
}