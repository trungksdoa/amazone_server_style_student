package com.student.project.amazone.config;

import java.io.IOException;
import java.util.Scanner;

public class Testss {

    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) throws IOException {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Scanner sc = new Scanner(System.in);
        String A = sc.next();
        String B = sc.next();
        /* Enter your code here. Print output to STDOUT. */
        String myString = A + B;

// Gets the char at a given index

        if (A.compareTo(B) <= 0) {
            System.out.println("No");
        } else {
            System.out.println("Yes");
        }
        String outputA = A.substring(0, 1).toUpperCase() + A.substring(1);
        String outputB = B.substring(0, 1).toUpperCase() + B.substring(1);
        System.out.println(outputA + " " + outputB);
//        Locale localeUS = new Locale("en", "US");
//        Locale localeIndia = new Locale("en", "IN");
//        Locale localeFuckChina = new Locale("zh", "CN");
//        Locale localeFrance = new Locale("fr", "FR");
//        NumberFormat currencyUS = NumberFormat.getCurrencyInstance(localeUS);
//        NumberFormat currencyIndia = NumberFormat.getCurrencyInstance(localeIndia);
//        NumberFormat currencyFuckChina = NumberFormat.getCurrencyInstance(localeFuckChina);
//        NumberFormat currencyFrance = NumberFormat.getCurrencyInstance(localeFrance);
//
//        String us = currencyUS.format(31213213);
//        String india = currencyIndia.format(31213213);
//        String china = currencyFuckChina.format(31213213);
//        String france = currencyFrance.format(31213213);
//
//        System.out.println("US: " + us);
//        System.out.println("India: " + india);
//        System.out.println("China: " + china);
//        System.out.println("France: " + france);

//        LocalDate date = LocalDate.of(2015,05,01);
//        System.out.println(date.getDayOfWeek()+"");
//
//        Calendar cal = Calendar.getInstance();
//        cal.set(2015, 8 - 1, 05);


//        int nd = cal.get(Calendar.DAY_OF_WEEK);
//        switch (nd) {
//            case 2:
//                System.out.print("MONDAY");
//                break;
//            case 3:
//                System.out.print("TUESDAY");
//                break;
//            case 4:
//                System.out.print("WEDNESDAY");
//                break;
//            case 5:
//                System.out.print("THURSDAY");
//                break;
//            case 6:
//                System.out.print("FRIDAY");
//                break;
//            case 7:
//                System.out.print("SATURDAY");
//                break;
//            default:
//                System.out.print("SUNDAY");
//                break;
//        }
//        System.out.println(nd);
//        int count = 1;
//        while (sc.hasNext()) {
//            String inputString = sc.nextLine();
//            System.out.println(count+" "+inputString);
//            count++;
//        }


//        for (int i = 0; i < t; i++) {
//            try {
//                long x = sc.nextLong();
//                System.out.println(x + " can be fitted in:");
//                if (x >= -128 && x <= 127) System.out.println("* byte");
//                if (x >= -Math.pow(2,15) && x <= Math.pow(2,15)-1) System.out.println("* short");
//                if (x >= -Math.pow(2,31) && x <= Math.pow(2,31)-1) System.out.println("* int");
//                if (x >= -Math.pow(2,63) && x <=Math.pow(2,63)-1) System.out.println("* long");
//                //Complete the code
//            } catch (Exception e) {
//                System.out.println(sc.next() + " can't be fitted anywhere.");
//            }
//        }
    }
}
