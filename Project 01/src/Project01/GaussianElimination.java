package Project01;

public class GaussianElimination
{
    private static GaussianElimination instance = null;
    private int numberOfEquations;
    private float[][] coefficients;
    private float[] rightHandSide;
    private float[] results;

    private GaussianElimination()
    {
    }

    public static GaussianElimination getInstance()
    {
        if(instance == null)
            instance = new GaussianElimination();
        return instance;
    }

    public void scaledPartialPivoting(int numberOfEquations, float[][] coefficients,
                                      float[] rightHandSide, float[] results)
    {
        this.numberOfEquations = numberOfEquations;
        this.coefficients = coefficients;
        this.rightHandSide = rightHandSide;
        this.results = results;

        int[] indices = new int[numberOfEquations];

        printEquations(coefficients, rightHandSide, numberOfEquations);
        forwardEliminationSPP(indices);
        printEquations(coefficients, rightHandSide, numberOfEquations);
        backSubstitution(indices);
    }

    private void forwardEliminationSPP(int[] indices)
    {
        int i = 0;
        int j = 0;
        int k = 0;
        float ratio;
        float largestRatio;
        float xmult;
        float[] scales = new float[numberOfEquations];

        // Initializes the indices array with the natural order
        // of the equations. Then it finds the largest coefficient
        // on each equation and stores it on the scales array.
        for(i = 0; i < numberOfEquations; i++)
        {
            indices[i] = i;
            scales[i] = max(coefficients, numberOfEquations, i);
        }

        // Calculates the largest ratio and finds the pivot row.
        for(k = 0; k < numberOfEquations - 1; k++)
        {
            largestRatio = 0;
            for(i = k; i < numberOfEquations; i++)
            {
                ratio = Math.abs(coefficients[indices[i]][k] / scales[indices[i]]);
                if(ratio > largestRatio)
                {
                    largestRatio = ratio;
                    j = i;
                }
            }

            // Swap the indices of the equations.
            // This simulates moving the pivot row.
            int temp = indices[j];
            indices[j] = indices[k];
            indices[k] = temp;

            // Does the subtraction.
            for(i = k + 1; i < numberOfEquations; i++)
            {
                xmult = coefficients[indices[i]][k] / coefficients[indices[k]][k];
                coefficients[indices[i]][k] = xmult;
                // The line below allows you to see the coefficients array in its final form.
                //coefficients[indices[i]][k] = 0;
                for (j = k + 1; j < numberOfEquations; j++)
                {
                    coefficients[indices[i]][j] = coefficients[indices[i]][j]
                            - (xmult * coefficients[indices[k]][j]);
                }
            }
        }

        for(k = 0; k < numberOfEquations - 1; k++)
        {
            for(i = k + 1; i < numberOfEquations; i++)
            {
                rightHandSide[indices[i]] = rightHandSide[indices[i]]
                        - (coefficients[indices[i]][k] * rightHandSide[indices[k]]);
            }
        }
    }

    public float max(float[][] coefficients, int numberOfEquations, int row)
    {
        float maxCoefficient = Math.abs(coefficients[row][0]);

        for(int i = 0; i < numberOfEquations; i++)
        {
            if(Math.abs(coefficients[row][i]) > maxCoefficient)
                maxCoefficient = Math.abs(coefficients[row][i]);
        }

        return maxCoefficient;
    }

    private void backSubstitution(int[] indices)
    {
        int i;
        float sum;

        for(int index = 0; index < numberOfEquations; index++)
        {
            results[index] = rightHandSide[indices[index]] / coefficients[indices[index]][index];
        }

        for(i = numberOfEquations - 1; i > 0; i--)
        {
            sum = rightHandSide[indices[i]];
            for(int j = i + 1; j < numberOfEquations; j++)
                sum = sum - (coefficients[indices[i]][j] * results[j]);
            results[i] = sum / coefficients[indices[i]][i];
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
