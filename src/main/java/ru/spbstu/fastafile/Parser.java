package ru.spbstu.fastafile;

import java.io.InputStream;
import java.util.List;

public interface Parser {

    List<FastaRecord> parseData(InputStream inputStream) throws Exception;

}
