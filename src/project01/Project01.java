/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package project01;

import java.util.Arrays;


/**
 *
 * @author yuna
 */
public class Project01 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        String bin = "11101011.1010101110011000";
        String output = Convert.subtractionConvert(bin, 2, 10, 4);
        System.out.println("Converty from binary value to denary: " + output);
        
        
    }
    
    
}
