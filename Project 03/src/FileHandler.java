import javafx.collections.ObservableList;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class FileHandler
{
    private static FileHandler instance = null;
    private String line;
    private String fileName;
    private FileReader fileReader;
    private BufferedReader bufferedReader;

    public static FileHandler getInstance()
    {
        if(instance == null)
            instance = new FileHandler();
        return instance;
    }

    private FileHandler()
    {
    }

    private void openFile()
    {
        try
        {
            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);
        }
        catch(FileNotFoundException e)
        {
            Main.mainWindowController.setStatus("ERROR",
                    "Unable to open file '" + fileName + "'.");
        }
    }

    private void closeFile()
    {
        try
        {
            bufferedReader.close();
            fileReader.close();
        }
        catch(IOException e)
        {
            Main.mainWindowController.setStatus("ERROR",
                    "Unable to close file '" + fileName + "'.");
        }
    }

    public void readFile(File file, ArrayList<ArrayList<Float>> table)
    {
        this.fileName = file.getAbsolutePath();
        openFile();

        int lineCounter = 0;
        String[] xValues = null;
        String[] yValues = null;
        ArrayList<Float> xs = new ArrayList<>();
        ArrayList<Float> ys = new ArrayList<>();

        try
        {
            while((line = bufferedReader.readLine()) != null)
            {
                if (line.matches("[0-9. ]+"))
                {
                    line = line.trim();

                    if(lineCounter == 0)
                        xValues = line.split("[ ]");
                    else if(lineCounter == 1)
                        yValues = line.split("[ ]");
                }
                lineCounter++;
            }

            for(int i = 0; i < xValues.length; i++)
            {
                xs.add(Float.valueOf(xValues[i]));
                ys.add(Float.valueOf(yValues[i]));
            }

            table.add(xs);
            table.add(ys);
        }
        catch (IOException e)
        {
            Main.mainWindowController.setStatus("ERROR",
                    "Unable to read from file '" + fileName + "'.");
        }
        finally
        {
            closeFile();
        }
    }




//    public void readFile(File file, ObservableList<InterpolationNode> data)
//    {
//        this.fileName = file.getAbsolutePath();
//        openFile();
//
//        int lineCounter = 0;
//        String[] xValues = null;
//        String[] yValues = null;
//
//        try
//        {
//            while((line = bufferedReader.readLine()) != null)
//            {
//                if (line.matches("[0-9. ]+"))
//                {
//                    line = line.trim();
//
//                    if(lineCounter == 0)
//                        xValues = line.split("[ ]");
//                    else if(lineCounter == 1)
//                        yValues = line.split("[ ]");
//                }
//                lineCounter++;
//            }
//
//            for(int i = 0; i < xValues.length; i++)
//            {
////                DynamicNode dynamicNode = new DynamicNode();
////                dynamicNode.setProperty("x", xValues[i]);
////                dynamicNode.setProperty("y", yValues[i]);
////
////                dynamicNode.define("getX", new Callable<Object>()
////                {
////                    @Override
////                    public Object call() throws Exception
////                    {
////                        return null;
////                    }
////                });
//
//                data.add(new InterpolationNode(Float.valueOf(xValues[i]),
//                        Float.valueOf(yValues[i])));
//                data.add(null);
//            }
//        }
//        catch (IOException e)
//        {
//            Main.mainWindowController.setStatus("ERROR",
//                    "Unable to read from file '" + fileName + "'.");
//        }
//        finally
//        {
//            closeFile();
//        }
//    }
}
