package com.univer.crypt.task1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class KasiskiTest {
    Scanner console;
    Scanner file;

    void run() throws FileNotFoundException {
        console = new Scanner(System.in);
        file = new Scanner(new FileInputStream("cipher_text.txt"));
        String s = file.nextLine();



    }

    public static void main(String[] args) throws FileNotFoundException {
        new KasiskiTest().run();
    }
}
