package ru.spbstu.fastafile;

import java.util.List;

public interface Parser {

    List<FastaRecord> parseData() throws Exception;

}
