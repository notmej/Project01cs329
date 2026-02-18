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
    
    // Integer subtraction
    public static int[] intSubtractionConversion(String srcVal, int src, int dest){
        //Denary --> binary (for now.)
        // this works for denary.
        String convertedValue = "";
        int denSrcVal = Integer.parseInt(srcVal);
        int power = (int) (Math.log(denSrcVal)/Math.log(dest));
        
        int[] powersWhereVal = new int[power];
        int index = 0;
        powersWhereVal[index++] = power;
        
        System.out.println("Find maximum possible value of the power: " + power);
        
        int coefficient;
        while (power > 0){
            coefficient = denSrcVal / ((int) (Math.pow(dest, power)));
            denSrcVal = denSrcVal - (coefficient * (int) (Math.pow(dest, power)));
            
            if(denSrcVal != 0){
                power = (int) (Math.log(denSrcVal)/Math.log(dest));
            } else {
                power = 0;
            }
            powersWhereVal[index++] = power;
            
            System.out.println("Next next power: " + dest + "^" + power + " current coeff: " + coefficient);
        }
        

        
        return powersWhereVal;
    } 
    
}
