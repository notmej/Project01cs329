package project01;

public class Project01 {

    public static void main(String[] args) {

        Project01 obj = new Project01();

        System.out.println(obj.intMultiplicationConversion(5, 3));

        System.out.println(obj.intSubtractionConversion(10, 4));

        System.out.println(obj.floatMultiplicationConversion(2.5f, 4.0f));

        System.out.println(obj.floatSubtractionConversion(7.5f, 2.5f));

    }



    public String intMultiplicationConversion(int num1, int num2){

        int result = num1 * num2;

        return "Int Multiplication: " + result;

    }



    public String intSubtractionConversion(int num1, int num2){

        int result = num1 - num2;

        return "Int Subtraction: " + result;

    }



    public String floatMultiplicationConversion(float num1, float num2){

        float result = num1 * num2;

        return "Float Multiplication: " + result;

    }



    public String floatSubtractionConversion(float num1, float num2){

        float result = num1 - num2;

        return "Float Subtraction: " + result;

    }

}