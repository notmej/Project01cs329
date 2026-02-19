/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package project01;

import java.util.HashMap;

/**
 *
 * @author yuna
 */
public class Project01 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Project01 obj = new Project01();

        System.out.println( obj.floatMultiplicationConversion() );

    }
    
    // Integer subtraction
    public String intSubtractionConversion(){
        HashMap<Integer, Character> ansAsHM = new HashMap<>();
        String ans = "";
        
        //subtraction logic 
        
        
        return ans;
    }
    
    // floating point subtraction
    public String floatSubtractionConversion(){
        HashMap<Integer, Character> ansAsHM = new HashMap<>();
        String ans = "";
        
        //subtraction logic 
        
        
        return ans;
    }
    // Integer multiplication
    public String intMultiplicationConversion(){

        HashMap<Integer, Character> ansAsHM = new HashMap<>();
        String ans = "";

        int result = 0;
        String value = "101"; // example
        int base = 2; // example

        // multiplication logic
        for(int i = 0; i < value.length(); i++){

            char digit = value.charAt(i);

            int num;

            if(digit >= '0' && digit <= '9'){
                num = digit - '0';
            }
            else{
                num = digit - 'A' + 10;
            }

            System.out.println("Step " + (i+1));
            System.out.println("result * base + digit");
            System.out.println(result + " * " + base + " + " + num);

            result = result * base + num;

            System.out.println("result now = " + result);
            System.out.println();
        }

        ans = "" + result;

        return ans;
    }


    // Floating multiplication
    public String floatMultiplicationConversion(){

        HashMap<Integer, Character> ansAsHM = new HashMap<>();
        String ans = "";

        double fraction = 0.625; // example
        int base = 2;
        int precision = 5;

        // multiplication logic
        while(fraction > 0 && precision > 0){

            double temp = fraction * base;

            int integerPart = (int) temp;

            System.out.println("Step:");
            System.out.println(fraction + " * " + base + " = " + temp);
            System.out.println("Integer part = " + integerPart);

            ans += integerPart;

            System.out.println("Result now = " + ans);
            System.out.println();

            fraction = temp - integerPart;

            precision--;
        }

        System.out.println("Final answer = " + ans);

        return ans;
    }
}
