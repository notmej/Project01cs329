/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project01;

/**
 *
 * @author yuna
 */
public class Convert {

    
    
    //converts (main method for subtraction)
    public static String convert(String value, int src, int destination, int precision){
        String[] parts = value.split("\\.");
        String intPart = parts[0];
        String fracPart = (parts.length > 1) ? parts[1] : "";
        String fracResult = fractionMultiplicationConversion(fracPart, src, destination, precision);
        String intResult = intMultiplicationConversion(intPart, src, destination);

        if(fracPart.equals(""))
            return intResult;
        else
            return intResult + "." + fracResult;
        
    }
    
            
    // Integer subtraction (scratch out probs) (in charge of the integer) 

    
    
    //another method in charge of everything after the decimal


    public static String intMultiplicationConversion(String intPart, int src, int destination){

        int decimalValue = Integer.parseInt(intPart, src);

        if(decimalValue == 0){
            return "0";
        }

        String result = "";

        while(decimalValue > 0){

            int remainder = decimalValue % destination;

            if(remainder < 10)
                result = remainder + result;
            else
                result = (char)(remainder - 10 + 'A') + result;

            decimalValue = decimalValue / destination;
        }

        return result;
    }

    public static String fractionMultiplicationConversion(String fracPart, int src, int destination, int precision){

        // this string will store the final result
        String result = "";

        // convert fraction string to decimal number
        double fraction = 0;

        // convert from source base to decimal
        for(int i = 0; i < fracPart.length(); i++){

            int digit = Character.getNumericValue(fracPart.charAt(i));

            fraction += digit / Math.pow(src, i+1);

        }



        // multiplication method
        for(int i = 0; i < precision; i++){

            fraction = fraction * destination;

            int integerPart = (int) fraction;

            result += integerPart;



            fraction = fraction - integerPart;

            // stop if fraction becomes 0
            if(fraction == 0)
                break;

        }

        return result;

    }
    
}
