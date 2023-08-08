import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        File newFile = new File("millionLengthArray.txt");
        PrintStream fout = null;

        try {
            newFile.createNewFile();
            fout = new PrintStream(newFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int [] arr = new int[1000000];
        Random intNum = new Random();
        for (int i = 0; i <arr.length; i++)
            arr[i] = intNum.nextInt(40000);

        Arrays.sort(arr);

        for (int i = 0; i < 1000000; i++)
            fout.println(arr[i]);

        fout.close();
    }

}
