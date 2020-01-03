package com.projects.parsers;

import com.projects.parameters.Color;
import com.projects.externalization.ExternalizableCat;
import com.projects.serialization.SerializableCat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Set;

public class FileParser {
    private static final String COMA = ",";
    private static final String SEMICOLON = ";";

    private String sourceFilePath;

    public FileParser(String sourceFilePath) {
        this.sourceFilePath = sourceFilePath;
    }

    private String[] parseLines() throws IOException {
        return Files.readString(Paths.get(sourceFilePath))
                    .split(SEMICOLON);
    }

    private String[] parseParameters(String line) {
        return Arrays.stream(line.split(COMA))
                .map(String::trim)
                .toArray(String[]::new);
    }

    public void parseSerializable(Set<SerializableCat> serializableCats) throws IOException {
        Arrays.asList(parseLines())
                .forEach(line -> serializableCats.add(returnSerializableCat(parseParameters(line))));
    }

    public void parseExternalizable(Set<ExternalizableCat> externalizableCats) throws IOException {
        Arrays.asList(parseLines())
                .forEach(line -> externalizableCats.add(returnExternalizableCat(parseParameters(line))));
    }

    private SerializableCat returnSerializableCat(String[] parameters) {
        return new SerializableCat(
                parameters[0],
                Integer.parseInt(parameters[1]),
                Color.valueOf(parameters[2].toUpperCase()),
                Integer.parseInt(parameters[3]));
    }

    private ExternalizableCat returnExternalizableCat(String[] parameters) {
        return new ExternalizableCat(
                parameters[0],
                Integer.parseInt(parameters[1]),
                Color.valueOf(parameters[2].toUpperCase()),
                Integer.parseInt(parameters[3]));
    }


}