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
        out = new PrintWriter(new BufferedOutputStream(new FileOutputStream("output.txt")));
        file = new Scanner(new FileInputStream("input.txt"));

//        System.out.println("??????? ???? ? ????? ? ??????????:");
//        String path = in.nextLine();

        System.out.println("Input key lenght:");
        keyLen = in.nextInt();
        key = new int[keyLen];
        genKey();

        while (file.hasNext()) {
            String s = in.nextLine();
            StringBuilder res = new StringBuilder();


            for (int j = 0; j < s.length(); j++) {
                res.append(s.charAt(key[j]))
            }

        }


    }

    void genKey() {
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

        for (int i = 0; i < keyLen; i++) {
            System.out.print(key[i] + " ");
        }
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
