package ru.spbstu.calculator;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import ru.spbstu.fastafile.FastaFile;

import java.io.IOException;

public class SimilarityScoreCalculatorTestInfinity {

    private final String fastaPW = "src/test/resources/PWTest";
    private final String fastaCalculate = "src/test/resources/calculator-test";
    private final double delta = 0.0001;
    private final int sequenceLength = 9;

    private DatasetScore ds;

    @Before
    public void setup() throws IOException {
        FastaFile fastaFile = new FastaFile();
        fastaFile.readFastaFile(fastaCalculate);

        FastaFile fastaFilePW = new FastaFile();
        fastaFilePW.readFastaFile(fastaPW);
        PWCalculator pwCalculator = new PWCalculator();
        PWMatrix matrix = pwCalculator.calculateMatrix(fastaFilePW, CalculationStrategy.builder()
                .sequenceLength(sequenceLength)
                .build());

        SimilarityScoreCalculator ssc = new SimilarityScoreCalculator(sequenceLength, fastaFile);
        ds = ssc.calculateScore(matrix);
    }

    @Test
    public void testDataSize() {
        assertEquals(3, ds.getData().size());
    }

    @Test
    public void testWindowSize() {
        assertEquals(sequenceLength, ds.getWindowSize());
    }

    @Test
    public void testElem0RecordID() {
        assertEquals("id1", ds.getData().get(0).getRecordID());
    }

    @Test
    public void testElem0Position() {
        assertEquals(0, ds.getData().get(0).getPosition());
    }

    @Test
    public void testElem0Score() {
        assertEquals(Double.NEGATIVE_INFINITY, ds.getData().get(0).getScore(), delta);
    }

    @Test
    public void testElem1RecordID() {
        assertEquals("id2", ds.getData().get(1).getRecordID());
    }

    @Test
    public void testElem1Position() {
        assertEquals(0, ds.getData().get(1).getPosition());
    }

    @Test
    public void testElem1Score() {
        assertEquals(Double.NEGATIVE_INFINITY, ds.getData().get(1).getScore(), delta);
    }

    @Test
    public void testElem2RecordID() {
        assertEquals("id2", ds.getData().get(2).getRecordID());
    }

    @Test
    public void testElem2Position() {
        assertEquals(1, ds.getData().get(2).getPosition());
    }

    @Test
    public void testElem2Score() {
        assertEquals(Double.NEGATIVE_INFINITY, ds.getData().get(2).getScore(), delta);
    }
}
