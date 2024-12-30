import java.util.HashMap;
import java.util.Map;

public enum Commands {
    ECHO("echo"),
    EXIT("exit 0"),
    PWD("pwd"),
    CAT("cat");

    private final String command;
    private static final Map<String, Commands> commandMap = new HashMap<>();

    Commands(String command) {
        this.command = command;
    }
    static {
        for (Commands cmd : Commands.values()) {
            commandMap.put(cmd.command, cmd);
        }
    }
    public String getCommand() {
        return command;
    }

    public static boolean isValidCommand(String inputCommand) {
        return commandMap.containsKey(inputCommand);
    }

    public static Commands getCommand(String inputCommand) {
        return commandMap.get(inputCommand);
    }
}
