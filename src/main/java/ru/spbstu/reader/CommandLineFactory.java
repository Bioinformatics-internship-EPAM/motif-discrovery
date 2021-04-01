package ru.spbstu.reader;

import org.apache.commons.cli.*;
import ru.spbstu.fastafile.FastaFileParser;
import ru.spbstu.fastafile.Parser;

public class CommandLineFactory implements ParserFactory {
    private CommandLineParser parser;
    private HelpFormatter formatter;
    private CommandLine cmd;
    private Options options;
    private String []args;

    private final static String FILE_PATH_OPTION = "file-path";
    private final static String FILE_PATH_SHORT_OPTION = "p";
    private final static String DESCRIPTION = "Fasta file path";
    private final static String PROJECT = "motif-discovery";

    public CommandLineFactory(String[] args) {
        parser = new DefaultParser();
        formatter = new HelpFormatter();
        options = new Options();
        this.args = args;
    }

    private void configureCommandLineOptions() {
        Option input = new Option(FILE_PATH_SHORT_OPTION, FILE_PATH_OPTION, true, DESCRIPTION);
        input.setRequired(true);
        options.addOption(input);
    }

    private void parseArguments() throws ParseException {
        cmd = parser.parse(options, args);
    }

    private String getFastaFileName() {
        if( cmd.hasOption( FILE_PATH_OPTION ) ) {
            return cmd.getOptionValue(FILE_PATH_OPTION);
        }
        return "";
    }

    public Parser createParser() {
        configureCommandLineOptions();
        try {
            parseArguments();
        } catch (ParseException e) {
            formatter.printHelp(PROJECT, options);
            return null;
        }
        String fastaFileName = getFastaFileName();
        return new FastaFileParser(fastaFileName);
    }
}
