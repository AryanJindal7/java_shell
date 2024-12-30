package service;

public class ExitCommand implements ICommand{
    @Override
    public Boolean execute(String[] args) {
        return false;
    }
}
