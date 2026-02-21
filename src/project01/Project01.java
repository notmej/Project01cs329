/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package project01;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;



/**
 *
 * @author Yunona Agzamova 70152S
 */
public class Project01 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        
        
        
        // initialization of values
        HashMap<String, String> allConvertedVal = new HashMap<>();
        int srcBase;
        int destBase;
        String inValue = "";
        int prec= 0;
        int conversionMethod;
        String outVal = "";
        Scanner in = new Scanner(System.in);
        String input = "";
        
        
        System.out.println("~~~~~~~~~~ Welcome to the Base to Base converter. ~~~~~~~~~~");
        while(true){
            System.out.println("Enter the source base (or type 'exit' to exit this program): ");
            input = in.nextLine();
            if(input.equals("exit")) break;
            srcBase = Integer.parseInt(input);

            System.out.println("Enter your value for conversion (Integer or fraction) (or type 'exit' to exit this program): ");
            input = in.nextLine();
            if(input.equals("exit")) break;
            inValue = input;

            System.out.println("Enter the destination base (or type 'exit' to exit this program): ");
            input = in.nextLine();
            if(input.equals("exit")) break;
            destBase = Integer.parseInt(input);

            System.out.println("Pick your conversion method: \n 1. Subtraction \n 2. Multiplication \n 3. Division \n (or type 'exit' to exit this program)");
            input = in.nextLine();
            if(input.equals("exit")) break;
            conversionMethod = Integer.parseInt(input);
            
            if(isFraction(inValue)){
                System.out.println("Input precision (or type 'exit' to exit this program): ");
                input = in.nextLine();
                if(input.equals("exit")) break;
                prec = Integer.parseInt(input);
            }
            
            try{
                validateInput(inValue, srcBase, destBase, prec, conversionMethod);
            } catch ( IllegalArgumentException e ){
                System.out.println("Error: " + e.getMessage());
            }
        }
        
        System.out.println("\nExited program.");
        
        
        
        
        

    }
    
   
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - Input Validation - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
    
    
    public static boolean isFraction(String value) {
        if (value == null || value.isEmpty()) return false;

        int dotCount = 0;

        for (char c : value.toCharArray()) {
            if (c == '.') {
                dotCount++;
            }
        }

        return dotCount == 1 && !value.equals(".");
    }
    
    public static boolean isValidBase(int base) {
        return base == 2 || base == 8 || base == 10 || base == 16;
    }
    
     public static boolean isValidConversion(int val) {
        return val == 1 || val == 2 || val == 3;
    }
    
    public static boolean isValidPrecision(int prec) {
        return prec >= 0;
    }
    
    public static boolean isValidValue(String value, int base) {
        value = value.toUpperCase();
        int dotCount = 0;
        //gho through each character and see if . is present. if there is more than 1 dor then the input is invalid
        for (char c : value.toCharArray()) {
            if (c == '.') {
                dotCount++;
                if (dotCount > 1) return false;
                continue;
            }
            int digit = Character.digit(c, base);
            if (digit == -1) {
                return false;
            }
        }
        return true;
    }
    
    public static void validateInput(String value, int srcBase, int destBase, int prec, int method) {

        if (!isValidBase(srcBase))
            throw new IllegalArgumentException("Invalid source base");

        if (!isValidBase(destBase))
            throw new IllegalArgumentException("Invalid destination base");

        if (!isValidValue(value, srcBase))
            throw new IllegalArgumentException("Invalid number for source base");

        if (!isValidPrecision(prec))
            throw new IllegalArgumentException("Invalid precision");

        if (!isValidConversion(method))
            throw new IllegalArgumentException("Invalid conversion method");
    }
}
