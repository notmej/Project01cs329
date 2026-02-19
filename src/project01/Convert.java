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
public class Convert {
    
    // Integer subtraction
    public static String intSubtractionConversion(String srcVal, int src){
        //Denary --> binary (for now.)
        // this works for denary.
        String convertedValue = "";
        int denSrcVal = Integer.parseInt(srcVal);
        int power = (int) (Math.log(denSrcVal)/Math.log(2));
        int highestPower = power;

        //-----------------------------------------------------------
        //       to store values where bit present
        ArrayList<Integer> powersWhereVal = new ArrayList<>();
        powersWhereVal.add(highestPower);
        
        //String for final output with length of hwre final bit is
        String bin = "";
        for( int i = 0; i <= highestPower; i++){  // <= 
            bin += "0";
            System.out.println(bin);
        }
        
        
        System.out.println("Find maximum possible value of the power: " + power);
        
        int coefficient;
        while (power > 0){
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
        System.out.println(powersWhereVal.size());
        for( int i = 0; i < powersWhereVal.size(); i++){
//            int pos = 
//            bin = bin.substring(0 + pos+1) + "\n" + bin.substring(pos+1, bin.length()) +"\n";
        }

        
        return bin;
    } 
    
}
