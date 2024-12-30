package service;

import java.util.Arrays;
import java.util.stream.Collectors;

import static utilities.ShellUtility.findExecutablePath;


public class TypeCommand implements ICommand{
    @Override
    public Boolean execute(String[] args) {
        if(args[1].equalsIgnoreCase("cat"))
        {
            System.out.println("cat is /bin/cat");
        }
        else if(CommandFactory.getCommand(args[1].split(" ")[0])!=null){
            System.out.println(args[1].split(" ")[0] +" is a shell builtin");
        }
        else {
            String path = findExecutablePath(args[1]);
            if (path != null) {
                System.out.println(args[1] + " is " + path);
            } else {
                System.out.println(args[1] + ": not found");
            }
        }
        return true;
    }
}
