package ru.spbstu.calculator;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import ru.spbstu.fastafile.FastaFile;
import ru.spbstu.fastafile.FastaFileParser;
import ru.spbstu.fastafile.FastaRecord;
import ru.spbstu.fastafile.Parser;

import java.util.List;

public class SimilarityScoreCalculatorTest {

    private FastaFile fastaFile;
    private final double delta = 0.0001;

    @Before
    public void setup() throws Exception {
        final String fileName = "src/test/resources/calculator-test";
        Parser fastaFileParser = new FastaFileParser(fileName);
        List<FastaRecord> fastaRecords = fastaFileParser.parseData();
        fastaFile = new FastaFile(fastaRecords);
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
        assertEquals(windowSize, ds.getWindowSize());

        assertEquals("id1", ds.getData().get(0).getRecordID());
        assertEquals(0, ds.getData().get(0).getPosition());
        assertEquals(1.0D, ds.getData().get(0).getScore(), delta);

        assertEquals("id1", ds.getData().get(1).getRecordID());
        assertEquals(1, ds.getData().get(1).getPosition());
        assertEquals(1.0D, ds.getData().get(1).getScore(), delta);

        assertEquals("id2", ds.getData().get(2).getRecordID());
        assertEquals(0, ds.getData().get(2).getPosition());
        assertEquals(0.4D, ds.getData().get(2).getScore(), delta);
    }
}
