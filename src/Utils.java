import java.io.*;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Utils {


   public int [] readArray (String path) {
        try {
            Scanner file = new Scanner( new File(path) );
            int[] array = new int[10000];
            int i = 0;
            while( file.hasNextInt() ) array[i++] = file.nextInt();
            file.close();
            return Arrays.copyOf(array, i);
        }
        catch (FileNotFoundException e) {
            return null;
        }

    }

    public void showArray( int[] array ) {
        //Small improvments

        int length = array.length;
       Boolean exceed = false;
        if(array.length >=400){
            length = 150;
            exceed = true;

        }

        for(int i = 0; i < length; i++) {

            System.out.format("%7d ", array[i]);
            if (i % 10 == 9) System.out.println();
        }
        if(exceed){
            System.out.format("%38s","...");
            System.out.println();
            for(int i = array.length - length; i<array.length;i++){
                System.out.format("%7d ", array[i]);
                if (i % 10 == 9) System.out.println();
            }
        }

        if (array.length % 10 != 0) System.out.println();
    }

   public boolean isAscending(int array[]){

        for(int i=0;i<array.length-1;i++){

            if(array[i]>array[i+1]){
                return  false;

            }
        }
        return true;
    }

    public int[] randomArray(int arraySize,int max,int min){

        int[] array = new int[arraySize];
        Random randomNumber = new Random();

        for(int i = 0;i<array.length;i++){
            array[i]= randomNumber.nextInt((max - min) + 1) + min;
        }

        return array;
    }

    public void writeOnFile(int [] array,String fileName){
        try{
            PrintWriter file = new PrintWriter(new FileWriter(fileName));
            for(int i = 0 ; i <array.length;i++){
                file.println(array[i]);

            }
            file.close();
        }catch (IOException e){

        }

    }

}
