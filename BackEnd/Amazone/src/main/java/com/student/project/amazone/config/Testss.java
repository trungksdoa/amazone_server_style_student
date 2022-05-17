package com.student.project.amazone.config;

import com.student.project.amazone.AmazoneApplication;
import org.springframework.boot.SpringApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.temporal.ValueRange;
import java.util.Scanner;

public class Testss {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        for (int i = 0; i < t; i++) {

            try {
                long x = sc.nextLong();
                System.out.println(x + " can be fitted in:");
                if (x >= -128 && x <= 127) System.out.println("* byte");
                if (x >= -Math.pow(2,15) && x <= Math.pow(2,15)-1) System.out.println("* short");
                if (x >= -Math.pow(2,31) && x <= Math.pow(2,31)-1) System.out.println("* int");
                if (x >= -Math.pow(2,63) && x <=Math.pow(2,63)-1) System.out.println("* long");
                //Complete the code
                System.out.println(Math.pow(2,64));
            } catch (Exception e) {
                System.out.println(sc.next() + " can't be fitted anywhere.");
            }
        }
    }
}
