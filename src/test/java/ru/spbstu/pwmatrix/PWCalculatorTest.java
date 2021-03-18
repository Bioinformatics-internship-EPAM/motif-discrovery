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
        PWMatrix matrix = pwcalculator.calculateMatrix(frequencyOfNucleotides);


        assertEquals(sequenceLength, matrix.getRowByNucleotide('A').getRow().length);
        assertEquals(sequenceLength, matrix.getRowByNucleotide('C').getRow().length);
        assertEquals(sequenceLength, matrix.getRowByNucleotide('G').getRow().length);
        assertEquals(sequenceLength, matrix.getRowByNucleotide('T').getRow().length);

        assertEquals(0.1823215567939546D, matrix.getRowByNucleotide('A').getByIndex(0), delta);
        assertEquals(-0.2231435513142097D, matrix.getRowByNucleotide('C').getByIndex(1), delta);
        assertEquals(1.0296194171811581D, matrix.getRowByNucleotide('G').getByIndex(2), delta);
        assertEquals(Double.NEGATIVE_INFINITY, matrix.getRowByNucleotide('T').getByIndex(3), delta);
    }
}
