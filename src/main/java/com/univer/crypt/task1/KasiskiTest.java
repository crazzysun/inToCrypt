package com.univer.crypt.task1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class KasiskiTest {
    Scanner console;
    Scanner file;

    void run() throws FileNotFoundException {
        console = new Scanner(System.in);
        file = new Scanner(new FileInputStream("cipher_text.txt"));
        String s = file.nextLine().toLowerCase();

        System.out.println("input a block length:");

        Map<String, ArrayList<Integer>> map;
        int ans = -1;

        while (true) {
            int n = console.nextInt();
            map = new HashMap<String, ArrayList<Integer>>();

            for (int i = 0; i < s.length() - n; i++) {
                String tmp = s.substring(i, i + n);

                if (!map.containsKey(tmp)) {
                    map.put(tmp, new ArrayList<Integer>());
                }

                map.get(tmp).add(i);
            }




            if (ans == 1) {
                System.out.println("input new block length (more than " + n + "):");
            } else {
                break;
            }
        }


    }

    public static void main(String[] args) throws FileNotFoundException {
        new KasiskiTest().run();
    }
}
