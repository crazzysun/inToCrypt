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

        Map<String, ArrayList<Integer>> map;
        ArrayList<Integer> lens = new ArrayList<Integer>();

        int n = 3;
        map = new HashMap<String, ArrayList<Integer>>();

        while (n < 9) {

            for (int i = 0; i < s.length() - n + 1; i++) {
                String tmp = s.substring(i, i + n);

                if (!map.containsKey(tmp)) {
                    map.put(tmp, new ArrayList<Integer>());
                }

                map.get(tmp).add(i);
            }

            for (String keys : map.keySet()) {
                ArrayList<Integer> tmp = map.get(keys);
                for (int i = 1; i < tmp.size(); i++) {
                    int len = tmp.get(i) - tmp.get(i - 1);
                    lens.add(len);
                }
            }
            n++;
        }

        Map<Integer, Integer> gcds = new HashMap<Integer, Integer>();

        for (int i = 0; i < lens.size(); i++) {
            for (int j = 0; j < lens.size(); j++) {
                int t = gcd(lens.get(i), lens.get(j));

                if (t > 1) {
                    if (!gcds.containsKey(t)) {
                        gcds.put(t, 0);
                    }

                    gcds.put(t, gcds.get(t) + 1);
                }
            }
        }

        int max = 0;
        int ans = 0;

        for (Integer k : gcds.keySet()) {
            System.out.println(k + " : " + gcds.get(k));
            if (gcds.get(k) > max) {
                max = gcds.get(k);
                ans = k;
            }
        }

        System.out.println("Key len = " + ans + ". May be.");
    }

    int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        new KasiskiTest().run();
    }
}
