import service.CommandFactory;
import service.ICommand;

import java.util.Scanner;

import static utilities.ShellUtility.extractParameter;
import static utilities.ShellUtility.findExecutablePath;

public class Main {
    public static void main(String[] args) throws Exception {

        Boolean exitBoolean = true;
        while (exitBoolean) {
            System.out.print("$ ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            String[] str = input.trim().split(" ");
            String parameter = extractParameter(str);
            ICommand command = CommandFactory.getCommand(str[0]);
            if (command != null) {
                exitBoolean = command.execute(new String[]{str[0], parameter});
            } else {
                String path = findExecutablePath(str[0]);
                if (path == null) {
                    System.out.printf("%s: command not found%n", str[0]);
                } else {
                    String fullPath = path + input.substring(str[0].length());
                    Process p = Runtime.getRuntime().exec(fullPath.split(" "));
                    p.getInputStream().transferTo(System.out);
                }
            }
        }
    }
}
