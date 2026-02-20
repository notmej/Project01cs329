/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project01;

//import java.util.ArrayList;

/**
 *
 * @author yuna
 */
public class Convert {

    
    
//    //converts (main method for subtraction)
//    public static String convert(String value, int src, int destination, int precision){
//        String[] parts = value.split("\\.");
//        String intPart = parts[0];
//        String fracPart = (parts.length > 1) ? parts[1] : "";
//        
//        return intSubtractionConversion(intPart, 10);
//        
//    }
//    
            
//    // Integer subtraction (scratch out probs) (in charge of the integer) 
//    public static String intSubtractionConversion(String srcVal, int src){
//        //Denary --> binary (for now.)
//        // this works for denary.
//        String convertedValue = "";
//        int denSrcVal = Integer.parseInt(srcVal);
//        int power = (int) (Math.log(denSrcVal)/Math.log(2));
//        
//        //arr to store values where bit present
////        ArrayList<Integer> powersWhereVal = new int[power];
////        int index = 0;
////        powersWhereVal[index++] = power;
//        
//        
//        //String for final output
//        String bin = "";
//        for( int i = 0; i < power; i++){
//            bin += "0";
//        }
//
//        
//        
//        System.out.println("Find maximum possible value of the power: " + power);
//        
//        int coefficient;
//        while (power > 0){
//            coefficient = denSrcVal / ((int) (Math.pow(2, power)));
//            denSrcVal = denSrcVal - (coefficient * (int) (Math.pow(2, power)));
//            
//            if(denSrcVal != 0){
//                power = (int) (Math.log(denSrcVal)/Math.log(2));
//            } else {
//                power = 0;
//            }
//            powersWhereVal[index++] = power;
//            
//            System.out.println("Next next power: " + 2 + "^" + power + " current coeff: " + coefficient);
//        }
//        
//        //To-DO:
//        // - fix formatting, currrently given as an array of positions of powers where a bit should go
//        
//        
//        return powersWhereVal;
//    } 
//    
//    
//    //another method in charge of everything after the decimal
//    
}
