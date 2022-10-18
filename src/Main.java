import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.nio.charset.StandardCharsets;

public class Main {
    public static class MyDouble {
        public double value = 0.0;
    }

    private static List<Main.MyDouble> getListOfMyDoubleFromStream(
            InputStream inputStream) {
        List<Main.MyDouble> result = new ArrayList<Main.MyDouble>();
        try (Scanner rowScanner = new Scanner(inputStream)) {
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext()) {
                String nextString = rowScanner.next();
                MyDouble nextDouble = new MyDouble();
                nextDouble.value = Double.parseDouble(nextString);
                result.add(nextDouble);
            }
        }
        return result;
    }

    public static void test()
    {
        File file = new File("temp.txt");

        try (InputStream in = new FileInputStream(file))
        {
            int content;
            while ((content = in.read()) != -1) {
                System.out.print((char)content);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        System.out.println("Running...");

        //test();

        double average = 0.0;
        double sum = 0.0;
        int numberOfInputNumbers = 0;

        File inFile = new File("temp.txt");;
        try (InputStream inStream = new FileInputStream(inFile)) {
            List<Main.MyDouble> list = getListOfMyDoubleFromStream(
                    inStream);
            for(int j = 0; j < list.size(); j++) {
                System.out.println(list.get(j).value);
                double currentValue = list.get(j).value;
                numberOfInputNumbers += 1;
                sum += currentValue;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(0 < numberOfInputNumbers) {
            average = sum / numberOfInputNumbers;
        }
        System.out.println("Average of inputs is " + average);
        System.out.println("Done.");
    }
}