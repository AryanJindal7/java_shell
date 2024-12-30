package utilities;


import java.io.File;

public class ShellUtility {

    private static String userDir=System.getProperty("user.dir");

    private static String dummyUserDir="";

    public static String getUserDir() {
        return userDir;
    }

    public static void setUserDir(String arg) {

//        Path currentPath = Paths.get(userDir);
//
//        if(arg.equalsIgnoreCase(".."))
//        {
//           userDir=currentPath.getParent().toString();
//        } else if (arg.startsWith("./")){
//            Path newPath = currentPath.resolve(arg.substring(2));
//            if (Files.isDirectory(newPath)) {
//                userDir = newPath.toString();
//            } else {
//                System.out.println("Directory does not exist: " + newPath);
//            }
//        }
        userDir=arg;
    }


public static String findExecutablePath(String command) {
    // Get the PATH environment variable and split it into directories
    String pathEnv = System.getenv("PATH");
    String[] paths = pathEnv.split(File.pathSeparator); // File.pathSeparator is ':' on Unix and ';' on Windows

    // Iterate through each directory in the PATH
    for (String path : paths) {
        // Create a File object for the potential executable path
        File file = new File(path, command);

        // Check if the file exists and is executable
        if (file.exists() && file.canExecute()) {
            return file.getAbsolutePath(); // Return the full path if the command is found
        }
    }

    return null; // Return null if the command is not found in any PATH directories
}

    public static String extractParameter(String[] str) {
        String parameter="";
        if (str.length > 2) {
            for (int i = 1; i < str.length; i++) {
                if (i < str.length - 1) {
                    parameter += str[i] + " ";
                } else {
                    parameter += str[i];
                }
            }
        } else if (str.length > 1) {
            parameter = str[1];
        }
        return parameter;
    }
}
