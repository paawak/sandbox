package com.swayam.geektrust.goldencrown;

import java.io.InputStream;
import java.io.PrintStream;

import com.swayam.geektrust.goldencrown.service.InputOutputHandler;

public class GoldenCrown {

    public static void main(String[] args) {

        InputStream inputStream = System.in;
        PrintStream standardOut = System.out;
        PrintStream errorOut = System.err;

        new InputOutputHandler().invokeAndWait(inputStream, standardOut, errorOut);

    }

}
