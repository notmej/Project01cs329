/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package project01;

import java.util.ArrayList;
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
        ArrayList<ArrayList<String>> allConvertedVal = new ArrayList<>();
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
            System.out.println("! Type 'x' to leave the program at any time !");
            System.out.println("Enter the source base: ");
            input = in.nextLine();
            if(input.toLowerCase().equals("x")) break;
            srcBase = Integer.parseInt(input);

            System.out.println("Enter your value for conversion (Integer or fraction): ");
            input = in.nextLine();
            if(input.toLowerCase().equals("x")) break;
            inValue = input;

            System.out.println("Enter the destination base: ");
            input = in.nextLine();
            if(input.toLowerCase().equals("x")) break;
            destBase = Integer.parseInt(input);

            System.out.println("Pick your conversion method: \n 1. Subtraction \n 2. Multiplication \n 3. Division ");
            input = in.nextLine();
            if(input.toLowerCase().equals("x")) break;
            conversionMethod = Integer.parseInt(input);
            
            if(isFraction(inValue)){
                System.out.println("Input precision : ");
                input = in.nextLine();
                if(input.toLowerCase().equals("x")) break;
                prec = Integer.parseInt(input);
            }
            
            boolean errorFound = false;
            try{
                validateInput(inValue, srcBase, destBase, prec, conversionMethod);
            } catch ( IllegalArgumentException e ){
                System.out.println("Error: " + e.getMessage());
                errorFound = true;
            }
            
            if(!errorFound){
                if(conversionMethod == 1){
                    outVal = Convert.subtractionConvert(inValue, srcBase, destBase, prec);
                    System.out.println("output: " + outVal);
                    ArrayList<String> temp = new ArrayList<>();
                    temp.add(inValue + " base " + srcBase );
                    temp.add(outVal + " base "+ destBase + " using subtraction method");
                    allConvertedVal.add(temp);
                } else if (conversionMethod == 2){
                    outVal = Convert.multiplicationConvert(inValue, srcBase, destBase, prec);
                    ArrayList<String> temp = new ArrayList<>();
                    System.out.println("output: " + outVal);
                    temp.add(inValue + " base " + srcBase );
                    temp.add(outVal + " base " + destBase + " using Multiplication method");
                    allConvertedVal.add(temp);
                }else if(conversionMethod == 3){
                    outVal = Convert.divisionConvert(inValue, srcBase, destBase, prec);
                    ArrayList<String> temp = new ArrayList<>();
                    System.out.println("output: " + outVal);
                    temp.add(inValue + " base " + srcBase);
                    temp.add(outVal + " base "+ destBase + " using Division method");
                    allConvertedVal.add(temp);
                }
            }
            
        }
        
        System.out.println("\n\n\n------------------------------------------------------------\n- - - Would you like to see all your conversions? - - -\n Type 'y' or 'Y' for yes and any opthey key for no.");
        input = in.nextLine();
        if(input.toLowerCase().equals("y")){
            for(int i = 0; i < allConvertedVal.size(); i++){
                
                System.out.print(" " + allConvertedVal.get(i).get(0) + " --> " +  allConvertedVal.get(i).get(1) + " ");
                
                System.out.println("");
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
