package ru.spbstu.calculator;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import ru.spbstu.fastafile.FastaFile;

import java.io.IOException;

public class SimilarityScoreCalculatorTest {

    private FastaFile fastaFile;
    private final String fastaPW = "src/test/resources/PWTest";
    private final String fastaCalculate = "src/test/resources/calculator-test";
    private PWMatrix matrix;
    private final double delta = 0.0001;
    private int sequenceLength = 9;

    @Before
    public void setup() throws IOException {
        fastaFile = new FastaFile();
        fastaFile.readFastaFile(fastaCalculate);
    }

    @Test
    public void testCountScore() throws IOException{
        FastaFile fastaFilePW = new FastaFile();
        fastaFilePW.readFastaFile(fastaPW);
        PWCalculator pwCalculator = new PWCalculator();
        matrix = pwCalculator.calculateMatrix(fastaFilePW, new CalculationStrategy.Builder()
                .sequenceLength(sequenceLength)
                .calculationType(Constants.calculationMethod.RELATIVE)
                .build());

        SimilarityScoreCalculator ssc = new SimilarityScoreCalculator(sequenceLength, fastaFile);
        DatasetScore ds = ssc.calculateScore(matrix);

        assertEquals(3, ds.getData().size());
        assertEquals(sequenceLength, ds.getWindowSize());

        assertEquals("id1", ds.getData().get(0).getRecordID());
        assertEquals(0, ds.getData().get(0).getPosition());
        assertEquals(2.5D, ds.getData().get(0).getScore(), delta);

        assertEquals("id2", ds.getData().get(1).getRecordID());
        assertEquals(0, ds.getData().get(1).getPosition());
        assertEquals(2.6D, ds.getData().get(1).getScore(), delta);

        assertEquals("id2", ds.getData().get(2).getRecordID());
        assertEquals(1, ds.getData().get(2).getPosition());
        assertEquals(2.6D, ds.getData().get(2).getScore(), delta);
    }

    @Test
    public void testInfinity() throws IOException {
        FastaFile fastaFilePW = new FastaFile();
        fastaFilePW.readFastaFile(fastaPW);
        PWCalculator pwCalculator = new PWCalculator();
        matrix = pwCalculator.calculateMatrix(fastaFilePW, new CalculationStrategy.Builder()
                .sequenceLength(sequenceLength)
                .build());

        SimilarityScoreCalculator ssc = new SimilarityScoreCalculator(sequenceLength, fastaFile);
        DatasetScore ds = ssc.calculateScore(matrix);

        assertEquals(3, ds.getData().size());
        assertEquals(sequenceLength, ds.getWindowSize());

        assertEquals("id1", ds.getData().get(0).getRecordID());
        assertEquals(0, ds.getData().get(0).getPosition());
        assertEquals(Double.NEGATIVE_INFINITY, ds.getData().get(0).getScore(), delta);

        assertEquals("id2", ds.getData().get(1).getRecordID());
        assertEquals(0, ds.getData().get(1).getPosition());
        assertEquals(Double.NEGATIVE_INFINITY, ds.getData().get(1).getScore(), delta);

        assertEquals("id2", ds.getData().get(2).getRecordID());
        assertEquals(1, ds.getData().get(2).getPosition());
        assertEquals(Double.NEGATIVE_INFINITY, ds.getData().get(2).getScore(), delta);
    }
}
