package com.projects;

import com.projects.externalization.ExternalizableCat;
import com.projects.externalization.ExternalizationManager;
import com.projects.commands.CommandManager;
import com.projects.parsers.FileParser;
import com.projects.serialization.SerializableCat;
import com.projects.serialization.SerializationManager;

import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    private static final String PATH_TO_SOURCE_FILE = "src/com/projects/files/Cats.txt";
    private static final String PATH_TO_RESULT_FILE = "src/com/projects/files/SavedCats.txt";

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        Set<SerializableCat> serializableCats = new HashSet<>();
        Set<ExternalizableCat> externalizableCats = new HashSet<>();

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(PATH_TO_RESULT_FILE));
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(PATH_TO_RESULT_FILE));

        FileParser fileParser = new FileParser(PATH_TO_SOURCE_FILE);

        SerializationManager serializationManager = new SerializationManager(serializableCats, fileParser, objectOutputStream, objectInputStream);
        ExternalizationManager externalizationManager = new ExternalizationManager(externalizableCats, fileParser, objectOutputStream, objectInputStream);
        CommandManager commandManager = new CommandManager(serializationManager, externalizationManager);

        try {
            commandManager.start(scanner);
        } finally {
            objectOutputStream.close();
            objectInputStream.close();
        }

    }
}