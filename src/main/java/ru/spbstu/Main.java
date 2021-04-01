package ru.spbstu;

import ru.spbstu.fastafile.Parser;
import ru.spbstu.reader.CommandLineFactory;
import ru.spbstu.reader.ParserFactory;

public class Main {
    public static void main(String[] args) throws Exception {
        ParserFactory commandLineReader = new CommandLineFactory(args);
        Parser fastaFileParser = commandLineReader.createParser();
        fastaFileParser.parseData();
    }
}
