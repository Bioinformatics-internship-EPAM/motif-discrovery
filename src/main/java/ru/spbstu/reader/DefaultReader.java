package ru.spbstu.reader;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DefaultReader implements Reader{

    private final String filePath;

    public DefaultReader(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public InputStream readData() throws Exception {
        Path path = Paths.get(filePath);
        return Files.newInputStream(path);
    }
}
