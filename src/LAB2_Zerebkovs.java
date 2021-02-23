import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class LAB2_Zerebkovs {


    public static void main(String[] args) {
        Utils utils = new Utils();
        Boolean closeProgram = false;
        long startTime = 0;
        long endTime = 0;

        int[] unsortedArr = utils.readArray("source1.txt");
        int arraySize = 0;
        Scanner userChoice = new Scanner(System.in);

        if (unsortedArr == null) {
            Boolean isInputCorrect = false;

            //Ask user Random array length
            System.out.println("source.txt NOT FOUND");
            do {
                System.out.print("Array size [0...100000] : ");
                if (userChoice.hasNextInt()) {
                    arraySize = userChoice.nextInt();
                    if (arraySize > 0 && arraySize <= 100000) {
                        isInputCorrect = true;
                        unsortedArr = utils.randomArray(arraySize, 100000, -100000);

                    } else {
                        System.out.println("Input is negative or exceed 100000");
                    }
                } else {
                    userChoice.nextLine();
                    System.out.println("Enter a valid Integer value");
                }
            } while (!isInputCorrect);
        }

        utils.showArray(unsortedArr);


        do {
            System.out.println("1: Sort Array Using Bubble Sort algorithm");
            System.out.println("2: Sort Array Using Sort Insertion algorithm");
            System.out.println("3: Sort Array Using Sort Selection algorithm");
            System.out.println("4: Sort Array Using Improved Bubble Sort algorithm");

            System.out.println("5: Exit program");
            System.out.print("Your choice ? =");
            int[] arrayCopy = Arrays.copyOf(unsortedArr, unsortedArr.length);

            if (userChoice.hasNextInt()) {
                switch (userChoice.nextInt()) {
                    case 1:
                        startTime = System.nanoTime();
                        bubbleSort(arrayCopy);
                        endTime = System.nanoTime();
                        break;
                    case 2:
                        startTime = System.nanoTime();
                        sortInsertion(arrayCopy);
                        endTime = System.nanoTime();
                        break;
                    case 3:
                        startTime = System.nanoTime();
                        sortSelection(arrayCopy);
                        endTime = System.nanoTime();
                        break;
                    case 4:
                        startTime = System.nanoTime();
                        improvedBubbleSort(arrayCopy);
                        endTime = System.nanoTime();
                        break;
                    case 5:
                    default:
                        closeProgram = true;
                        userChoice.close();
                        break;

                }

            }
            if (!closeProgram) {
                utils.writeOnFile(arrayCopy, "result");
                System.out.println("Printing sorted array");
                utils.showArray(arrayCopy);

                if (utils.isAscending(arrayCopy)) {
                    System.out.println("Array is sorted");
                } else {
                    System.out.println("Array is unsorted");
                }
                System.out.println("Sorting Time = " + (endTime - startTime) / 1000000 + " milliseconds");
            }

        } while (!closeProgram);

    }


    static int[] bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1; j++) {

                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);

                }
            }
        }
        return arr;
    }

    static int[] sortInsertion(int[] arr) {
        int key;

        for (int i = 1; i < arr.length; i++) {
            key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }

        return arr;
    }

    static int[] sortSelection(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;

                }

            }
            swap(arr, minIndex, i);
        }

        return arr;
    }

    static int[] improvedBubbleSort(int[] arr) {
        boolean wasSwapped;

        for (int i = 0; i < arr.length-1; i++) {
            wasSwapped = false;
            for (int j = 0; j < arr.length-i-1; j++) {

                if (arr[j] > arr[j + 1]) {

                    swap(arr, j, j + 1);
                    wasSwapped = true;

                }
            }
            if (wasSwapped == false) {
                break;
            }
        }
        return arr;
    }

    static void swap(int[] arr, int position1, int position2) {

        int temp = arr[position1];
        arr[position1] = arr[position2];
        arr[position2] = temp;

    }


}

