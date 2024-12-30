package service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CatCommand implements ICommand {

    @Override
    public Boolean execute(String[] args) {
        ArrayList<String> files = new ArrayList<>();

        // Process input to handle different quote styles and spaces in paths
        extractFilePaths(args[1], files);

        StringBuilder content = new StringBuilder();

        for (String file : files) {
            String fileContent = "";
            try {
                fileContent = new String(Files.readAllBytes(Paths.get(file)));
            } catch (IOException e) {
                System.err.println("Error reading file: " + file + ". " + e.getMessage());
                return false;
            }
            content.append(fileContent);
        }

        // Print the concatenated file contents
        System.out.print(content.toString());
        return true;
    }

    // Function to extract file paths considering quoted and non-quoted strings
    private static void extractFilePaths(String arg, ArrayList<String> files) {
        // Regular expression to match file paths inside single or double quotes or unquoted
        Pattern pattern = Pattern.compile("\"([^\"]*)\"|'([^']*)'|([^\\s]+)");
        Matcher matcher = pattern.matcher(arg);

        // Match all file paths and add them to the files list
        while (matcher.find()) {
            if (matcher.group(1) != null) {
                // Double-quoted string
                files.add(matcher.group(1));
            } else if (matcher.group(2) != null) {
                // Single-quoted string
                files.add(matcher.group(2));
            } else if (matcher.group(3) != null) {
                // Unquoted string
                files.add(matcher.group(3));
            }
        }
    }
}
//public class CatCommand implements ICommand{
//
//    @Override
//    public Boolean execute(String[] args) {
//        ArrayList<String> files=new ArrayList<>();
//
//        if (args[1].contains("'")) {
//            properFilePaths(args, files,'\'');
//        }
//        else if (args[1].contains("\"")) {
//            properFilePaths(args, files,'"');
//
//        }
//        else {
//            String [] fil = args[1].split(" ");
//            for(String s:fil)
//                files.add(s);
//        }
//
//
//        String content="";
//
//        for(String file:files){
//            String fileContent="";
//            try {
//                fileContent= new String(Files.readAllBytes(Paths.get(file)));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            content+=fileContent;
//        }
//        System.out.print(content);
//        return true;
//    }
//
//    private static void properFilePaths(String[] args, ArrayList<String> files,char delemiter) {
//        LinkedHashSet<Integer> quoteIndices = new LinkedHashSet<>();
//
//        // Find all the indices of single quotes in the string
//        for (int i = 0; i < args[1].length(); i++) {
//            if (args[1].charAt(i) == delemiter) {
//                quoteIndices.add(i);
//            }
//        }
//
//        // Ensure we have pairs of single quotes for proper file paths
//        if (quoteIndices.size() % 2 != 0) {
//            System.out.println("Unmatched single quotes in input");
//        }
//
//        // Iterate through the quote indices two at a time to extract file paths
//        Integer[] quoteArray = quoteIndices.toArray(new Integer[0]);
//        for (int i = 0; i < quoteArray.length; i += 2) {
//            int start = quoteArray[i] + 1; // Skip the first quote
//            int end = quoteArray[i + 1];   // Stop before the second quote
//
//            // Extract the substring between the quotes and add to files
//            String filePath = args[1].substring(start, end).trim();
//            if (!filePath.isEmpty()) {
//                files.add(filePath);
//            }
//        }
//    }
//}
