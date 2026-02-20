/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project01;



/**
 *
 * @author Yunona Agzamova 70152
 */
public class Convert {
    
    
    public static String subtractionConvert(String value, int src, int dest, int prec) throws IllegalStateException{
        
        if(src == 10 && (dest == 2 || dest == 8 || dest == 16)){
            return subtractionConvertDenaryToAny(value, dest, prec);
        } else if ( src == 2 && (dest == 10 || dest == 8 || dest == 16)){
            if(dest == 10){
                return convertAnyToDenary(value, src,prec);
            }
            return subtractionConvertDenaryToAny(convertAnyToDenary(value, src, prec), dest, prec);
        }
        else {
            throw new IllegalStateException("Invalid source or destination base value, please try again with a valid source or destination value");
        }
        
    }
    
    public static String convertAnyToDenary(String value, int src, int prec){
        
        // Split input into integer part and fraction part
        String[] parts = value.split("\\.");
        String intPart = parts[0];
        String fractPart = (parts.length > 1) ? parts[1] : "";

        int decimal = 0;
        int power = intPart.length() - 1;

        for (int i = 0; i < intPart.length(); i++) {
            int digit = Character.digit(intPart.charAt(i), src);
            if (digit == -1) throw new IllegalArgumentException("Invalid digit");
    
            // subtraction method: multiply by power of base
            decimal += digit * Math.pow(src, power);
            power--;
         }
        
        
        double fracResult = 0.0;
        for (int i = 0; i < fractPart.length(); i++) {
            int digit = Character.digit(fractPart.charAt(i), src);
            if (digit == -1) throw new IllegalArgumentException("Invalid digit");
    
            // subtract method equivalent: add digit * src^-position
            fracResult += digit / Math.pow(src, i + 1);
        }
        
        double total = decimal + fracResult;
        String formatted = String.format("%." + prec + "f", total);
        return formatted;
    }
    
    
    
