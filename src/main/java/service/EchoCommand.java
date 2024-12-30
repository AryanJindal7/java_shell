package service;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class EchoCommand implements ICommand{
    @Override
    public Boolean execute(String[] args) {


        // Use a CharacterIterator to parse the input string
        String input = args[1].trim();
        StringBuilder result = new StringBuilder();

        // Initialize the CharacterIterator
        CharacterIterator it = new StringCharacterIterator(input);

        boolean insideSingleQuote = false;
        boolean insideDoubleQuote = false;
        StringBuilder currentWord = new StringBuilder();
        ArrayList<String> words = new ArrayList<>();
        boolean previousCharWasBackslash = false;  // To track escape characters

        for (char c = it.first(); c != CharacterIterator.DONE; c = it.next()) {

            // Handle escaped characters (both single and double quotes)
            if (previousCharWasBackslash) {
                currentWord.append(c); // Append the escaped character
                previousCharWasBackslash = false;
                continue;
            }

            if (c == '\\') {
                previousCharWasBackslash = true;  // The next character is escaped
                continue;
            }

            // Toggle single-quote mode (only if not inside double quotes)
            if (c == '\'' && !insideDoubleQuote) {
                insideSingleQuote = !insideSingleQuote;
                continue;
            }

            // Toggle double-quote mode (only if not inside single quotes)
            if (c == '"' && !insideSingleQuote) {
                insideDoubleQuote = !insideDoubleQuote;
                continue;
            }

            // Word boundary when encountering space outside quotes
            if (c == ' ' && !insideSingleQuote && !insideDoubleQuote) {
                if (currentWord.length() > 0) {
                    words.add(currentWord.toString());
                    currentWord.setLength(0); // Reset the word buffer
                }
            } else {
                // Otherwise, add characters to the current word inside or outside quotes
                currentWord.append(c);
            }
        }

        // Add the last word if present
        if (currentWord.length() > 0) {
            words.add(currentWord.toString());
        }

        // Join the words with a single space and print the result
        result.append(String.join(" ", words));
        System.out.println(result.toString());


//        StringBuilder result=new StringBuilder();
//
//
//        if (args != null && args.length > 1 && args[1] != null) {
//
//            if ((args[1].charAt(0) == '\'') && (args[1].charAt(args[1].length() - 1) == '\'')) {
//                System.out.println(args[1].substring(1, args[1].length() - 1));}
//            else if(args[1].contains("\""))
//                {
//                   String [] targetValues=args[1].split("\"  ");
//                   StringBuffer targetResult=new StringBuffer();
//                   for (String s:targetValues)
//                   {
//                       String modifies=s.replaceAll("\"","");
//                        targetResult.append(modifies+" ");
//                   }
//                    System.out.println(targetResult.toString());
//                }
//            else {
//                for (int i = 0; i < args[1].length()-1; i++) {
//                    if (args[1].charAt(i) == ' ' && args[1].charAt(i+1) == ' ') {
//
//                    } else {
//                        result.append(args[1].charAt(i));
//                    }
//
//                }
//                result.append(args[1].charAt(args[1].length()-1));
//
//                System.out.println(result);
//            }
//        }
        return true;
    }
}
