package com.projects.serialization;

import com.projects.parsers.FileParser;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Set;

public class SerializationManager {

    private Set<SerializableCat> serializableCats;
    private FileParser fileParser;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    public SerializationManager(Set<SerializableCat> serializableCats, FileParser fileParser, ObjectOutputStream objectOutputStream, ObjectInputStream objectInputStream) {
        this.serializableCats = serializableCats;
        this.fileParser = fileParser;
        this.objectOutputStream = objectOutputStream;
        this.objectInputStream = objectInputStream;
    }

    public void start() throws IOException, ClassNotFoundException {
        System.out.println("Before serialization:");
        parse();

        System.out.println("After serialization:");
        serialize();
    }

    private void parse() throws IOException {
        fileParser.parseSerializable(serializableCats);
        printCats();
    }

    private void serialize() throws IOException, ClassNotFoundException {
        save();
        load();
        printCats();
    }

    private void save() throws IOException {
        objectOutputStream.writeObject(serializableCats);
    }

    private void load() throws IOException, ClassNotFoundException {
        serializableCats = (HashSet<SerializableCat>) objectInputStream.readObject();
    }

    public void printCats() {
        serializableCats.forEach(System.out::println);
        System.out.println();
    }
}
