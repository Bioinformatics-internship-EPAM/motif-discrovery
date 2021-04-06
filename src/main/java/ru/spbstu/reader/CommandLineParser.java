package ru.spbstu.reader;

import org.apache.commons.cli.*;

public class CommandLineParser {
    private org.apache.commons.cli.CommandLineParser parser;
    private HelpFormatter formatter;
    private CommandLine cmd;
    private Options options;
    private String []args;

    private final static String FILE_PATH_OPTION = "file-path";
    private final static String FILE_PATH_SHORT_OPTION = "p";
    private final static String PROJECT = "motif-discovery";
    private static final String DEFAULT_FASTA_FILENAME = "src/main/resources/PWMSample";
    private final static String DESCRIPTION = "Fasta file path, default value: " + DEFAULT_FASTA_FILENAME;

    public CommandLineParser(String[] args) {
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

    private String getFastaFileFromOptions() {
        if( cmd.hasOption( FILE_PATH_OPTION ) ) {
            return cmd.getOptionValue(FILE_PATH_OPTION);
        }
        return DEFAULT_FASTA_FILENAME;
    }

    public String getFastaFileNameFromCmd() {
        configureCommandLineOptions();
        try {
            parseArguments();
        } catch (ParseException e) {
            formatter.printHelp(PROJECT, options);
            return DEFAULT_FASTA_FILENAME;
        }
        return getFastaFileFromOptions();
    }
}
