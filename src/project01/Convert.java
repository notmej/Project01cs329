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
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - color coding stuff for print our readability - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_YELLOW = "\u001B[33m"; // color for any base to denary conversion
    private static final String ANSI_CYAN = "\u001B[36m"; // color for denary to any base conversion
    private static final String ANSI_BG_PURPLE = "\u001B[45m";  // Final Value + intermediary value volor
    private static final String ANSI_GREEN = "\u001B[32m"; // Secondary Conversion color

    private static void baseToDenStepsColor(String text) {
        System.out.println(ANSI_YELLOW + text + ANSI_RESET);
    }
    private static void denToBinStepsColor(String text) {
        System.out.println(ANSI_CYAN + text + ANSI_RESET);
    }
    private static void finalAndIntermediaryValues(String text) {
        System.out.println(ANSI_BG_PURPLE + text + ANSI_RESET);
    }
    private static void secondaryConversionValues(String text) {
        System.out.println(ANSI_GREEN + text + ANSI_RESET);
    } 
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
    
    public static String subtractionConvert(String value, int src, int dest, int prec) throws IllegalArgumentException {

        if( src == dest){
            return value;
        }else if (src == 10 && (dest == 2 || dest == 8 || dest == 16)) {
            return subtractionConvertDenaryToAny(value, dest, prec); 
        }else if (src == 2 && (dest == 10 || dest == 8 || dest == 16)) {
            if (dest == 10) { 
                return convertAnyToDenary(value, src, prec);
            }
            return subtractionConvertDenaryToAny(convertAnyToDenary(value, src, prec), dest, prec);
        } else if (src == 8 && (dest == 2 || dest == 10|| dest == 16)){
            return subtractionConvertDenaryToAny(convertAnyToDenary(value, src, prec), dest, prec);
        } else if (src == 16 && (dest == 2 || dest == 8 || dest == 10)){
            return subtractionConvertDenaryToAny(convertAnyToDenary(value, src, prec), dest, prec);
        }else {
            throw new IllegalArgumentException("Invalid source or destination base value, please try again with a valid source or destination value");
        }

    }

    private static String convertAnyToDenary(String value, int src, int prec) {

        // Split input into integer part and fraction part
        String[] parts = value.split("\\.");
        String intPart = parts[0];
        String fractPart = (parts.length > 1) ? parts[1] : "";

        baseToDenStepsColor(" - - - - - - - - - - - - - - - Convert from Base to Denary - - - - - - - - - - - - -");
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -Integer part - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        baseToDenStepsColor("- - - - - - Integer part  - - - - - - ");
        int decimal = 0;
        int power = intPart.length() - 1;

        for (int i = 0; i < intPart.length(); i++) {
            baseToDenStepsColor("processing position in given value: " + power);
            int digit = Character.digit(intPart.charAt(i), src);
            baseToDenStepsColor("value at " + power + " position : " + digit);
            if (digit == -1) {
                throw new IllegalArgumentException("Invalid digit");
            }

            // subtraction method: multiply by power of base
            decimal += digit * Math.pow(src, power);
            baseToDenStepsColor("Decimal: " + decimal);
            power--;
            baseToDenStepsColor("");
        }
        finalAndIntermediaryValues("Final Decimal Val: " + decimal);
        baseToDenStepsColor("");

        // - - - - - - - - - - - - - - - - - - - - - - - -- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - Fraction part - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
        baseToDenStepsColor("- - - - - - Fraction part  - - - - - - ");
        double fracResult = 0.0;
        for (int i = 0; i < fractPart.length(); i++) {
            baseToDenStepsColor("processing position in given value: " + i);
            int digit = Character.digit(fractPart.charAt(i), src);
            baseToDenStepsColor("value at " + i + " position : " + digit);
            if (digit == -1) {
                throw new IllegalArgumentException("Invalid digit");
            }

            // subtract method equivalent: add digit * src^-position
            fracResult += digit / Math.pow(src, i + 1);
            baseToDenStepsColor("fraction: " + fracResult);
            baseToDenStepsColor("");
        }
        finalAndIntermediaryValues("Final fraction Val: " + fracResult);
        baseToDenStepsColor("");
        
        double total = decimal + fracResult;
        String formatted = String.format("%." + prec + "f", total);
        finalAndIntermediaryValues("Final value from base to denary: " + formatted + "\n");
        return formatted;
    }

    /**
     * Converts from denary to any base using the subtraction method
     *
     * @param value The value you need to convert (in denary)
     * @param dest destination base to convert denary value to.
     * @param prec precision point for fractions
     * @return Converted value in destination base
     */
    private static String subtractionConvertDenaryToAny(String value, int dest, int prec) {
        
        //adjusting precision points based on destination.
        if(dest == 8){
            prec *= 3;
        } else if (dest == 16) {
            prec *=4;
        }

        //splits input into integer part and decimal part
        String[] parts = value.split("\\.");
        String intPart = parts[0];
        String fractPart = (parts.length > 1) ? parts[1] : "";

        //where to put conversion ans
        String intPartConvertedBin = "";
        String fractPartConvertedBin = "";
        String finalConversionBin = "";

        String intFinalConversion = "";
        String fractFinalConversion = "";
        String finalConversion = "";

        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -Denary to Binary- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        denToBinStepsColor("- - - - - - Denary to binary - - - - - - ");
        int intPartIntType = Integer.parseInt(intPart);

        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -Integer part - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        denToBinStepsColor("- - - - - - Integer part  - - - - - - ");
        int highestPower = (int) (Math.log(intPartIntType) / Math.log(2));
        denToBinStepsColor("Find the highest power of " + intPartIntType + " in base " + 2 + ": " + highestPower);

        // loop from highestPower to 0, find which position has a HIGH or LOW Value
        // eg; pow = 8, 2^8 = 256, 328 - 256 >= 0  .... 2^3 = 8, 8-8 >= 0
        for (int i = highestPower; i >= 0; i--) {
            //power value
            int val = (int) (Math.pow(2, i));
            //Check logic
            if (intPartIntType - val >= 0) {
                denToBinStepsColor("current check: " + (intPartIntType - val) + " > 0 is True. Value of 1 at pos 2^" + i);
                intPartConvertedBin = intPartConvertedBin + "1";
                intPartIntType -= val;
            } else if (intPartIntType - val < 0) {
                intPartConvertedBin = intPartConvertedBin + "0";
                denToBinStepsColor("current check: " + (intPartIntType - val) + " > 0 is False. Value of 0 at pos 2^" + i);
            }
        }

        finalAndIntermediaryValues("\n! --- > Final value of integer part: " + intPartConvertedBin + " base 2");

        // - - - - - - - - - - - - - - - - - - - - - - - -- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - Fraction part - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
        denToBinStepsColor("\n - - - - - - Fraction part  - - - - - - ");
        prec = -prec;
        double fractPartDoubVal = Double.parseDouble(value) - Integer.parseInt(intPart);
        for (int i = -1; i >= prec; i--) {
            denToBinStepsColor("current precision: " + i);
            //power value
            double val = Math.pow(2, i);
            //check logic
            if (fractPartDoubVal - val >= 0) {
                denToBinStepsColor("current check: " + (fractPartDoubVal - val) + " > 0 is True. Value of 1 at pos 2^" + i);
                denToBinStepsColor(""+fractPartDoubVal);
                fractPartConvertedBin = fractPartConvertedBin + "1";
                fractPartDoubVal -= val;
            } else if (fractPartDoubVal - val < 0) {
                fractPartConvertedBin = fractPartConvertedBin + "0";
                denToBinStepsColor("current check: " + (fractPartDoubVal - val) + " > 0 is False. Value of 0 at pos 2^" + i);
            }

        }

        finalAndIntermediaryValues("! --- > Final value of fract part: " + fractPartConvertedBin + " base 2 \n\n");

        // - - - - - - - - - - - - - -  Combination of fract and binary parts - - - - - - - - - - - - 
        finalConversionBin = (intPartConvertedBin.length() == 0) ? "0." + fractPartConvertedBin : intPartConvertedBin + "." + fractPartConvertedBin;
        finalAndIntermediaryValues("- - - > FINAL VALUE IN BINARY: " + finalConversionBin);
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

        //Convert to octal or hex or return as is in binary,
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - Octal conversion:- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        if (dest == 8) {
            // check if int conversion is necessary in the first place

            // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -Integer part - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
            secondaryConversionValues("\n- - - - - - Binary to Octal - - - - - - \n - - - - - - Integer Part - - - - - - ");
            secondaryConversionValues("Add extra 0's at the end to aid conversion if necessary:");
            if (intPartConvertedBin.length() % 3 != 0) {
                for (int i = 3 - (intPartConvertedBin.length() % 3); i > 0; i--) {
                    intPartConvertedBin = "0" + intPartConvertedBin;

                }
                secondaryConversionValues("Current value (Int part): " + intPartConvertedBin);
            } else {
                secondaryConversionValues("Extra Zeros Not Necessary.");
            }

            int count = 1; // count the triads
            for (int i = 0; i <= intPartConvertedBin.length() - 3; i += 3) {
                int sum = 0;
                for (int j = i; j < i + 3; j++) {
                    sum += (intPartConvertedBin.charAt(j) == '1') ? (int) Math.pow(2, ((i + 3) - j) - 1) : 0;
                }
                secondaryConversionValues("Sum of bits at the " + count++ + "(st/nd/rd/th) triad: " + sum);

                intFinalConversion += sum;
            }
            finalAndIntermediaryValues("Base 8 final answer on Integer side: " + intFinalConversion);

        // - - - - - - - - - - - - - - - - - - - - - - - -- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - Fraction part - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
            count = 1; // count the triads
            for (int i = 0; i <= fractPartConvertedBin.length() - 3; i += 3) {
                int sum = 0;
                for (int j = i; j < i + 3; j++) {
                    sum += (fractPartConvertedBin.charAt(j) == '1') ? (int) Math.pow(2, ((i + 3) - j) - 1) : 0;
                }
                secondaryConversionValues("Sum of bits at the " + count++ + "(st/nd/rd/th) triad: " + sum);

                fractFinalConversion += sum;
            }

            finalAndIntermediaryValues("Base 8 final answer on Fraction side:" + fractFinalConversion);

            finalConversion = (intFinalConversion.length() == 0) ? "0." + fractFinalConversion : intFinalConversion + "." + fractFinalConversion;
            finalAndIntermediaryValues("Octal final value: " + finalConversion);

            return finalConversion;

        } else if (dest == 16) {

            /**
             * Note for hex A: 10 B: 11 C: 12 D: 13 E: 14 F: 15
             *
             *
             * ASCII A: char a = (char)(10 + 55);
             */
            // - - - - - - - - - - - - - - - - - - - - - - - - - - - - Hex Conversion - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
            // check if int conversion is necessary in the first place
            // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -Integer part - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
            secondaryConversionValues("- - - - - - Binary to Hex - - - - - - \n - - - - - - Integer Part - - - - - - ");
            secondaryConversionValues("Add extra 0's at the end to aid conversion if necessary:");
            if (intPartConvertedBin.length() % 4 != 0) {
                for (int i = 4 - (intPartConvertedBin.length() % 4); i > 0; i--) {
                    intPartConvertedBin = "0" + intPartConvertedBin;
                }
                secondaryConversionValues("Current value (Int part): " + intPartConvertedBin);
            } else {
                secondaryConversionValues("Extra Zeros Not Necessary.");
            }

            // conversion logic
            int count = 1; // count the Quartets
            for (int i = 0; i <= intPartConvertedBin.length() - 4; i += 4) {
                int sum = 0;
                for (int j = i; j < i + 4; j++) {
                    sum += (intPartConvertedBin.charAt(j) == '1') ? (int) Math.pow(2, ((i + 4) - j) - 1) : 0;
                }
                secondaryConversionValues("Sum of bits at the " + count++ + "(st/nd/rd/th) Quartet: " + sum);

                if (sum > 9) {
                    char a = (char) (sum + 55);
                    intFinalConversion += a;
                } else {
                    intFinalConversion += sum;
                }

            }
            finalAndIntermediaryValues("Base 16 final answer on Integer side: " + intFinalConversion);

        // - - - - - - - - - - - - - - - - - - - - - - - -- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - Fraction part - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
            secondaryConversionValues("\n - - - - - - Fraction Part - - - - - - ");
            count = 1; // count the Quartets
            for (int i = 0; i <= fractPartConvertedBin.length() - 4; i += 4) {
                int sum = 0;
                for (int j = i; j < i + 4; j++) {
                    sum += (fractPartConvertedBin.charAt(j) == '1') ? (int) Math.pow(2, ((i + 4) - j) - 1) : 0;
                }
                secondaryConversionValues("Sum of bits at the " + count++ + "(st/nd/rd/th) Quartet: " + sum);

                if (sum > 9) {
                    char a = (char) (sum + 55);
                    fractFinalConversion += a;
                } else {
                    fractFinalConversion += sum;
                }
            }
            finalAndIntermediaryValues("Base 16 final answer on Fraction side:" + fractFinalConversion);

            finalConversion = (intFinalConversion.length() == 0) ? "0." + fractFinalConversion : intFinalConversion + "." + fractFinalConversion;
            finalAndIntermediaryValues("hex final value: " + finalConversion);

            return finalConversion;
        }

        return finalConversionBin;

    }

}
