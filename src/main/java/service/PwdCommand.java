package service;

import utilities.ShellUtility;


public class PwdCommand implements ICommand{
    @Override
    public Boolean execute(String[] args) {
        System.out.println(ShellUtility.getUserDir());
        return true;
    }
}
