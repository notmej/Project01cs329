/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package project01;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author yuna
 */
public class ConvertTest {
    
    public ConvertTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of subtractionConvert method, of class Convert.
     */
    @Test
    public void testSubtractionConvert() {
        // bin : "11101011.1010101110011000"
        // octal;  "353.52714"
        // hex: "EB.AB98"
        // decimal: "235.6703"
        
        
        //bin --> other base
        assertEquals(Convert.subtractionConvert("11101011.1010101110011000", 2, 10, 4), "235.6703"); 
        assertEquals(Convert.subtractionConvert("11101011.1010101110011000", 2, 8, 5), "353.52714");
        assertEquals(Convert.subtractionConvert("11101011.1010101110011000", 2, 16, 4), "EB.AB98");
        
        assertEquals(Convert.subtractionConvert("11101011", 2, 10, 0), "235"); 
        assertEquals( "353", Convert.subtractionConvert("11101011", 2, 8, 0));/// this problkematic
        assertEquals(Convert.subtractionConvert("11101011", 2, 16, 0), "EB");
        
        //Den --> other base
        assertEquals(Convert.subtractionConvert("235.6703", 10, 2, 16), "11101011.1010101110011000");
        assertEquals(Convert.subtractionConvert("235.6703", 10, 8, 5), "353.52714");
        assertEquals(Convert.subtractionConvert("235.6703", 10, 16, 4), "EB.AB98");
        
        assertEquals(Convert.subtractionConvert("235", 10, 2, 0), "11101011");
        assertEquals(Convert.subtractionConvert("235", 10, 8, 0), "353");
        assertEquals(Convert.subtractionConvert("235", 10, 16, 0), "EB");
        
        //octal --> other base
        assertEquals(Convert.subtractionConvert("353.52714", 8, 10, 4), "235.6703");
        assertEquals(Convert.subtractionConvert("353.52714", 8, 2, 16), "11101011.1010101110011000"); /// ---!
        assertEquals(Convert.subtractionConvert("353.52714", 8, 16, 4), "EB.AB98");
        
        assertEquals( "235", Convert.subtractionConvert("353", 8, 10, 0));
        assertEquals( "11101011", Convert.subtractionConvert("353", 8, 2, 0)); /// ---!
        assertEquals( "EB", Convert.subtractionConvert("353", 8, 16, 0));
        
        //hex --> otherbase
        assertEquals(Convert.subtractionConvert("EB.AB98", 16, 2, 16), "11101011.1010101110011000");
        assertEquals(Convert.subtractionConvert("EB.AB98", 16, 10, 4), "235.6703");
        assertEquals(Convert.subtractionConvert("EB.AB98", 16, 8, 5),"353.52714");
        
        assertEquals(Convert.subtractionConvert("EB", 16, 2, 0), "11101011");
        assertEquals(Convert.subtractionConvert("EB", 16, 10, 0), "235");
        assertEquals(Convert.subtractionConvert("EB", 16, 8, 0),"353");
    }

