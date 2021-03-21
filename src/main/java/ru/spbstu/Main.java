package ru.spbstu;

import org.apache.commons.cli.*;

public class Main {
    public static void main(String[] args) {
        Options options = new Options();

        Option input = new Option("p", "file-path", true, "Fasta file path");
        input.setRequired(true);
        options.addOption(input);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("motif-discovery", options);

            System.exit(0);
        }

        String fastaFileName = cmd.getOptionValue("file-path");
        System.out.println(fastaFileName);
    }
}
