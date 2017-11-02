package Project01;

import java.io.*;

public class FileHandler
{
    private static FileHandler instance = null;
    private String line;
    private String filePath;
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
        try {
            fileReader = new FileReader(filePath);
            bufferedReader = new BufferedReader(fileReader);
        } catch(FileNotFoundException e) {
            System.err.println("ERROR: Unable to open file '" + filePath + "'.");
        }
    }

    private void closeFile()
    {
        try {
            bufferedReader.close();
            fileReader.close();
        } catch(IOException e) {
            System.err.println("ERROR: Unable to close file '" + filePath + "'.");
        }
    }

   
    public void readFile(float[][] coefficients, float[] rightHandSide, String filename)
    {
        int equationNumber = 0;

        filePath = System.getProperty("user.dir")
                + "\\src\\Project01\\Resources\\" + filename + ".txt";
        
        openFile();
        try
        {
            while((line = bufferedReader.readLine()) != null)
            {
                //System.out.println(line);
                
                String[] numbers = line.split(" ");

                for(int i = 0; i < numbers.length; i++)
                {
                    if(i < numbers.length - 1)
                        coefficients[equationNumber][i] = Float.parseFloat(numbers[i]);
                    if(i == numbers.length - 1)
                        rightHandSide[equationNumber] = Float.parseFloat(numbers[i]);
                }

                equationNumber++;
            }
                
        } catch (IOException e) {
            System.err.println("ERROR: Unable to read from file '" + filePath + "'.");
        } finally {
            closeFile();
        }
    }
}