package ru.spbstu;


import ru.spbstu.calculator.*;
import ru.spbstu.fastafile.FastaFile;
import ru.spbstu.fastafile.FastaFileParser;
import ru.spbstu.fastafile.FastaRecord;
import ru.spbstu.fastafile.Parser;
import ru.spbstu.reader.CommandLineParser;
import ru.spbstu.reader.CommandLineReader;
import ru.spbstu.reader.Reader;
import ru.spbstu.reporter.MotifReporter;
import ru.spbstu.recognizer.GibbsMotifRecognizer;

import java.io.InputStream;
import java.util.List;


public class Main {

    private static final int TOP_COUNT = 5;

    public static void main(String[] args) throws Exception {
        Reader commandLineReader = new CommandLineReader(new CommandLineParser(args));
        InputStream data = commandLineReader.readData();
        Parser fastaFileParser = new FastaFileParser();
        List<FastaRecord> fastaRecords = fastaFileParser.parseData(data);
        FastaFile fastaFile = new FastaFile(fastaRecords);
        PWCalculator pwCalculator = new PWCalculator(new CalculationStrategy
                .Builder(Constants.DEFAULT_WINDOW_SIZE).build());
        MotifSet motifSet = new MotifSet(fastaFile, MotifSet.Policy.FIRST, Constants.DEFAULT_WINDOW_SIZE);
        PWMatrix matrix = pwCalculator.calculateMatrix(motifSet);
        SimilarityScoreCalculator smCalculator = new SimilarityScoreCalculator(Constants.DEFAULT_WINDOW_SIZE, fastaFile);
        DatasetScore resultScore = smCalculator.calculateScore(matrix);
        System.out.println(matrix);
        MotifReporter.reportTopResults(resultScore, System.out, TOP_COUNT);

        System.out.println("Matrix with the most scored motifset: ");
        GibbsMotifRecognizer recognizer = new GibbsMotifRecognizer();
        System.out.println(recognizer.recognize(fastaFile));
    }
}
