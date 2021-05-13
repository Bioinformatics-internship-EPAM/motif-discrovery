package ru.spbstu.reader;

import org.junit.Test;
import ru.spbstu.fastafile.Parser;

import static org.junit.Assert.*;

public class CommandLineParserTest {

    @Test
    public void createParserEmptyArguments() {
        String [] args = {} ;
        CommandLineParser commandLineParser = new CommandLineParser(args);
        String file = commandLineParser.getFastaFileNameFromCmd();
        assertEquals("src/main/resources/PWMSample", file);
    }

    @Test
    public void createParserLongOption() {
        String [] args = {"--file-path", "path"} ;
        CommandLineParser commandLineParser = new CommandLineParser(args);
        String file = commandLineParser.getFastaFileNameFromCmd();
        assertEquals("path", file);
    }

    @Test
    public void createParserShortOption() {
        String [] args = {"-p", "path"} ;
        CommandLineParser commandLineParser = new CommandLineParser(args);
        String file = commandLineParser.getFastaFileNameFromCmd();
        assertEquals("path", file);
    }
}