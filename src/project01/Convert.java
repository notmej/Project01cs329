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

    //================================================================= Yunona Agzamova's code ============================================================================
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - color coding stuff for print out readability - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    private static final String ANSI_RESET = "\u001B[0m";

    private static final String ANSI_YELLOW = "\u001B[33m"; // color for any base to denary conversion
    private static final String ANSI_CYAN = "\u001B[36m"; // color for denary to any base conversion
    private static final String ANSI_GREEN = "\u001B[32m"; // Secondary Conversion color
    private static final String ANSI_BLUE = "\u001B[34m"; // Division Method
    private static final String ANSI_BG_PURPLE = "\u001B[45m";  // Final Value + intermediary value volor

    private static void yellowPrint(String text) {
        System.out.println(ANSI_YELLOW + text + ANSI_RESET);
    }

    private static void cyanPrint(String text) {
        System.out.println(ANSI_CYAN + text + ANSI_RESET);
    }

    private static void purpleHighlightPrint(String text) {
        System.out.println(ANSI_BG_PURPLE + text + ANSI_RESET);
    }

    private static void greenPrint(String text) {
        System.out.println(ANSI_GREEN + text + ANSI_RESET);
    }

    private static void bluePrint(String text) {
        System.out.println(ANSI_BLUE + text + ANSI_RESET);
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
    public static String subtractionConvert(String value, int src, int dest, int prec) throws IllegalArgumentException {

        if (dest == 8) {
            prec *= 3;
        } else if (dest == 16) {
            prec *= 4;
        }

        if (src == dest) {
            return value;
        } else if (src == 10 && (dest == 2 || dest == 8 || dest == 16)) {
            return subtractionConvertDenaryToAny(value, dest, prec);
        } else if (src == 2 && (dest == 10 || dest == 8 || dest == 16)) {
            if (dest == 10) {
                return convertAnyToDenary(value, src, prec);
            }
            return subtractionConvertDenaryToAny(convertAnyToDenary(value, src, prec), dest, prec);
        } else if (src == 8 && (dest == 2 || dest == 10 || dest == 16)) {
            if (dest == 10) {
                return convertAnyToDenary(value, src, prec);
            }
            return subtractionConvertDenaryToAny(convertAnyToDenary(value, src, prec), dest, prec);
        } else if (src == 16 && (dest == 2 || dest == 8 || dest == 10)) {
            if (dest == 10) {
                return convertAnyToDenary(value, src, prec);
            }
            return subtractionConvertDenaryToAny(convertAnyToDenary(value, src, prec), dest, prec);
        } else {
            throw new IllegalArgumentException("Invalid source or destination base value, please try again with a valid source or destination value");
        }

    }

    private static String convertAnyToDenary(String value, int src, int prec) {

        // Split input into integer part and fraction part
        String[] parts = value.split("\\.");
        String intPart = parts[0];
        String fractPart = (parts.length > 1) ? parts[1] : "";

        yellowPrint(" - - - - - - - - - - - - - - - Convert from Base to Denary - - - - - - - - - - - - -");
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -Integer part - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        yellowPrint("- - - - - - Integer part  - - - - - - ");
        int decimal = 0;
        int power = intPart.length() - 1;

        for (int i = 0; i < intPart.length(); i++) {
            yellowPrint("processing position in given value: " + power);
            int digit = Character.digit(intPart.charAt(i), src);
            yellowPrint("value at " + power + " position : " + digit);
            if (digit == -1) {
                throw new IllegalArgumentException("Invalid digit");
            }

            // subtraction method: multiply by power of base
            decimal += digit * Math.pow(src, power);
            yellowPrint("Decimal: " + decimal);
            power--;
            yellowPrint("");
        }
        purpleHighlightPrint("Final Decimal Val: " + decimal);
        yellowPrint("");

        // - - - - - - - - - - - - - - - - - - - - - - - -- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - Fraction part - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
        yellowPrint("- - - - - - Fraction part  - - - - - - ");
        double fracResult = 0.0;
        for (int i = 0; i < fractPart.length(); i++) {
            yellowPrint("processing position in given value: " + i);
            int digit = Character.digit(fractPart.charAt(i), src);
            yellowPrint("value at " + i + " position : " + digit);
            if (digit == -1) {
                throw new IllegalArgumentException("Invalid digit");
            }

            // subtract method equivalent: add digit * src^-position
            fracResult += digit / Math.pow(src, i + 1);
            yellowPrint("fraction: " + fracResult);
            yellowPrint("");
        }
        purpleHighlightPrint("Final fraction Val: " + fracResult);
        yellowPrint("");

        if (fracResult == 0) {
            return Integer.toString(decimal);
        }
        double total = decimal + fracResult;
        String formatted = String.format("%." + prec + "f", total);
        purpleHighlightPrint("Final value from base to denary: " + formatted + "\n");
        return formatted;
    }

    /**
     * Converts from denary to any base using the subtraction method UASGE: - IF
     * BASE = 8, MULTIPLY PREC BY 3 - IF BASE = 16, MULTIPLY PREC BY 4
     *
     * @param value The value you need to convert (in denary)
     * @param dest destination base to convert denary value to.
     * @param prec precision point for fractions
     * @return Converted value in destination base
     */
    private static String subtractionConvertDenaryToAny(String value, int dest, int prec) {

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
        cyanPrint("- - - - - - Denary to binary - - - - - - ");
        int intPartIntType = Integer.parseInt(intPart);

        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -Integer part - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        cyanPrint("- - - - - - Integer part  - - - - - - ");
        int highestPower = (int) (Math.log(intPartIntType) / Math.log(2));
        cyanPrint("Find the highest power of " + intPartIntType + " in base " + 2 + ": " + highestPower);

        // loop from highestPower to 0, find which position has a HIGH or LOW Value
        // eg; pow = 8, 2^8 = 256, 328 - 256 >= 0  .... 2^3 = 8, 8-8 >= 0
        for (int i = highestPower; i >= 0; i--) {
            //power value
            int val = (int) (Math.pow(2, i));
            //Check logic
            if (intPartIntType - val >= 0) {
                cyanPrint("current check: " + (intPartIntType - val) + " > 0 is True. Value of 1 at pos 2^" + i);
                intPartConvertedBin = intPartConvertedBin + "1";
                intPartIntType -= val;
            } else if (intPartIntType - val < 0) {
                intPartConvertedBin = intPartConvertedBin + "0";
                cyanPrint("current check: " + (intPartIntType - val) + " > 0 is False. Value of 0 at pos 2^" + i);
            }
        }

        purpleHighlightPrint("\n! --- > Final value of integer part: " + intPartConvertedBin + " base 2");

        // - - - - - - - - - - - - - - - - - - - - - - - -- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - Fraction part - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
        cyanPrint("\n - - - - - - Fraction part  - - - - - - ");
        prec = -prec;
        double fractPartDoubVal = Double.parseDouble(value) - Integer.parseInt(intPart);
        for (int i = -1; i >= prec; i--) {
            cyanPrint("current precision: " + i);
            //power value
            double val = Math.pow(2, i);
            //check logic
            if (fractPartDoubVal - val >= 0) {
                cyanPrint("current check: " + (fractPartDoubVal - val) + " > 0 is True. Value of 1 at pos 2^" + i);
                cyanPrint("" + fractPartDoubVal);
                fractPartConvertedBin = fractPartConvertedBin + "1";
                fractPartDoubVal -= val;
            } else if (fractPartDoubVal - val < 0) {
                fractPartConvertedBin = fractPartConvertedBin + "0";
                cyanPrint("current check: " + (fractPartDoubVal - val) + " > 0 is False. Value of 0 at pos 2^" + i);
            }

        }

        purpleHighlightPrint("! --- > Final value of fract part: " + fractPartConvertedBin + " base 2 \n\n");

        // - - - - - - - - - - - - - -  Combination of fract and binary parts - - - - - - - - - - - - 
        if (intPartConvertedBin.length() == 0) {
            finalConversionBin = "0." + fractPartConvertedBin;
        } else if (fractPartConvertedBin.length() == 0) {
            finalConversionBin = intPartConvertedBin;
        } else {
            finalConversionBin = intPartConvertedBin + "." + fractPartConvertedBin;

        }
        purpleHighlightPrint("- - - > FINAL VALUE IN BINARY: " + finalConversionBin);
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

        //Convert to octal or hex or return as is in binary,
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - Octal conversion:- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        if (dest == 8) {
            // check if int conversion is necessary in the first place

            // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -Integer part - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
            greenPrint("\n- - - - - - Binary to Octal - - - - - - \n - - - - - - Integer Part - - - - - - ");
            greenPrint("Add extra 0's at the end to aid conversion if necessary:");
            if (intPartConvertedBin.length() % 3 != 0) {
                for (int i = 3 - (intPartConvertedBin.length() % 3); i > 0; i--) {
                    intPartConvertedBin = "0" + intPartConvertedBin;

                }
                greenPrint("Current value (Int part): " + intPartConvertedBin);
            } else {
                greenPrint("Extra Zeros Not Necessary.");
            }

            int count = 1; // count the triads
            for (int i = 0; i <= intPartConvertedBin.length() - 3; i += 3) {
                int sum = 0;
                for (int j = i; j < i + 3; j++) {
                    sum += (intPartConvertedBin.charAt(j) == '1') ? (int) Math.pow(2, ((i + 3) - j) - 1) : 0;
                }
                greenPrint("Sum of bits at the " + count++ + "(st/nd/rd/th) triad: " + sum);

                intFinalConversion += sum;
            }
            purpleHighlightPrint("Base 8 final answer on Integer side: " + intFinalConversion);

            // - - - - - - - - - - - - - - - - - - - - - - - -- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - Fraction part - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
            count = 1; // count the triads
            for (int i = 0; i <= fractPartConvertedBin.length() - 3; i += 3) {
                int sum = 0;
                for (int j = i; j < i + 3; j++) {
                    sum += (fractPartConvertedBin.charAt(j) == '1') ? (int) Math.pow(2, ((i + 3) - j) - 1) : 0;
                }
                greenPrint("Sum of bits at the " + count++ + "(st/nd/rd/th) triad: " + sum);

                fractFinalConversion += sum;
            }

            purpleHighlightPrint("Base 8 final answer on Fraction side:" + fractFinalConversion);

            if (intFinalConversion.length() == 0) {
                finalConversion = "0." + fractFinalConversion;
            } else if (fractFinalConversion.length() == 0) {
                finalConversion = intFinalConversion;
            } else {
                finalConversion = intFinalConversion + "." + fractFinalConversion;

            }
            purpleHighlightPrint("Octal final value: " + finalConversion);

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
            greenPrint("- - - - - - Binary to Hex - - - - - - \n - - - - - - Integer Part - - - - - - ");
            greenPrint("Add extra 0's at the end to aid conversion if necessary:");
            if (intPartConvertedBin.length() % 4 != 0) {
                for (int i = 4 - (intPartConvertedBin.length() % 4); i > 0; i--) {
                    intPartConvertedBin = "0" + intPartConvertedBin;
                }
                greenPrint("Current value (Int part): " + intPartConvertedBin);
            } else {
                greenPrint("Extra Zeros Not Necessary.");
            }

            // conversion logic
            int count = 1; // count the Quartets
            for (int i = 0; i <= intPartConvertedBin.length() - 4; i += 4) {
                int sum = 0;
                for (int j = i; j < i + 4; j++) {
                    sum += (intPartConvertedBin.charAt(j) == '1') ? (int) Math.pow(2, ((i + 4) - j) - 1) : 0;
                }
                greenPrint("Sum of bits at the " + count++ + "(st/nd/rd/th) Quartet: " + sum);

                if (sum > 9) {
                    char a = (char) (sum + 55);
                    intFinalConversion += a;
                } else {
                    intFinalConversion += sum;
                }

            }
            purpleHighlightPrint("Base 16 final answer on Integer side: " + intFinalConversion);

            // - - - - - - - - - - - - - - - - - - - - - - - -- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - Fraction part - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
            greenPrint("\n - - - - - - Fraction Part - - - - - - ");
            count = 1; // count the Quartets
            for (int i = 0; i <= fractPartConvertedBin.length() - 4; i += 4) {
                int sum = 0;
                for (int j = i; j < i + 4; j++) {
                    sum += (fractPartConvertedBin.charAt(j) == '1') ? (int) Math.pow(2, ((i + 4) - j) - 1) : 0;
                }
                greenPrint("Sum of bits at the " + count++ + "(st/nd/rd/th) Quartet: " + sum);

                if (sum > 9) {
                    char a = (char) (sum + 55);
                    fractFinalConversion += a;
                } else {
                    fractFinalConversion += sum;
                }
            }
            purpleHighlightPrint("Base 16 final answer on Fraction side:" + fractFinalConversion);

            if (intFinalConversion.length() == 0) {
                finalConversion = "0." + fractFinalConversion;
            } else if (fractFinalConversion.length() == 0) {
                finalConversion = intFinalConversion;
            } else {
                finalConversion = intFinalConversion + "." + fractFinalConversion;

            }
            purpleHighlightPrint("hex final value: " + finalConversion);

            return finalConversion;
        }

        return finalConversionBin;

    }
    // =====================================================================================================================================================================

    // =================================================================== Souaad's code (Multiplication) ===================================================================================
    public static String multiplicationConvert(String value, int src, int destination, int precision) {
        bluePrint("- - - - - - - Multiplication Conversion - - - - - -");
        String[] parts = value.split("\\.");
        String intPart = parts[0];
        String fracPart = (parts.length > 1) ? parts[1] : "";
        String fracResult = fractionMultiplicationConversion(fracPart, src, destination, precision);
        String intResult = intMultiplicationConversion(intPart, src, destination);

        if (fracPart.equals("")) {
            return intResult;
        } else {
            return intResult + "." + fracResult;
        }

    }

    private static String intMultiplicationConversion(String intPart, int src, int destination) {
        System.out.println("- - - - - - - - - Integer Part - - - - - - - ");

        int decimalValue = 0;

        System.out.println("- - - - - - - Source base to Decimal conversion - - - - - - - ");
        for (int i = 0; i < intPart.length(); i++) {

            char ch = intPart.charAt(i);
            int digit;
            // convert char to a numeric value
            if (Character.isDigit(ch)) {
                digit = ch - '0';
            } else {
                digit = ch - 'A' + 10;
            }
            yellowPrint("Step " + (i + 1));
            yellowPrint("Digit = " + ch + " → " + digit);

            decimalValue = decimalValue * src + digit;

            yellowPrint("New Total = previous * " + src + " + " + digit);
            yellowPrint("Total = " + decimalValue);
        }

        if (decimalValue == 0) {
            purpleHighlightPrint("Value is 0 → Result = 0");
            return "0";
        }

        String result = "";

        cyanPrint("- - - - - Decimal to Destination Base - - - - -");

        while (decimalValue > 0) {

            int remainder = decimalValue % destination;
            cyanPrint("Divide: " + decimalValue + " / " + destination);
            cyanPrint("Remainder = " + remainder);

            if (remainder < 10) {
                result = remainder + result;

            } else {
                result = (char) (remainder - 10 + 'A') + result;
            }

            decimalValue = decimalValue / destination;
            cyanPrint("New value = " + decimalValue);
            cyanPrint("Current Result = " + result);
            cyanPrint("-----------------------------------");
        }

        return result;
    }

    private static String fractionMultiplicationConversion(String fracPart, int src, int destination, int precision) {
        if (destination == 10) {
            precision += 1;
        }
        System.out.println("- - - - - - - - - Fraction Part - - - - - - - ");
        // this string will store the final result
        String result = "";

        // convert fraction string to decimal number
        double fraction = 0;

        // convert from source base to decimal
        yellowPrint("- - - - - - - From source base to decimal - - - - - - - - ");
        for (int i = 0; i < fracPart.length(); i++) {

            int digit = Character.getNumericValue(fracPart.charAt(i));

            fraction += digit / Math.pow(src, i + 1);
            yellowPrint("Fraction: " + digit + " / " + Math.pow(src, i + 1) + " = " + fraction);

        }
        int count = 0;
        // multiplication method
        for (int i = 0; i < precision; i++) {

            cyanPrint("Step " + (i + 1));
            fraction = fraction * destination;
            cyanPrint("Multiply: fraction × " + destination + " = " + fraction);

            int integerPart = (int) fraction;
            cyanPrint("Integer Part = " + integerPart);

            if (integerPart < 10) {
                result += integerPart;
                cyanPrint("Digit added = " + integerPart);
            } else {
                char digitChar = (char) (integerPart - 10 + 'A');
                result += digitChar;
                cyanPrint("Digit added = " + digitChar);

            }

            fraction = fraction - integerPart;
            cyanPrint("New Fraction = " + fraction);
            cyanPrint("Current Result = " + result);
            // stop if fraction becomes 0
            if (fraction == 0) {
                purpleHighlightPrint("Fraction became 0 so stopping early");
                break;
            }
            count++;

        }
        if (destination == 10) {
            double temp = Double.parseDouble(result) / Math.pow(10, precision);
            String formatted = String.format("%." + (precision - 1) + "f", temp);
            temp = Double.parseDouble(formatted) * Math.pow(10, precision - 1);
            int temp1 = (int) temp;
            formatted = String.format("%d", temp1);
            result = formatted;
        } else if (destination == 2) {
            if (count < precision) {
                for (; count < precision - 1; count++) {
                    result = result + "0";

                }
            }
        }

        purpleHighlightPrint("Final Converted Fraction Part = " + result);

        return result;

    }
    // ====================================================================================================================================================================================================================

    //======================================================================================== BOSHRAS PART (division) ===================================================================================================
    public static String divisionConvert(String value, int src, int dest, int prec) {
        double decimalValue = Convert.convertToDecimal(value, src);
        String result = Convert.convertFromDecimal(decimalValue, dest, prec);
        return result;
    }

    private static double convertToDecimal(String value, int base) {
        double decimalValue = 0;

        String[] parts = value.split("\\.");
        String integerPart = parts[0];
        String fractionPart = parts.length > 1 ? parts[1] : "";

        int power = integerPart.length() - 1;

        for (int i = 0; i < integerPart.length(); i++) {
            int digit = charToDigit(integerPart.charAt(i));
            double current = digit * Math.pow(base, power);
            decimalValue += current;

            yellowPrint("Integer Conversion: " + digit + " * " + base + "^" + power + " = " + current);
            power--;
        }

        int fractionPower = 1;
        for (int i = 0; i < fractionPart.length(); i++) {
            int digit = charToDigit(fractionPart.charAt(i));
            double current = digit * Math.pow(base, -fractionPower);
            decimalValue += current;

            yellowPrint("Fraction Conversion: " + digit + " * " + base + "^-" + fractionPower + " = " + current);
            fractionPower++;
        }
        purpleHighlightPrint("Decimal value " + decimalValue);
        return decimalValue;
    }

    private static int charToDigit(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'A' && c <= 'F') {
            return c - 'A' + 10;
        }
        throw new IllegalArgumentException("Invalid digit: " + c);
    }

    private static String convertFromDecimal(double decimalValue, int base, int precision) {
        StringBuilder result = new StringBuilder();

        int integerPart = (int) decimalValue;
        result.append(convertIntegerDivision(integerPart, base));

        double fractionalPart = decimalValue - integerPart;

        if (fractionalPart > 0 && precision > 0) {
            result.append(".");
            result.append(convertFractionMultiplication(fractionalPart, base, precision));
        }

        return result.toString();
    }

