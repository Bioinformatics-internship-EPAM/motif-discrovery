package ru.spbstu.pwmatrix;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import ru.spbstu.fastafile.FastaFile;

import java.io.IOException;

public class PWCalculatorTest {
    private FastaFile fastaFile;
    private final double delta = 0.00000000001;

    @Before
    public void setup() throws IOException {
        fastaFile = new FastaFile();
        String fileName = "src/test/resources/PWTest";
        fastaFile.readFastaFile(fileName);
    }

    @Test
    public void testPositionWeightMatrix() {
        int sequenceLength = 9;
        double frequencyOfNucleotides = 0.25;
        PWCalculator pwcalculator = new PWCalculator(fastaFile, sequenceLength);
        PWData data = pwcalculator.calculateMatrix(frequencyOfNucleotides);

        assertEquals(1, data.getListMatrix().size());

        PWMatrix matrix = data.getMatrixByIndex(0);

        assertEquals(0.1823215567939546, matrix.getRowByNucleotide('A').getByIndex(0), delta);
        assertEquals(-0.2231435513142097, matrix.getRowByNucleotide('C').getByIndex(1), delta);
        assertEquals(1.0296194171811581, matrix.getRowByNucleotide('G').getByIndex(2), delta);
        assertEquals(0.8754687373538999, matrix.getRowByNucleotide('T').getByIndex(8), delta);
    }
}
