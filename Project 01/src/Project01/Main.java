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
        float[][] coefficients;
        float[][] originalCoefficients = new float [numberOfEquations][numberOfEquations];
        float[] rightHandSide = new float[numberOfEquations];
        float[] results = new float[numberOfEquations];


        getCoefficients(originalCoefficients, rightHandSide, coefficientsOption, numberOfEquations);

        // Saves the original array.
        coefficients = originalCoefficients.clone();


        GaussianElimination.getInstance().scaledPartialPivoting(numberOfEquations,
                coefficients, rightHandSide, results);

        System.out.println("\n");
        for(int i = 0; i < numberOfEquations; i++)
        {
            System.out.println(results[i]);
        }
    }

	public static void getCoefficients(float[][] coefficients, float[] rightHandSide,  int coefficientsOption,
                                       int numberOfEquations)
    {
        switch (coefficientsOption)
        {
            case 1:
                for(int i = 0; i < numberOfEquations; i++)
                {
                    System.out.println("\nEquation #" + (i + 1) + ": ");
                    for(int j = 0; j < numberOfEquations + 1; j++)
                    {
                        if(j < numberOfEquations)
                        {
                            System.out.print("\tC" + (j + 1) + " = ");
                            coefficients[i][j] = scanner.nextFloat();
                        }
                        else
                        {
                            System.out.print("\tRH = ");
                            rightHandSide[i] = scanner.nextFloat();
                        }
                    }
                }
                break;
            case 2:
                FileHandler.getInstance().readFile(coefficients, rightHandSide, "coefficients");
                break;
            default:
        }
    }

    public static void printEquations(float[][] coefficients, float[] rightHandSide, int numberOfEquations)
    {
        for(int i = 0; i < numberOfEquations; i++)
        {
            System.out.println("\t");
            for(int j = 0; j < numberOfEquations + 1; j++)
            {
                if(j < numberOfEquations)
                {
                    if(coefficients[i][j] >= 0 && j > 0)
                        System.out.print("+");

                    System.out.print(coefficients[i][j] + "X" + generateSubscript(j + 1));
                }
                else if(j == numberOfEquations)
                    System.out.print("=" + rightHandSide[i]);
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
