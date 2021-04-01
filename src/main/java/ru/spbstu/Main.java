package ru.spbstu;

import ru.spbstu.fastafile.FastaFile;
import ru.spbstu.fastafile.FastaRecord;
import ru.spbstu.fastafile.Parser;
import ru.spbstu.reader.CommandLineFactory;
import ru.spbstu.reader.ParserFactory;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        ParserFactory commandLineReader = new CommandLineFactory(args);
        Parser fastaFileParser = commandLineReader.createParser();
        if (fastaFileParser != null) {
            List<FastaRecord> fastaRecords = fastaFileParser.parseData();
            FastaFile fastaFile = new FastaFile(fastaRecords);
        }
    }
}
