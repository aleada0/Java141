/** 
 * @author Adam Alencastro
 * @since 7/22/2025
 * @version 1.0
 */

import java.util.Scanner;

public class ICAOATranslator {
    public static void main(String[] args){
        //starts by putting you into the menu first
        menu();
    }

/**
 * a menu that asks for input
 */
    public static void menu(){

        // this will move all the above text above the scrollview point. it wont affect anything printed after it.
        System.out.print("\033[H\033[2J"); 

        System.out.println(" ______________________________________________________ ");
        System.out.println("|                   ICAOA translator                   |");
        System.out.println("|______________________________________________________|");
        System.out.println("|     Please select any of the following actions by    |");
        System.out.println("|     typing the corresponding number                  |");
        System.out.println("|                                                      |");
        System.out.println("|               1: Start the program                   |");
        System.out.println("|               0: End the session                     |");
        System.out.println("|______________________________________________________|");
        menuChoice();
    }

/**
 * given from the menu(), typing in any accepted number shown below will do something. otherwise, it'll ask you again.
 */
    public static void menuChoice(){
        Scanner input = new Scanner(System.in);

        // the continuing variable doesn't have a variable yet
        Scanner continuing = new Scanner(System.in);

        System.out.println("Enter your choice: ");
        //gets the first character in the input in case the user tries typing a word, and sets it as the character value instead
        switch (input.next().charAt(0)) {
            case '1' -> startProgram();
            case '0' -> endProgram();
            default -> {
                System.out.print("\033[H\033[2J");
                System.out.println("This is not a valid number. Try again.");
                // the variable asks for an input here since 'continuing' doesnt have one yet. it wont be assigned
                // anywhere, so that you can type in anything and simply press enter to continue.
                continuing.nextLine();
                menu();
            }
        }
        
    }

/** 
 * starts the process of turning every letter into a word
 */ 
    public static void startProgram(){
        System.out.print("\033[H\033[2J"); 
        //starts with a word input
        Scanner input = new Scanner(System.in);
        System.out.println("Type in your word: ");
        String currentWord = input.nextLine();
        System.out.print("\033[H\033[2J");

        // assigns the overall word sentence to 'icaoaSentence', then will select letters to swap out
        chooseLetter(currentWord);

    }

/** 
 * gets letters from the string. it'll check if there are any non-letters first. if not, the method will continue as normally.
 * @param word has its letters checked first, then each of them are turned into a corresponding word and are added to a string
 */
    public static void chooseLetter(String word){
        Scanner continuing = new Scanner(System.in);
        //here, the new sentence is declared for later use
        String newSentence = "";

        // checks for any non-letters and will handle the error gracefully. 
        // the values here are outside the range of uppercase and lowercase letter's ASCII values.
        boolean hasErrors = false;
        for (int i = 0; i < word.length(); i++){
            if (word.charAt(i) < 65 || word.charAt(i) > 122 || word.charAt(i) > 91 &&  word.charAt(i) < 97) {
                hasErrors = true;
            }
        }

        if(!hasErrors){
            for (int i = 0; i < word.length(); i++){
                // gets the character for a letter in the word, repeating the process each time with the next letter
                char letter = word.charAt(i);
                // then converts the chosen letter to a word from the method convTo which is returned and added to newSentence
                newSentence += convTo(letter) + " ";
            }

            System.out.println(newSentence);
            // goes back to the main menu once it's printed out and this choice is done
            System.out.println("enter anything to return to the main menu");
            continuing.nextLine();
            menu();

        }else{
            // only if hasErrors is true, in which case the word has a non-letter somewhere, and you'll go back to re-entering the word
            newSentence = "Invalid input. Enter only letters. Press enter to continue.";
            System.out.println(newSentence);
            continuing.nextLine();
            startProgram();

        }
    }

/** 
 * @param switchLetter the switchletter is set to an int value and chooses which word to swap it out with
 * @return newWord is the word from the letter. it gets returned and added to the sentence in chooseLetter()
 */
    public static String convTo(int switchLetter){
        String newWord = null;
        // checks if the characters decimal values are in range of the letter decimal values, uppercase or lowercase

        // itll decide to subtract 6 more or not if it's lowercase, because in ascii,
        // uppercase z and lowercase a are seperated by 6 integers.

        if (switchLetter >= 65 && switchLetter <= 90){
            switchLetter = (switchLetter-65) % 26;
        } else if (switchLetter >= 97 && switchLetter <= 122){
            switchLetter = (switchLetter-65-6) % 26;
        }

            // decides which word to return depending on the letter character it's using
            switch (switchLetter) {
                case 0 -> newWord = "Alpha";
                case 1 -> newWord = "Bravo";
                case 2 -> newWord = "Charlie";
                case 3 -> newWord = "Delta";
                case 4 -> newWord = "Echo";
                case 5 -> newWord = "Foxtrot";
                case 6 -> newWord = "Golf";
                case 7 -> newWord = "Hotel";
                case 8 -> newWord = "India";
                case 9 -> newWord = "Juliet";
                case 10 -> newWord = "Kilo";
                case 11 -> newWord = "Lima";
                case 12 -> newWord = "Mike";
                case 13 -> newWord = "November";
                case 14 -> newWord = "Oscar";
                case 15 -> newWord = "Papa";
                case 16 -> newWord = "Quebec";
                case 17 -> newWord = "Romeo";
                case 18 -> newWord = "Sierra";
                case 19 -> newWord = "Tango";
                case 20 -> newWord = "Uniform";
                case 21 -> newWord = "Victor";
                case 22 -> newWord = "Whiskey";
                case 23 -> newWord = "X-ray";
                case 24 -> newWord = "Yankee";
                case 25 -> newWord = "Zulu";
                    
            }
        
        // returns the word to the newSentence string where it's added to the sentence
        return newWord;
    }
/** 
 * ending the program
 */
    public static void endProgram(){
        Scanner continuing = new Scanner(System.in);
        System.out.print("\033[H\033[2J"); 
        System.out.println("TERMINATING SESSION");
        continuing.nextLine();
        System.out.print("\033[H\033[2J"); 
    }
}
