package com.pengchen;

import java.util.Scanner;


import java.io.*;

/**
 * Created by CP on 13/11/2017.
 */
public class DigitClassifier {

    public static void main(String[] args) {
        System.out.println("the digits are: " + GetRecResultByTF("2.png"));
    }


    public String GetRecResult(String PhotoPath) {
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

            System.out.println("Here is the standard error of the command (if any):\n");
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }
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
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return ReadFile("/Users/CP/Software/apache-tomcat-8.5.23/bin/result.txt");
        }

    }

    public static String GetRecResultByTF(String PhotoPath) {
        String s = null;
        String result = null;
        try {

            // using the Runtime exec method:
            String preCommand ="source /Users/CP/tensorflow/bin/activate";
            Runtime.getRuntime().exec(preCommand);
            String command = "python predict_2.py " + PhotoPath;
            //String command = "pip list";
            System.out.println(command);

            Process p = Runtime.getRuntime().exec(command);
            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(p.getErrorStream()));

            System.out.println("Here is the standard input of the command (if any):\n");

            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
                result = s;

            }
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
                result = s;

            }


        } catch (IOException e) {
            System.out.println("exception happened - here's what I know: ");
            e.printStackTrace();
            System.exit(-1);
        }
        return result;
    }

    public String ReadFile(String FilePath) {
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
