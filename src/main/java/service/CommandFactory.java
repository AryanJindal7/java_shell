package service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CommandFactory {
    private static final Map<String, ICommand> commandMap=new HashMap<>();

    static {
        commandMap.put("echo",new EchoCommand());
        commandMap.put("exit",new ExitCommand());
        commandMap.put("type",new TypeCommand());
        commandMap.put("pwd",new PwdCommand());
        commandMap.put("cd", new CdCommand());
        commandMap.put("cat" , new CatCommand());
    }

    public static ICommand getCommand(String command){
        return commandMap.getOrDefault(command,null);
    }

}
