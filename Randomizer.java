import java.util.Arrays;
import java.util.Scanner;
import java.util.HashSet;
import java.util.ArrayList;

public class Randomizer
{
    // we use an array list to allow the size to increase / decrease, and a scanner for user input
    private ArrayList<String> nameList = new ArrayList<String>();
    final static Scanner scanner = new Scanner(System.in);

    /* Takes input from user and returns it */
    private String scanForInput(String message)
    {
        System.out.println(message);
        String input = scanner.nextLine();
        return input;
    }


    private void addMultipleNames()
    {
        // reading in the names into a single string
        String msg = "Please enter the names seperated by a comma. i.e. Jane Doe,"
                +" John Doe, followed by the 'Enter' key.";
        String names = scanForInput(msg);

        // splitting the names into an array and assigning them to tnameList
        String[] namesArray = names.split(",");
        Arrays.stream(namesArray).forEach(str -> nameList.add(str.trim()));

		/* TODO: save the names to a file and read from the file if it exists
		 if not create it and populate it with input */
    }

    private void addName()
    {
        // reading the name into our nameList
        String msg = "Please enter your the name followed by the 'Enter' key.";
        String name = scanForInput(msg).trim();
        nameList.add(name);

		/* TODO: save the names to a file if it exists */
    }

    private void removeName()
    {
        String msg = "Please enter your the name followed by the 'Enter' key.";
        String name = scanForInput(msg).trim();
        int indexOfName = nameList.indexOf(name);
        if(indexOfName != -1) {
            boolean result = nameList.remove(name);
            System.out.printf("Name %s succesfully removed from the list\n", name);
            return;

        }
        System.out.printf("Name %s is not in the list", name);

		/* TODO: remove the name from the file if it exists */
    }

    private void removeMultipleNames()
    {
        // retrieving the names and placing them in an array
        String msg = "Please enter your the names seperated by a comma. i.e. "
                + "Jane Doe, John Doe, followed by the 'Enter' key.";
        String names = scanForInput(msg);
        String[] namesArray = names.split(",");
        // trimming the whitespaces from names
        namesArray = Arrays.stream(namesArray).map(String::trim).toArray(String[]::new);
        // retrieve the indices for the given names, and a HashSet to check if names were removed
        int[] indexArray = Arrays.stream(namesArray).map(name -> nameList.indexOf(name)).mapToInt(Integer::intValue).toArray();
        HashSet<String> set = new HashSet<String>();
        Arrays.stream(indexArray)
                .filter(idx -> idx != -1)
                .forEach(idx -> {
                    set.add(nameList.get(idx));
                    nameList.remove(idx);
                });
        // printing the names of the names that were removed and those that were unsuccesful
        String succesful = "";
        String unsuccesful = "";
        for(String name : namesArray)
        {
            if(set.add(name))
                unsuccesful = unsuccesful.concat(name.concat(", "));
            else
                succesful = succesful.concat(name.concat(", "));
        }

        if(!succesful.trim().isEmpty())
            System.out.printf("Succesfully removed the following name(s): %s\n", succesful);
        if(!unsuccesful.trim().isEmpty())
            System.out.printf("Error occured when removing the following name(s): %s\n", unsuccesful);
    }

    private void printList()
    {
        for(String name : nameList)
            System.out.printf("%s, ", name);
        System.out.print("\n");
    }

    private void printRandomName()
    {
    	// TODO
    }
    /* This function starts the program */
    public static void Start()
    {
        int fibOf = 0;
        int usersChoice = 0;
        long startTime = 0;
        long endTime = 0;
        long duration = 0;
        Randomizer randomizer = new Randomizer();

        while(true)
        {
            System.out.println("\nPlease choose an option\n1 - Add multiple names\n2 - Add one name\n3 - Remove multiple names\n4- Remove one name\n5 - Print list\n6 - Exit\n");
            usersChoice = scanner.nextInt();
            scanner.nextLine();
            switch(usersChoice)
            {
                case 1:
                    randomizer.addMultipleNames();
                    break;
                case 2:
                    randomizer.addName();
                    break;
                case 3:
                    randomizer.removeMultipleNames();
                    break;
                case 4:
                    randomizer.removeName();
                    break;
                case 5:
                    randomizer.printList();
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please give a valid input");
                    break;
            }
        }
    }
    public static void main(String[]args)
    {
        Randomizer rand = new Randomizer();
        rand.Start();
    }
}
