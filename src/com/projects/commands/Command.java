package com.projects.commands;

public enum Command {
    SERIALIZATION("1"),
    EXTERNALIZATION("2");

    private final String commandNumber;

    Command(String commandNumber) {
        this.commandNumber = commandNumber;
    }

    public static Command getCommand(String commandNumber) {
        for(Command command: Command.values()) {
            if(command.getCommandNumber().equals(commandNumber)) {
                return command;
            }
        }
        throw new IllegalArgumentException("Incorrect command number entered: " + commandNumber + "!");
    }

    public String getCommandNumber() {
        return commandNumber;
    }
}
