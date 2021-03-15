package ru.spbstu.calculator;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import ru.spbstu.fastafile.FastaFile;

import java.io.IOException;

public class SimilarityScoreCalculatorTest {

    private FastaFile fastaFile;
    private final double delta = 0.0001;

    @Before
    public void setup() throws IOException {
        fastaFile = new FastaFile();
        final String fileName = "src/test/resources/calculator-test";
        fastaFile.readFastaFile(fileName);
    }

    @Test
    public void testCountScore() {

        double m[][] = {
                {0.1D, 0.1D, 0.1D, 0.1D},
                {0.2D, 0.2D, 0.2D, 0.2D},
                {0.3D, 0.3D, 0.3D, 0.3D},
                {0.4D, 0.4D, 0.4D, 0.4D},
        };
        PWMatrix pwm = new PWMatrix(m);

        int windowSize = 4;
        SimilarityScoreCalculator ssc = new SimilarityScoreCalculator(windowSize, fastaFile);

        DatasetScore ds = ssc.calculateScore(pwm);

        assertEquals(3, ds.getData().size());

        assertEquals("id1", ds.getData().get(0).getRecordID());
        assertEquals(0, ds.getData().get(0).getPosition());
        assertEquals(windowSize, ds.getData().get(0).getLength());
        assertEquals(1.0D, ds.getData().get(0).getScore(), delta);

        assertEquals("id1", ds.getData().get(1).getRecordID());
        assertEquals(1, ds.getData().get(1).getPosition());
        assertEquals(windowSize, ds.getData().get(1).getLength());
        assertEquals(1.0D, ds.getData().get(1).getScore(), delta);

        assertEquals("id2", ds.getData().get(2).getRecordID());
        assertEquals(0, ds.getData().get(2).getPosition());
        assertEquals(windowSize, ds.getData().get(2).getLength());
        assertEquals(0.4D, ds.getData().get(2).getScore(), delta);
    }
}
