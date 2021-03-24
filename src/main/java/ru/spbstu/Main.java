package ru.spbstu;

import org.apache.commons.cli.*;

public class Main {
    private final static String OPTION = "file-path";
    private final static String SHORT_OPTION = "p";
    private final static String DESCRIPTION = "Fasta file path";
    private final static String PROJECT = "motif-discovery";

    public static void main(String[] args) {
        Options options = new Options();

        Option input = new Option(SHORT_OPTION, OPTION, true, DESCRIPTION);
        input.setRequired(true);
        options.addOption(input);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp(PROJECT, options);

            System.exit(0);
        }

        String fastaFileName = cmd.getOptionValue(OPTION);
        System.out.println(fastaFileName);
    }
}
