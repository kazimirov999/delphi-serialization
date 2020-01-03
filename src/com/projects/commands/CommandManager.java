package com.projects.commands;

import com.projects.externalization.ExternalizationManager;
import com.projects.serialization.SerializationManager;

import java.io.IOException;
import java.util.Scanner;

import static com.projects.commands.Command.*;

public class CommandManager {

    private SerializationManager serializationManager;
    private ExternalizationManager externalizationManager;

    public CommandManager(SerializationManager serializationManager, ExternalizationManager externalizationManager) {
        this.serializationManager = serializationManager;
        this.externalizationManager = externalizationManager;
    }

    public void start(Scanner scanner) {
        System.out.println("Please enter \"1\" to use serialization or \"2\" to use externalization: ");

        try {
            Command command = Command.getCommand(scanner.nextLine());

            if (command.equals(SERIALIZATION)) {
                serializationManager.start();
            } else if (command.equals(EXTERNALIZATION)) {
                externalizationManager.start();
            }
        } catch (IOException | ClassNotFoundException | IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
}