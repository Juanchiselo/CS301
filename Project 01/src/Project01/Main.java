package Project01;

import java.util.Scanner;

public class Main
{
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args)
    {
        int numberOfEquations;
        int coefficientsOption;

        System.out.print("Please enter the number of linear equations to solve: \nn = ");
        numberOfEquations = scanner.nextInt();

        System.out.print("\nWould you like to enter the coefficients of each equation or " +
                "would you like to provide a file name that has\na matrix which has the " +
                "equation coefficients with the right hand column vector added to this " +
                "coefficient matrix? " +
                "\n\t1) Enter coefficients manually." +
                "\n\t2) Enter file name." +
                "\nChoice: ");
        coefficientsOption = scanner.nextInt();


        // Creates an array to hold the coefficients.
        float[][] coefficients = new float[numberOfEquations][numberOfEquations];

        getCoefficients(coefficients, coefficientsOption, numberOfEquations);

        printCoefficients(coefficients, numberOfEquations);


        System.out.print("X" + generateSubscript(1));

	}

	public static void getCoefficients(float[][] coefficients, int coefficientsOption,
                                       int numberOfEquations)
    {
        switch (coefficientsOption)
        {
            case 1:
                for(int i = 0; i < numberOfEquations; i++)
                {
                    System.out.println("\nEquation #" + (i + 1) + ": ");
                    for(int j = 0; j < numberOfEquations; j++)
                    {
                        System.out.print("\tC" + (j + 1) + " = ");
                        coefficients[i][j] = scanner.nextFloat();
                    }
                }
                break;
            case 2:
                break;
            default:
        }

    }

    public static void printCoefficients(float[][] coefficients, int numberOfEquations)
    {
        for(int i = 0; i < numberOfEquations; i++)
        {
            System.out.println("\nEquation #" + (i + 1) + ": ");
            for(int j = 0; j < numberOfEquations; j++)
            {
                System.out.print("\tC" + (j + 1) + " = " + coefficients[i][j]);
            }
        }
    }

    public static String generateSubscript(int i)
    {
        StringBuilder sb = new StringBuilder();
        for (char ch : String.valueOf(i).toCharArray()) {
            sb.append((char) ('\u2080' + (ch - '0')));
        }
        return sb.toString();
    }
}
