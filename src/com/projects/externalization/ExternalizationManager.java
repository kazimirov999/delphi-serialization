package com.projects.externalization;

import com.projects.parsers.FileParser;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Set;

public class ExternalizationManager {

    private Set<ExternalizableCat> externalizableCats;
    private FileParser fileParser;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    public ExternalizationManager(Set<ExternalizableCat> externalizableCats, FileParser fileParser, ObjectOutputStream objectOutputStream, ObjectInputStream objectInputStream) {
        this.externalizableCats = externalizableCats;
        this.fileParser = fileParser;
        this.objectOutputStream = objectOutputStream;
        this.objectInputStream = objectInputStream;
    }

    public void start() throws IOException, ClassNotFoundException {
        System.out.println("Before externalization:");
        parse();

        System.out.println("After externalization:");
        serialize();
    }

    private void parse() throws IOException {
        fileParser.parseExternalizable(externalizableCats);
        printCats();
    }

    private void serialize() throws IOException, ClassNotFoundException {
        save();
        load();
        printCats();
    }

    private void save() throws IOException {
        objectOutputStream.writeObject(externalizableCats);
    }

    private void load() throws IOException, ClassNotFoundException {
        externalizableCats = (HashSet<ExternalizableCat>) objectInputStream.readObject();
    }

    public void printCats() {
        externalizableCats.forEach(System.out::println);
        System.out.println();
    }
}
