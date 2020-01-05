// Name: Deandra Spike-Madden
// Date Worked on: 2019-12-22 -> 2019-12-23


import javax.swing.text.AttributeSet;
import java.util.*;

public class HangMan {
    public static void main(String[] args) {

        // Level of difficulty with three categories
        String[][] easyLevel = {{"apple", "banana", "cherry", "blueberry", "honeydew"}, {"circle", "triangle", "square", "rectangle", "hexagon"}, {"blue", "green", "red", "orange", "yellow"}};
        String[][] mediumLevel = {{"Toronto", "London", "Tokyo", "New York", "Cape Town"}, {"Uber", "Apple", "ubisoft", "Shell", "Electronic Arts"}, {"Frozen", "star Wars", "Fast and Furious", "Avengers", "toy story"}};
        String[][] hardLevel = {{"won", "Euro", "Yen", "Ruble", "Peso"}, {"butterfly", "Panda ant", "Thorn bug", "Devil's Flower Mantis", "Walking Stick"}, {"John Turner", " Joe Clark", "John Abbot", "George Washington", "James Monroe"}};

        //Creating Scanner
        Scanner input = new Scanner(System.in);

        // Declaring variables
        int levelOfDif;
        int category;
        String randWord = "";
        List<String> lettersGuessed = new ArrayList<String>(); //Used to store each guess
        String guess = "";
        int wrong = 0; // store amount of incorrect guesses
        int playAgain = 0;

        while(playAgain !=-2) {

            System.out.println("WELCOME TO HANGMAN!");

            levelOfDif = getDifficulty();
            category = getCategory(levelOfDif);
            System.out.println();
            randWord = (getRandomWord(levelOfDif, category, easyLevel, mediumLevel, hardLevel)).toLowerCase();
            System.out.println();

            String[] filling = new String[randWord.length()]; // array that will be updated with correct guesses


            // Beginning of search for letters
            System.out.println("RULES: You have six chances to guess all the letters.");
            printBlanks(randWord, filling);
            System.out.println();
            System.out.println("LET'S BEGIN!");

            while (wrong != 6) {

                // Declaring and initializing variables
                List<String> list = Arrays.asList(filling); // casting filling to arraylist to see if player won
                char[] slicing = randWord.toCharArray(); // converting the random word in ta list
                String tempStorage = ""; // uses to store guess
                char guessToChar;

                System.out.println();

                printGuesses(filling);

                if (!(list.contains("_"))) { // if array filling contains no underscores then the player has won
                    System.out.println("YOU WIN!");
                    break;
                }

                do { // do while loop to make sure the player enters a single character and not a repeat
                    System.out.print("Enter a letter: ");
                    guess = input.next();
                } while (guess.length() > 1 || !(guess.matches("[a-zA-Z]+")) || lettersGuessed.contains(guess));

                lettersGuessed.add(guess.toLowerCase()); // adds guess to list lettersGuessed and sets it to lowerCase

                if (randWord.contains(guess.toLowerCase())) { // If the random word word contains the guess...
                    tempStorage += guess.toLowerCase();
                    guessToChar = tempStorage.charAt(0); // converts string to char
                    for (int i = 0; i < randWord.length(); i++) {// for loop to check for the occurrences of guess and replace the guess at correct indexes
                        if (slicing[i] == guessToChar) {
                            String guessFinal = Character.toString(guessToChar); // converts Character back to string
                            filling[i] = guessFinal; // replaces underscore with guess
                        }
                    }
                } else { // if guess is not contained in random word, wrong is incremented
                    wrong++;
                }

            }
            if (wrong == 6) { // Informing user of lost
                System.out.println("YOU LOSE. The word was: " + randWord);
                System.out.println();
            }
            do {
                System.out.print("Would you like to play again? (Y:0 OR N:-2): ");
                playAgain = input.nextInt();
            }while(playAgain != 0 && playAgain != -2);
            System.out.println();

            if (playAgain ==0){
                lettersGuessed.clear();
                System.out.println();
            }
        }
        if (playAgain == -2) { // Informing user of lost.
            System.out.println("Thanks for playing `(*>﹏<*)′");
        }

    }



    public static void printBlanks(String randomWord, String[] spaces) {
        /*
        For loop to store underscores and necessary spaces for random word at correct indexes
        For each loop to print out underscores etc.
        returns nothing
         */
        for (int i = 0; i < randomWord.length(); i++) {
            char printingSpaces = randomWord.charAt(i);
            if (printingSpaces == (' ')) {
                spaces[i] = " ";

            }
            else if(printingSpaces == ('\'')){
                spaces[i] = "'";
            }
            else {
                spaces[i] = "_";
            }
        }
        for (String x : spaces) {
            System.out.print(x + " ");

        }

    }

