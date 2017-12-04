import java.util.ArrayList;

public class PolynomialInterpolation
{
    private static PolynomialInterpolation instance = null;

    public static PolynomialInterpolation getInstance()
    {
        if(instance == null)
            instance = new PolynomialInterpolation();
        return instance;
    }

    private PolynomialInterpolation()
    {
    }

    public void newton(ArrayList<ArrayList<Float>> table, int iteration)
    {
        if(iteration == table.get(0). size() - 1)
            return;

        int iterations = table.get(table.size() - 1).size() - 1;
        ArrayList<Float> coefficients = new ArrayList<>(iterations);
        float coefficient;

        for(int i = 0; i < iterations; i++)
        {
            float fb = table.get(table.size() - 1).get(i + 1);
            float fa = table.get(table.size() - 1).get(i);

            int indexXB = (i + iteration) + 1;
            int indexXA = i;
            float xb = table.get(0).get(indexXB);
            float xa = table.get(0).get(indexXA);

            coefficient = (fb - fa) / (xb - xa);

            //System.out.println(coefficient + " = (" + fb + " - " + fa + ") / (" + xb + " - " + xa + ")");

            coefficients.add(coefficient);
        }

        table.add(coefficients);

        newton(table, iteration + 1);


    }

    public void printDividedDifferenceTable(ArrayList<ArrayList<Float>> table)
    {
        String line = "x       ";

        for(int i = 0; i < table.size() - 1; i++)
        {
            line += "f[";
            for (int j = 0; j < i; j++)
                line += ",";
            line += "]       ";
        }
        System.out.println(line);


        int rows = table.get(0).size() * 2 - 1;
        int center = rows % 2 == 0 ? rows / 2 : rows / 2 + 1;

        for(int row = 0; row < rows; row++)
        {
            line = "";

            if(row == 0)
            {
                line += table.get(0).get(0)
                        + "     " + table.get(1).get(0);
            }
            else if(row == center)
            {
                line += "                " + table.get(2).get(1)
                        + "              " + table.get(table.size() - 1).get(0);
            }

            else if(row == rows - 1)
            {
                line += table.get(0).get(table.get(0).size() - 1)
                        + "     " + table.get(1).get(table.get(1).size() - 1);
            }

            System.out.println(line);
        }

//        for(ArrayList<Float> column : table)
//        {
//
//
//
//            for(float row : column)
//            {
//                System.out.println("Row: " + row);
//            }
//
//            row++;
//        }
    }

    public String printInterpolatingPolynomial(ArrayList<ArrayList<Float>> table)
    {
        String polynomial = "";

        for(int i = 1; i < table.size(); i++)
        {
            polynomial += table.get(i).get(0);

            if(i > 1)
            {
                for(int j = 0; j < i - 1; j++)
                {
                    polynomial += "(x-" + table.get(0).get(j)
                            + ")";
                }
            }

            if(i != table.size() - 1)
            {
                if(table.get(i + 1).get(0) >= 0)
                    polynomial += "+";
            }

        }

        return polynomial;
    }

    public String printSimplifiedPolynomial(ArrayList<ArrayList<Float>> table)
    {
        String polynomial = "";

        return polynomial;
    }

    public String lagrange(ArrayList<ArrayList<Float>> table)
    {
        String polynomial = "";

        for(int i = 0; i < table.get(1).size(); i++)
        {
            polynomial += table.get(1).get(i) + " (";

            for(int j = 0; j < table.get(0).size(); j++)
            {
                if(j == i)
                    continue;

                polynomial += "(x - " + table.get(0).get(j)
                        + ")";
            }

            polynomial += ") / (";
            for(int j = 0; j < table.get(0).size(); j++)
            {
                if(j == i)
                    continue;

                polynomial += "(" + table.get(0).get(i)
                        + " - " + table.get(0).get(j)
                        + ")";
            }
            polynomial += ")";

            if(i != table.get(1).size() - 1)
            {
                if(table.get(1).get(i) >= 0)
                    polynomial += " + ";
            }

        }
        return polynomial;
    }
}
