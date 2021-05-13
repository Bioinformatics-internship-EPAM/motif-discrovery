package ru.spbstu.reader;

import java.io.InputStream;

public class CommandLineReader implements Reader{

    private final CommandLineParser cmd;

    public CommandLineReader(CommandLineParser cmd) {
        this.cmd = cmd;
    }

    @Override
    public InputStream readData() throws Exception {
        String fastaFileName = cmd.getFastaFileNameFromCmd();
        return new DefaultReader(fastaFileName).readData();
    }
}
