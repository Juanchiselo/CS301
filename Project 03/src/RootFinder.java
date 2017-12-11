import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RootFinder
{
    private static RootFinder instance = null;

    public static RootFinder getInstance()
    {
        if(instance == null)
            instance = new RootFinder();
        return instance;
    }

    private RootFinder()
    {
    }

    public ObservableList<RootRow> Bisection(String equation, double a, double b,
                                    double nmax, double epsilon)
    {
        ObservableList<RootRow> data = FXCollections.observableArrayList();

        int n;
        double c = 0;
        double fa = 0;
        double fb = 0;
        double fc = 0;
        double error;

        if(equation.equals("A"))
        {
            fa = EquationEvaluator.getInstance().equationA(a);
            fb = EquationEvaluator.getInstance().equationA(b);
        }
        else
        {
            fa = EquationEvaluator.getInstance().equationB(a);
            fb = EquationEvaluator.getInstance().equationB(b);
        }

        if(fa * fb >= 0)
        {
            Main.mainWindowController.setStatus("ERROR", "Function has same signs at a and b."
                + " a = " + a + ", b = " + b + ", f(a) = " + fa + ", f(b) = " + fb);
            return null;
        }

        error = b - a;

        for(n = 0; n < nmax; n++)
        {
            error = error / 2;
            c = a + error;

            if(equation.equals("A"))
                fc = EquationEvaluator.getInstance().equationA(c);
            else
                fc = EquationEvaluator.getInstance().equationB(c);

            data.add(new RootRow(n, a, b, c, fa, fb, fc, error));

            if(Math.abs(error) < epsilon)
            {
                Main.mainWindowController.setStatus("Status", "Convergence!");
                return data;
            }

            if(fa * fc < 0)
            {
                b = c;
                fb = fc;
            }
            else
            {
                a = c;
                fa = fc;
            }
        }

        return data;
    }

    public ObservableList<RootRow> Newton(String equation, double x,
                                          double nmax, double epsilon)
    {
        ObservableList<RootRow> data = FXCollections.observableArrayList();

        int n;
        double fx;
        double fp;
        double delta = 0;

        if(equation.equals("A"))
            fx = EquationEvaluator.getInstance().equationA(x);
        else
            fx = EquationEvaluator.getInstance().equationB(x);

        for(n = 0; n < nmax; n++)
        {
            if(equation.equals("A"))
                fp = EquationEvaluator.getInstance().equationAP(x);
            else
                fp = EquationEvaluator.getInstance().equationBP(x);

            if(Math.abs(fp) < delta)
            {
                Main.mainWindowController.setStatus("ERROR", "Small derivative.");
                return null;
            }

            double d = fx / fp;
            data.add(new RootRow(n, x, fx, fp, x - d, Math.abs(d)));
            x = x - d;
            if(equation.equals("A"))
                fx = EquationEvaluator.getInstance().equationA(x);
            else
                fx = EquationEvaluator.getInstance().equationB(x);

            if(Math.abs(d) < epsilon)
            {
                Main.mainWindowController.setStatus("Status", "Convergence.");
                return data;
            }
        }

        return data;
    }

    public ObservableList<RootRow> Secant(String equation, double a, double b,
                                          double nmax, double epsilon)
    {
        ObservableList<RootRow> data = FXCollections.observableArrayList();

        int n;
        double fa;
        double fb;
        double d = 0;
        double x0 = a;
        double x1 = b;
        double x2 = 0;
        double fx0;
        double fx1;
        double fx2;

        if(equation.equals("A"))
        {
            fa = EquationEvaluator.getInstance().equationA(a);
            fb = EquationEvaluator.getInstance().equationA(b);
        }
        else
        {
            fa = EquationEvaluator.getInstance().equationB(a);
            fb = EquationEvaluator.getInstance().equationB(b);
        }

        fx0 = fa;
        fx1 = fb;

        if(Math.abs(fa) > Math.abs(fb))
        {
            double temp = a;
            a = b;
            b = temp;

            temp = fa;
            fa = fb;
            fb = temp;
        }

        for(n = 1; n < nmax; n++)
        {
            if(n > 1)
            {
                x0 = x1;
                x1 = x2;
                fx0 = fx1;
            }

            if(Math.abs(fa) > Math.abs(fb))
            {
                x0 = a;
                x1 = b;

                double temp = a;
                a = b;
                b = temp;

                temp = fa;
                fa = fb;
                fb = temp;
            }

            d = (b - a) / (fb - fa);
            b = a;
            fb = fa;
            d = d * fa;
            x2 = a - d;

            data.add(new RootRow(n, x0, x1, x2, fx1, fx0, d));

            if(Math.abs(d) < epsilon)
            {
                Main.mainWindowController.setStatus("Status", "Convergence!");
                return data;
            }


            a = a - d;
            if(equation.equals("A"))
                fa = EquationEvaluator.getInstance().equationA(a);
            else
                fa = EquationEvaluator.getInstance().equationB(a);
        }

        return data;
    }
}
