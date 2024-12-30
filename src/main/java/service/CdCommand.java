package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utilities.ShellUtility;

import java.nio.file.Files;
import java.nio.file.Path;


public class CdCommand implements ICommand {

    Logger logger= LoggerFactory.getLogger(CdCommand.class);
    public Boolean execute(String[] args) {

        Path newPath = Path.of(args[1]);

        // Check if the path is relative or absolute
        if (!newPath.isAbsolute()) {
            newPath = Path.of(ShellUtility.getUserDir()).resolve(newPath).normalize();
            logger.info(newPath.toString());
        }

        if (Files.isDirectory(newPath)) {
            ShellUtility.setUserDir(newPath.toString());
            logger.info(newPath.toString());
        }
        else if(newPath.toString().contains("~"))
        {
           newPath = Path.of(newPath.toString().replace(newPath.toString(), System.getenv("HOME")));
            ShellUtility.setUserDir(String.valueOf(newPath));
        }
        else {
            System.out.println(args[0]+": "+args[1] +": No such file or directory");
        }
        return true;
    }
}





