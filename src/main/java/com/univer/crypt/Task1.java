package com.univer.crypt;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Task1 {
    int keyLen;
    Scanner in;
    PrintWriter out;
    Scanner file;
    int[] key;

    //Transposition cipher

    void run() throws FileNotFoundException {
        in = new Scanner(System.in);
        out = new PrintWriter(new BufferedOutputStream(new FileOutputStream("cipher_text.txt")));


        System.out.println("Input key lenght:");
        keyLen = in.nextInt();
        key = new int[keyLen];
        genKey();

        System.out.println("input path:");
        //String path = in.nextLine();
        String path = "input.txt";
        file = new Scanner(new FileInputStream(path));

        while (file.hasNext()) {
            String s = file.nextLine().replaceAll(" ", "");
            while (s.length() % keyLen != 0) {
                s += "#";
            }

            StringBuilder res = new StringBuilder();

            for (int i = 0; i < s.length(); i += keyLen) {
                for (int j = 0; j < keyLen; j++) {
                    res.append(s.charAt(i + key[j]));
                }
            }


            out.print(res);
            System.out.println(res);
        }

        out.flush();

    }

    void genKey() throws FileNotFoundException {
        int[] arr = new int[keyLen];
        for (int i = 0; i < keyLen - 1; i++) {
            arr[i] = i + 1;
        }
        Random r = new Random();
        for (int i = 0; i < keyLen - 1; i++) {
            swap(arr, i, r.nextInt(keyLen - 1));
        }
        arr[keyLen - 1] = 0;
        key[0] = arr[0];
        for (int i = 1; i < keyLen; i++) {
            key[arr[i - 1]] = arr[i];
        }

        PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream("key.txt")));

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < keyLen; i++) {
            res.append(key[i]).append(" ");
        }

        out.print(res);
        System.out.println(res.toString());
        out.close();
    }

    void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) throws FileNotFoundException {
        new Task1().run();
    }
}
