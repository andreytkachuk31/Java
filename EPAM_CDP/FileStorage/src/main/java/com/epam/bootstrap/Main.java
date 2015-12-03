package com.epam.bootstrap;

import com.epam.bootstrap.runner.FileRunner;

import java.io.IOException;


/**
 * @author Andrii_Tkachuk
 * @since 10/27/2015
 */
public class Main {

    public static void main(String[] args) throws IOException {
        FileRunner.createInstance().run();
    }
}
