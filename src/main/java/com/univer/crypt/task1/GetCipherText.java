package com.univer.crypt.task1;

import java.io.*;
import java.util.Scanner;

public class GetCipherText {
    Scanner console;
    Scanner file;
    int keyLen;
    PrintWriter out;
    int[] key;

    void run() throws FileNotFoundException {
        console = new Scanner(System.in);
        out = new PrintWriter(new BufferedOutputStream(new FileOutputStream("cipher_text.txt")));

        key = getKey();

        System.out.println("Input path:");
        //String path = console.nextLine();

        System.out.println("input.txt");
        String path = "input.txt";

        file = new Scanner(new FileInputStream(path));

        String s = "";

        while (file.hasNext()) {
            s += file.nextLine().replaceAll(" ", "");
        }

        while (s.length() % keyLen != 0) {
            s += Character.toLowerCase(s.charAt(0));
        }

        crypt(s, key);

        out.close();
    }

    void crypt(String s, int[] key) {
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < s.length(); i += key.length) {
            for (int j = 0; j < key.length; j++) {
                res.append(s.charAt(i + key[j]));
            }
        }
        out.print(res);
        System.out.println(res);
        out.flush();
    }


    public static void main(String[] args) throws FileNotFoundException {
        new GetCipherText().run();
    }

    public int[] getKey() throws FileNotFoundException {
        Scanner keyFile = new Scanner(new FileInputStream("key.txt"));
        String[] s = keyFile.nextLine().trim().split(" ");
        keyLen = s.length;
        key = new int[keyLen];

        for (int i = 0; i < keyLen; i++) {
            key[i] = Integer.parseInt(s[i]);
        }

        return key;
    }
}
