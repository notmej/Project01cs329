package project01;

import java.util.HashMap;

public class Project01 {

    public static void main(String[] args) {

        Project01 obj = new Project01();

        System.out.println(obj.floatMultiplicationConversion());

    }


    // Integer subtraction
    public String intSubtractionConversion(){

        HashMap<Integer, Character> ansAsHM = new HashMap<>();

        String ans = "";

        return ans;
    }



    // Integer multiplication
    public String intMultiplicationConversion(){

        HashMap<Integer, Character> ansAsHM = new HashMap<>();

        String ans = "";

        int result = 0;
        String value = "101";
        int base = 2;

        for(int i = 0; i < value.length(); i++){

            char digit = value.charAt(i);

            int num;

            if(digit >= '0' && digit <= '9'){

                num = digit - '0';

            }else{

                num = digit - 'A' + 10;

            }

            System.out.println("Step " + (i+1));

            result = result * base + num;

            System.out.println("result now = " + result);

        }

        ans = "" + result;

        return ans;
    }



    // Floating multiplication
    public String floatMultiplicationConversion(){

        HashMap<Integer, Character> ansAsHM = new HashMap<>();

        String ans = "";

        double fraction = 0.625;

        int base = 2;

        int precision = 5;

        while(fraction > 0 && precision > 0){

            double temp = fraction * base;

            int integerPart = (int) temp;

            System.out.println("multiply fraction by base");

            System.out.println("take integer part = " + integerPart);

            ans += integerPart;

            fraction = temp - integerPart;

            precision--;

        }

        System.out.println("Final answer = " + ans);

        return ans;

    }

}