    public static void printGuesses(String[] spaces) {
        /* For loop to print out underscores including guesses
           Returns nothing
         */

        for (String element : spaces) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    public static int getDifficulty() {
        /*
        Asks user for input regarding difficultly level
        do while loop to make sure user enters one of the options
        if else statements to print out difficulty level user chose
        returns difficulty level as int
         */
        int diff = 0;
        Scanner input = new Scanner(System.in);
        System.out.println("Choose your level of difficulty:");
        System.out.println("1.Easy\t\t2.Medium\t\t3.Hard ");
        do {
            System.out.print("Please enter the number associated with the difficulty you desire: ");
            diff = input.nextInt();  // Obtaining level of difficulty
        } while (diff != 1 && diff != 2 && diff != 3);

        if (diff == 1) {
            System.out.println("You chose: Easy");
        } else if (diff == 2) {
            System.out.println("You chose: Medium");
        } else {
            System.out.println("You chose: Hard");
        }

        return diff;
    }

    public static int getCategory(int dif) {
          /*
        Asks user for input regarding category with regards to level
        switch statement for each level of difficulty
            - do while loop to make sure user enters an option regarding category
            - if else statements to inform user what category they chose
          returns category as int
         */
        int categoryTemp;
        int category = 0;
        Scanner input = new Scanner(System.in);
        switch (dif) {
            case 1:
                System.out.println("Choose your category:");
                System.out.println("1.Fruits\t\t2.Shapes\t\t3.Colours ");
                do {
                    System.out.print("Please enter the number associated with the category you desire: ");
                    categoryTemp = input.nextInt();  // Obtaining level of difficulty
                } while (categoryTemp != 1 && categoryTemp != 2 && categoryTemp != 3);

                if (categoryTemp == 1) {
                    System.out.println("You chose: Fruits");
                } else if (categoryTemp == 2) {
                    System.out.println("You chose: Shapes");
                } else {
                    System.out.println("You chose: Colours");
                }
                category = categoryTemp;

                break;
            case 2:
                System.out.println("Choose your category:");
                System.out.println("1.Cities\t\t2.Companies\t\t3.Movies ");
                do {
                    System.out.print("Please enter the number associated with the category you desire: ");
                    categoryTemp = input.nextInt();  // Obtaining level of difficulty
                } while (categoryTemp != 1 && categoryTemp != 2 && categoryTemp != 3);

                if (categoryTemp == 1) {
                    System.out.println("You chose: Cities");
                } else if (categoryTemp == 2) {
                    System.out.println("You chose: Companies");
                } else {
                    System.out.println("You chose: Movies");
                }
                category = categoryTemp;

                break;
            case 3:
                System.out.println("Choose your category:");
                System.out.println("1.Currencies\t\t2.Insects\t\t3.Prime Ministers/ Presidents (Canada and USA) ");
                do {
                    System.out.print("Please enter the number associated with the category you desire: ");
                    categoryTemp = input.nextInt();  // Obtaining level of difficulty
                } while (categoryTemp != 1 && categoryTemp != 2 && categoryTemp != 3);

                if (categoryTemp == 1) {
                    System.out.println("You chose: Currencies");
                } else if (categoryTemp == 2) {
                    System.out.println("You chose: Insects");
                } else {
                    System.out.println("You chose: Prime Ministers/ Presidents (Canada and USA)");
                }
                category = categoryTemp;

                break;
        }
        return category;
    }

    public static String getRandomWord (int dif, int category, String [][] easy, String [][] medium, String[][] hard){
          /*
        switch case based of level of difficulty
            - if user chose category n:
                -random number is generated for column index
                - from there a random word is chosen
            - returns random word as string
         */
        String randWord = "";
        Random rand = new Random();
        int randNum = rand.nextInt(5);
        switch (dif) {
            case 1:
                if (category == 1) {
                    randWord = easy[0][randNum];
                } else if (category == 2) {
                    randWord = easy[1][randNum];
                } else {
                    randWord = easy[2][randNum];
                }
                break;
            case 2:
                if (category == 1) {
                    randWord = medium[0][randNum];
                } else if (category == 2) {
                    randWord = medium[1][randNum];
                } else {
                    randWord = medium[2][randNum];
                }
                break;
            case 3:
                if (category == 1) {
                    randWord = hard[0][randNum];
                } else if (category == 2) {
                    randWord = hard[1][randNum];
                } else {
                    randWord = hard[2][randNum];
                }
                break;
        }
        return randWord;
    }

}

