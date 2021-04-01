package ru.spbstu.reader;

import org.junit.Test;
import ru.spbstu.fastafile.Parser;

import static org.junit.Assert.*;

public class CommandLineFactoryTest {

    @Test
    public void createParserEmptyArguments() {
        String [] args = {} ;
        CommandLineFactory commandLineFactory = new CommandLineFactory(args);
        Parser parser = commandLineFactory.createParser();
        assertNull(parser);
    }

    @Test
    public void createParserLongOption() {
        String [] args = {"--file-path", "path"} ;
        CommandLineFactory commandLineFactory = new CommandLineFactory(args);
        Parser parser = commandLineFactory.createParser();
        assertNotNull(parser);
    }

    @Test
    public void createParserShortOption() {
        String [] args = {"-p", "path"} ;
        CommandLineFactory commandLineFactory = new CommandLineFactory(args);
        Parser parser = commandLineFactory.createParser();
        assertNotNull(parser);
    }
}