    /**
     * Test of multiplicationConvert method, of class Convert.
     */
    @Test
    public void testMultiplicationConvert() {
        // bin : "11101011.1010101110011000"
        // octal;  "353.52714"
        // hex: "EB.AB98"
        // decimal: "235.6703"
        
        
        //bin --> other base
        assertEquals("235.6703", Convert.multiplicationConvert("11101011.1010101110011000", 2, 10, 4)); 
        assertEquals( "353.52714", Convert.multiplicationConvert("11101011.1010101110011000", 2, 8, 5));
        assertEquals( "EB.AB98", Convert.multiplicationConvert("11101011.1010101110011000", 2, 16, 4));
        
        assertEquals(Convert.multiplicationConvert("11101011", 2, 10, 0), "235"); 
        assertEquals(Convert.multiplicationConvert("11101011", 2, 8, 0), "353");
        assertEquals(Convert.multiplicationConvert("11101011", 2, 16, 0), "EB");
        
        //Den --> other base
        assertEquals( "11101011.1010101110011000", Convert.multiplicationConvert("235.6703", 10, 2, 16));
        assertEquals( "353.52714", Convert.multiplicationConvert("235.6703", 10, 8, 5));
        assertEquals("EB.AB98", Convert.multiplicationConvert("235.6703", 10, 16, 4));
        
        
        assertEquals(Convert.multiplicationConvert("235", 10, 2, 0), "11101011");
        assertEquals(Convert.multiplicationConvert("235", 10, 8, 0), "353");
        assertEquals(Convert.multiplicationConvert("235", 10, 16, 0), "EB");
        
        //octal --> other base
        assertEquals( "235.6703",Convert.multiplicationConvert("353.52714", 8, 10, 4));
        assertEquals( "11101011.1010101110011000", Convert.multiplicationConvert("353.52714", 8, 2, 16));
        assertEquals( "EB.AB98", Convert.multiplicationConvert("353.52714", 8, 16, 4));
        
        assertEquals(Convert.multiplicationConvert("353", 8, 10, 0), "235");
        assertEquals(Convert.multiplicationConvert("353", 8, 2, 0), "11101011"); /// ---!
        assertEquals(Convert.multiplicationConvert("353", 8, 16, 0), "EB");
        
        //hex --> otherbase
        assertEquals( "11101011.1010101110011000", Convert.multiplicationConvert("EB.AB98", 16, 2, 16));
        assertEquals( "235.6703", Convert.multiplicationConvert("EB.AB98", 16, 10, 4));
        assertEquals("353.52714", Convert.multiplicationConvert("EB.AB98", 16, 8, 5));
        
        assertEquals(Convert.multiplicationConvert("EB", 16, 2, 0), "11101011");
        assertEquals(Convert.multiplicationConvert("EB", 16, 10, 0), "235");
        assertEquals(Convert.multiplicationConvert("EB", 16, 8, 0),"353");
    }

    /**
     * Test of divisionConvert method, of class Convert.
     */
    @Test
    public void testDivisionConvert() {
        
        // bin : "11101011.1010101110011000"
        // octal;  "353.52714"
        // hex: "EB.AB98"
        // decimal: "235.6703"
        
        
        //bin --> other base
        assertEquals( "235.6703",Convert.divisionConvert("11101011.1010101110011000", 2, 10, 4)); 
        assertEquals( "353.52714",Convert.divisionConvert("11101011.1010101110011000", 2, 8, 5));
        assertEquals( "EB.AB98",Convert.divisionConvert("11101011.1010101110011000", 2, 16, 4));
        
        //Den --> other base
        assertEquals( "11101011.1010101110011000", Convert.divisionConvert("235.6703", 10, 2, 16));
        assertEquals( "353.52714", Convert.divisionConvert("235.6703", 10, 8, 5));
        assertEquals( "EB.AB98", Convert.divisionConvert("235.6703", 10, 16, 4));
        
        //octal --> other base
        assertEquals( "235.6703", Convert.divisionConvert("353.52714", 8, 10, 4));
        assertEquals( "11101011.1010101110011000", Convert.divisionConvert("353.52714", 8, 2, 16));
        assertEquals( "EB.AB98", Convert.divisionConvert("353.52714", 8, 16, 4));
        
        //hex --> otherbase
        assertEquals( "11101011.1010101110011000", Convert.divisionConvert("EB.AB98", 16, 2, 16));
        assertEquals( "235.6703", Convert.divisionConvert("EB.AB98", 16, 10, 4));
        assertEquals("353.52714", Convert.divisionConvert("EB.AB98", 16, 8, 5));
        
        assertEquals(Convert.divisionConvert("11101011", 2, 10, 0), "235"); 
        assertEquals(Convert.divisionConvert("11101011", 2, 8, 0), "353");
        assertEquals(Convert.divisionConvert("11101011", 2, 16, 0), "EB");
        
        assertEquals(Convert.divisionConvert("235", 10, 2, 0), "11101011");
        assertEquals(Convert.divisionConvert("235", 10, 8, 0), "353");
        assertEquals(Convert.divisionConvert("235", 10, 16, 0), "EB");
        
        assertEquals(Convert.divisionConvert("353", 8, 10, 0), "235");
        assertEquals(Convert.divisionConvert("353", 8, 2, 0), "11101011"); /// ---!
        assertEquals(Convert.divisionConvert("353", 8, 16, 0), "EB");
        
        assertEquals(Convert.divisionConvert("EB", 16, 2, 0), "11101011");
        assertEquals(Convert.divisionConvert("EB", 16, 10, 0), "235");
        assertEquals(Convert.divisionConvert("EB", 16, 8, 0),"353");
        
    }
    
}
