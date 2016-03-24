package com.univer.crypt.task1;


import java.io.*;
import java.util.Scanner;

public class GetDecryptText {
    Scanner console = new Scanner(System.in);
    Scanner file;
    PrintWriter out;

    void run() throws FileNotFoundException {
        file = new Scanner(new FileInputStream("key.txt"));
        out = new PrintWriter(new FileOutputStream("decrypt_text.txt"));

        String[] t = file.nextLine().split(" ");
        int[] key = new int[t.length];

        for (int i = 0; i < t.length; i++) {
            key[i] = Integer.parseInt(t[i]);
        }

        file = new Scanner(new FileInputStream("cipher_text.txt"));
        String s = file.nextLine();

        StringBuilder res = new StringBuilder();

        char[] tmp = new char[key.length];
        for (int i = 0; i < s.length(); i += key.length) {
            for (int j = 0; j < key.length; j++) {
                tmp[key[j]] = s.charAt(i + j);

               // res.append(s.charAt(i + key[j]));
            }

            for (int j = 0; j < key.length; j++) {
                res.append(tmp[j]);
            }
        }

        out.print(res.toString());
        out.close();
    }


    public static void main(String[] args) throws FileNotFoundException {
        new GetDecryptText().run();
    }
}
