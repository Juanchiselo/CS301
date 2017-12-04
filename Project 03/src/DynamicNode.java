import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class DynamicNode
{

    private Map<String, String> properties = new HashMap<>();
    private Map<String, Callable<Object>> callables = new HashMap<>();

    public String getProperty(String key)
    {
        return properties.get(key);
    }

    public void setProperty(String key, String value)
    {
        properties.put(key, value);
    }

    public Object call(String key)
    {
        Callable<Object> callable = callables.get(key);

        try
        {
            if (callable != null)
            {
                return callable.call();
            }
        }
        catch(Exception exception)
        {
//            Main.mainWindowController.setStatus("ERROR",
//                    exception.getMessage());
        }


        return null;
    }

    public void define(String key, Callable<Object> callable)
    {
        callables.put(key, callable);
    }
}