    /**
     * Converts from denary to any base.
     * - - - !NOTE! for precision point usage: - - -
     * - destination base: 8 -> multiply precision point by 3
     * - destination base: 16 -> multiply precision point by 4
     * 
     * @param value The value you need to convert (in denary)
     * @param dest destination base to convert denary value to.
     * @param prec precision point for fractions
     * @return Converted value in destination base
     */
    private static String subtractionConvertDenaryToAny(String value, int dest, int prec){
        
        //splits input into integer part and decimal part
        String[] parts = value.split("\\.");
        String intPart = parts[0];
        String fractPart = (parts.length > 1) ? parts[1] : "";
        
        //where to put conversion ans
        String intPartConvertedBin = "";
        String fractPartConvertedBin = "";
        String finalConversionBin  = "";
        
        String intFinalConversion = "";
        String fractFinalConversion = "";
        String finalConversion = "";

        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -Denary to Binary- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
 

        System.out.println("- - - - - - Denary to binary - - - - - - ");
        int intPartIntType = Integer.parseInt(intPart);
        
        // - - - - - - - - - -  integer part  - - - - - - - - - - - - - - 
        
        if(intPartIntType != 0){
            System.out.println("- - - - - - Integer part  - - - - - - ");
            int highestPower = (int) (Math.log(intPartIntType) / Math.log(2));
            System.out.println("Find the highest power of " + intPartIntType + " in base " + 2 + ": " + highestPower);
            
            // loop from highestPower to 0, find which position has a HIGH or LOW Value
            // eg; pow = 8, 2^8 = 256, 328 - 256 >= 0  .... 2^3 = 8, 8-8 >= 0
            for( int i = highestPower; i >= 0; i--){
                //power value
                int val = (int) (Math.pow(2, i));
                //Check logic
                if(intPartIntType - val >= 0){     
                    System.out.println("current check: " + (intPartIntType - val) + " > 0 is True. Value of 1 at pos 2^" + i);
                    intPartConvertedBin = intPartConvertedBin + "1";
                    intPartIntType -= val;
                } else if (intPartIntType - val < 0) {
                    intPartConvertedBin = intPartConvertedBin + "0";
                    System.out.println("current check: " + (intPartIntType - val) + " > 0 is False. Value of 0 at pos 2^" + i);
                }
            }
            
            System.out.println("\n! --- > Final value of integer part: " + intPartConvertedBin + " base 2");
        }else {
            System.out.println("No integer Part to Evaluate.\n");
        }
        
        
        // - - - - - - - - - -  Fraction part  - - - - - - - - - - - - - - 
        
        if (!fractPart.isEmpty()){
            System.out.println("\n - - - - - - Fraction part  - - - - - - ");
            prec = -prec;
            double fractPartDoubVal = Double.parseDouble(value) - Integer.parseInt(intPart);
            for(int i = -1; i >= prec ; i--){
                System.out.println("current precision: " + i);
                //power value
                double val =  Math.pow(2,i);
                //check logic
                if(fractPartDoubVal - val >= 0){     
                    System.out.println("current check: " + (fractPartDoubVal - val) + " > 0 is True. Value of 1 at pos 2^" + i);
                    System.out.println(fractPartDoubVal);
                    fractPartConvertedBin = fractPartConvertedBin + "1";
                    fractPartDoubVal -= val;
                } else if (fractPartDoubVal - val < 0) {
                    fractPartConvertedBin = fractPartConvertedBin + "0";
                    System.out.println("current check: " + (fractPartDoubVal - val) + " > 0 is False. Value of 0 at pos 2^" + i);
                }
            
            }
            
            System.out.println("\n! --- > Final value of fract part: " + fractPartConvertedBin + " base 2\n\n");
        } else {
            System.out.println("No Fraction Part to Evaluate.\n");
        }
        
        // - - - - - - - - - - - - - -  Combination of fract and binary parts - - - - - - - - - - - - 
        finalConversionBin = (intPartConvertedBin.length() ==0) ? "0." + fractPartConvertedBin : intPartConvertedBin + "." + fractPartConvertedBin ;
        System.out.println("- - - > FINAL VALUE IN BINARY: " + finalConversionBin);
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        
        
        
        
        
        
        //Convert to octal or hex or return as is in binary,
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - Octal conversion:- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        if(dest == 8){
            // check if int conversion is necessary in the first place
            if(!intPartConvertedBin.isEmpty()) {
                // - - - - - - - - - -  integer part  - - - - - - - - - - - - - - 
                System.out.println("\n- - - - - - Binary to Octal - - - - - - \n - - - - - - Integer Part - - - - - - ");
                System.out.println("Add extra 0's at the end to aid conversion if necessary:");
                if(intPartConvertedBin.length() % 3 != 0){
                    for(int i = 3-(intPartConvertedBin.length() % 3); i > 0; i--){
                       intPartConvertedBin = "0" + intPartConvertedBin;
                       
                    }
                    System.out.println("Current value (Int part): " + intPartConvertedBin);
                }else {
                    System.out.println("Extra Zeros Not Necessary.");
                }
                
                int count = 1; // count the triads
                for(int i = 0; i <= intPartConvertedBin.length()-3; i+=3){
                   int sum = 0;
                   for( int j = i; j < i+3; j++){
                       sum += (intPartConvertedBin.charAt(j)=='1') ? (int) Math.pow(2,((i+3)-j)-1) : 0;
                   }
                   System.out.println("Sum of bits at the " + count++ + "(st/nd/rd/th) triad: " + sum);
                
                    intFinalConversion += sum;
                }
                System.out.println("Base 8 final answer on Integer side: " + intFinalConversion);
            } else {
                System.out.println("No integer value to evaluate");
            }
            
            
            // - - - - - - - - - -  fraction part  - - - - - - - - - - - - - - 
            if(!fractPartConvertedBin.isEmpty()){
                int count = 1; // count the triads
                for(int i = 0; i <= fractPartConvertedBin.length()-3; i+=3){
                    int sum = 0;
                    for( int j = i; j < i+3; j++){
                        sum += (fractPartConvertedBin.charAt(j)=='1') ? (int) Math.pow(2,((i+3)-j)-1) : 0;
                    }
                    System.out.println("Sum of bits at the " + count++ + "(st/nd/rd/th) triad: " + sum);
                
                    fractFinalConversion += sum;
                }
                
                System.out.println("Base 8 final answer on Fraction side:" + fractFinalConversion);
                
            } else {
                System.out.println("No fraction part to evaluate.");
            }
            finalConversion = (intFinalConversion.length() ==0) ? "0." + fractFinalConversion : intFinalConversion + "." + fractFinalConversion;
            System.out.println("Octal final value: " + finalConversion);
            
            return finalConversion;
            
        } else if ( dest == 16 ){
            
            /**
             * Note for hex
             * A: 10
             * B: 11
             * C: 12
             * D: 13
             * E: 14
             * F: 15
             * 
             * 
             * ASCII A: char a = (char)(10 + 55);
             */
            // - - - - - - - - - - - - - - - - - - - - - - - - - - - - Hex Conversion - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
            // check if int conversion is necessary in the first place
            if(!intPartConvertedBin.isEmpty()) {
                // - - - - - - - - - -  integer part  - - - - - - - - - - - - - - 
                System.out.println("\n- - - - - - Binary to Hex - - - - - - \n - - - - - - Integer Part - - - - - - ");
                System.out.println("Add extra 0's at the end to aid conversion if necessary:");
                if(intPartConvertedBin.length() % 4 != 0){
                    for(int i = 4-(intPartConvertedBin.length() % 4); i > 0; i--){
                       intPartConvertedBin = "0" + intPartConvertedBin;
                    }
                    System.out.println("Current value (Int part): " + intPartConvertedBin);
                }else {
                    System.out.println("Extra Zeros Not Necessary.");
                }
                
                // conversion logic
                int count = 1; // count the Quartets
                for(int i = 0; i <= intPartConvertedBin.length()-4; i+=4){
                   int sum = 0;
                   for( int j = i; j < i+4; j++){
                       sum += (intPartConvertedBin.charAt(j)=='1') ? (int) Math.pow(2,((i+4)-j)-1) : 0;
                   }
                   System.out.println("Sum of bits at the " + count++ + "(st/nd/rd/th) Quartet: " + sum);
                   
                   if(sum > 9){
                       char a = (char) (sum + 55);
                       intFinalConversion += a;
                   } else {
                       intFinalConversion += sum;
                   }
                
                }
                System.out.println("Base 16 final answer on Integer side: " + intFinalConversion);
            } else {
                System.out.println("No integer value to evaluate");
            }
            
            
            // - - - - - - - - - -  fraction part  - - - - - - - - - - - - - - 
            if(!fractPartConvertedBin.isEmpty()){
                int count = 1; // count the Quartets
                for(int i = 0; i <= fractPartConvertedBin.length()-4; i+=4){
                    int sum = 0;
                    for( int j = i; j < i+4; j++){
                        sum += (fractPartConvertedBin.charAt(j)=='1') ? (int) Math.pow(2,((i+4)-j)-1) : 0;
                    }
                    System.out.println("Sum of bits at the " + count++ + "(st/nd/rd/th) Quartet: " + sum);

                    if(sum > 9){
                       char a = (char) (sum + 55);
                       fractFinalConversion += a;
                   } else {
                       fractFinalConversion += sum;
                   }
                }
                System.out.println("Base 16 final answer on Fraction side:" + fractFinalConversion);
                
            } else {
                System.out.println("No fraction part to evaluate.");
            }
            finalConversion = (intFinalConversion.length() == 0) ? "0." + fractFinalConversion : intFinalConversion + "." + fractFinalConversion ;
            System.out.println("hex final value: " + finalConversion);
        
            return finalConversion;
        }
        
        return finalConversionBin;
        
    }
    
    
}
