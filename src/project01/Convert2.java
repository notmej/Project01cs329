/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project01;

import java.util.ArrayList;

/**
 *
 * @author yuna
 */
public class Convert2 {

    
    
    //converts (main method for subtraction. takes in DENARY VALUE)
    public static String convert(String value, int src, int destination, int precision){
        
        //splits input into integer part and decimal part
        String[] parts = value.split("\\.");
        String intPart = parts[0];
        String fracPart = (parts.length > 1) ? parts[1] : "";
        
        return intSubtractionConversion(intPart, 10);
        
    }
    
            
    // Integer subtraction (in charge of the integer part) 
    public static String intSubtractionConversion(String srcVal, int src){
        //Denary --> binary (for now.)
        // this works for denary.
        String convertedValue = "";
        int denSrcVal = Integer.parseInt(srcVal);
        int power = (int) (Math.log(denSrcVal)/Math.log(2));
        
        //arr to store values where bit present
        ArrayList<Integer> powersWhereVal = new ArrayList<>();
        int index = 0;
        powersWhereVal.add(power);
        
        
        //String for final output with length of hwre final bit is
        String bin = "";
        for( int i = 0; i <= power; i++){
            bin += "0";
            System.out.println(bin);
        }

        
        
        System.out.println("Find maximum possible value of the power: " + power);
        
        int coefficient;
        while (power > 0){ // only positive values
            coefficient = denSrcVal / ((int) (Math.pow(2, power)));
            denSrcVal = denSrcVal - (coefficient * (int) (Math.pow(2, power)));
            
            if(denSrcVal != 0){
                power = (int) (Math.log(denSrcVal)/Math.log(2));
            } else {
                power = 0;
            }
            powersWhereVal.add(power);
            
            System.out.println("Next next power: " + 2 + "^" + power + " current coeff: " + coefficient);
        }
        
        System.out.println(powersWhereVal.toString());
        // - fix formatting, currrently given as an ArrayList of positions of powers where a HIGH bit should go
        // ignore output for now and the loop below
        for( int i = 0; i < powersWhereVal.size(); i++){
            
        }
        
        return bin;
    } 
   
    
    //another method in charge of everything after the decimal
    
}
