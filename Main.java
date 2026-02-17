import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        //File inFile = new File ("A1input.txt");
        //String inFile = "A1input.txt";
        int[] numbersArray;

        try {
            numbersArray = readFile("A1input.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Welcome to Integer Finder");

        while (running) {
            System.out.println("\n1. Find integer");
            System.out.println("2. Modify integer");
            System.out.println("3. Add to end of array");
            System.out.println("4. Remove integer via index");
            System.out.println("5. Display list");
            System.out.println("6. Exit");
            System.out.print("Enter Your Choice (1-5): ");

            int userChoice; //set user's choice first

            //validate user's input
            try { 
                userChoice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number 1-6.");
                continue; // go back to menu
            }
            switch (userChoice) {
                case 1:
                    System.out.print("Enter number: ");
                    int target = Integer.parseInt(scanner.nextLine());
                    int index = findInteger(numbersArray, target);

                    if (index != -1) {
                        System.out.println("Found at index: " + index);
                    } else 
                        System.out.println("Number not found.");
                    break;

                case 2:
                    try {
                        System.out.print("Enter index: ");
                        int userIndex = Integer.parseInt(scanner.nextLine());

                        System.out.print("Enter new value: ");
                        int newValue = Integer.parseInt(scanner.nextLine());

                        int oldValue = modifyInteger(numbersArray, userIndex, newValue);

                        System.out.println("Old value: " + oldValue);
                        System.out.println("New value: " + newValue);

                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid integer.");//if input is invalid like strings and whatnot
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Invalid index. Please enter an index within the array bounds."); //if index is not in the range of the array
                    }
                    break;

                case 3:
                    try {
                        System.out.print("Enter number to add: ");
                        int userNewValue = Integer.parseInt(scanner.nextLine());

                        numbersArray = addInteger(numbersArray, userNewValue);
                        System.out.println("Value added: " + userNewValue);

                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid integer."); //same as case 2
                    }
                    break;

                case 4:
                    System.out.println("Enter index: ");
                    int userRemove = Integer.parseInt(scanner.nextLine());
                    numbersArray = removeInteger(numbersArray, userRemove);
                    break;
                case 5:
                    System.out.println("\nCurrent List:");
                    for (int i = 0; i < numbersArray.length; i++) {
                        System.out.print(numbersArray[i] + " ");
                    }
                    System.out.println();
                    break;

                case 6:
                    System.out.println("Exiting program | thank you!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice: ");
            }
        }
        scanner.close();
                
    }



    public static int[] readFile(String file) throws FileNotFoundException {
        int count = 0;

        // First pass to count the number of elements to figure out our determined array
        try (Scanner scanner = new Scanner(new File(file))) {
            while (scanner.hasNextInt()) {
                count++;
                scanner.nextInt();
            }
        } 

        int[] arr = new int[count];

        //scan numbers again to add to created array
        try (Scanner scanner = new Scanner(new File(file))) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = scanner.nextInt();
            }
        } 
        return arr;
    }

    //return the index if found else return -1 which then notifies user's value not found
    public static int findInteger(int [] numbersArray, int target) {
        for (int i = 0; i < numbersArray.length; i++) {
            if (target == numbersArray[i]){
                return i;
            }  
        }
        return -1;
    }

    //modify given integer based on user's choice, returning an array of both old and new
    public static int modifyInteger(int[] arr, int index, int newValue) {
        int oldValue = arr[index];
        arr[index] = newValue;
        return  oldValue;
    }

    //create new array first with size increase, copy old array to new one, than add new value to end
    public static int[] addInteger(int[] arr, int newValue) {
        int[] newArr = new int[arr.length + 1]; //new list with +1 size increase 

        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i]; //copy
        }

        newArr[arr.length] = newValue;
        return newArr; //return new list of added value
    }

    //arrays can't shrink like ArrayList
    public static int[] removeInteger(int[] arr, int index) {
        int[] newArr = new int[arr.length - 1]; //create new array but -1 size

        int j = 0;
        for (int i = 0; i < arr.length; i++) { //iterating through og list 
            if (i != index) {
                newArr[j] = arr[i]; //copying to new list with counter
                j++;
            }
        }

        return newArr;
    }
}