//// --------------
    private static String convertIntegerDivision(int integerPart, int base) {
        if (integerPart == 0) {
            return "0";
        }

        StringBuilder result = new StringBuilder();

        while (integerPart > 0) {
            int remainder = integerPart % base;

            cyanPrint("Division Step: " + integerPart + " / " + base
                    + " = " + (integerPart / base) + " remainder " + remainder);

            result.insert(0, digitToChar(remainder));
            integerPart /= base;
        }

        return result.toString();
    }

    private static String convertFractionMultiplication(double fractionalPart, int base, int precision) {
        if (base == 10) {
            precision += 1;
        }

        StringBuilder result = new StringBuilder();
        int count = 0;

        while (fractionalPart > 0 && count < precision) {
            fractionalPart *= base;
            int digit = (int) fractionalPart;

            cyanPrint("Multiplication Step: " + fractionalPart
                    + " -> digit = " + digit);

            result.append(digitToChar(digit));
            fractionalPart -= digit;
            count++;
        }

        if (base == 10) {
            if (result.length() > 1) {
                char last = result.charAt(result.length() - 1);
                int lastDigit = last >= 'A' ? last - 'A' + 10 : last - '0';

                // check if we should round previous digit
                if (lastDigit >= base / 2) {
                    int i = result.length() - 2;
                    boolean carry = true;
                    while (i >= 0 && carry) {
                        char c = result.charAt(i);
                        int val = c >= 'A' ? c - 'A' + 10 : c - '0';
                        val++;
                        if (val == base) {
                            result.setCharAt(i, '0');
                            i--;
                        } else {
                            result.setCharAt(i, digitToChar(val));
                            carry = false;
                        }
                    }
                    // if carry propagated past all digits, prepend '1'
                    if (carry) {
                        result.insert(0, '1');
                    }
                }
                // remove the extrs digit used for rounding
                result.setLength(result.length() - 1);
            }
        } else if (base == 2) {
            if (count < precision) {
                for (; count < precision; count++) {
                    result.append("0");

                }
            }
        }

        return result.toString();
    }

    private static char digitToChar(int value) {
        if (value < 10) {
            return (char) ('0' + value);
        }
        return (char) ('A' + (value - 10));
    }
// ================================================================================================================================================================================================================
}
