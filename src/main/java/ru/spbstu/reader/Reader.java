package ru.spbstu.reader;

import java.io.InputStream;

public interface Reader {
    InputStream readData() throws Exception;
}
