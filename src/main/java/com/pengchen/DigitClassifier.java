package com.pengchen;

import java.util.Scanner;


import java.io.*;

/**
 * Created by CP on 13/11/2017.
 */
public class DigitClassifier {
    public static void main(String[] args) {
        System.out.println("the digits are: " + GetRecResult("/Users/CP/Documents/Study/code/intellij/MobileComputingORC/src/main/java/com/pengchen/photo_2.jpg"));
    }

    public static String GetRecResult(String PhotoPath) {
        String s = null;
        String ModelPath = " /Users/CP/Documents/Study/code/intellij/MobileComputingORC/src/main/java/com/pengchen/";

        try {

            // using the Runtime exec method:
            String command = "python" + ModelPath + "performRecognition.py -c" + ModelPath + "digits_cls.pkl -i" + PhotoPath;
            Process p = Runtime.getRuntime().exec(command);
            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(p.getErrorStream()));

            // read any errors from the attempted command
            /*
            System.out.println("Here is the standard error of the command (if any):\n");
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }
            */


        } catch (IOException e) {
            System.out.println("exception happened - here's what I know: ");
            e.printStackTrace();
            System.exit(-1);
        } finally {

            return ReadFile("/Users/CP/Documents/Study/code/intellij/MobileComputingORC/result.txt");
        }

    }

    public static String ReadFile(String FilePath) {
        StringBuilder text = new StringBuilder();
        try {

            String NL = System.getProperty("line.separator");
            Scanner scanner = new Scanner(new FileInputStream(FilePath), "UTF-8");
            try {
                while (scanner.hasNextLine()) {
                    text.append(scanner.nextLine() + NL);
                }
            } finally {
                scanner.close();
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return text.toString();


    }

}
