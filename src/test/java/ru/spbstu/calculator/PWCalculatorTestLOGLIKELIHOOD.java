package ru.spbstu.calculator;

import org.junit.Before;
import org.junit.Test;
import ru.spbstu.fastafile.FastaFile;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class PWCalculatorTestLOGLIKELIHOOD {
    private final String fileName = "src/test/resources/PWTest";
    private final double delta = 0.00000000001;
    private int sequenceLength = 9;

    private PWMatrix matrix;

    @Before
    public void setup() throws IOException {
        FastaFile fastaFile = new FastaFile();
        fastaFile.readFastaFile(fileName);

        PWCalculator pwCalculator = new PWCalculator();
        matrix = pwCalculator.calculateMatrix(fastaFile, CalculationStrategy.builder()
                .sequenceLength(sequenceLength)
                .build());
    }

    @Test
    public void testLengthA() {
        assertEquals(sequenceLength, matrix.getRowByNucleotide('A').getRow().length);
    }

    @Test
    public void testLengthC() {
        assertEquals(sequenceLength, matrix.getRowByNucleotide('C').getRow().length);
    }

    @Test
    public void testLengthT() {
        assertEquals(sequenceLength, matrix.getRowByNucleotide('T').getRow().length);
    }

    @Test
    public void testLengthG() {
        assertEquals(sequenceLength, matrix.getRowByNucleotide('G').getRow().length);
    }

    @Test
    public void testElemA0() {
        assertEquals(0.1823215567939546D, matrix.getRowByNucleotide('A').getByIndex(0), delta);
    }

    @Test
    public void testElemC1() {
        assertEquals(-0.2231435513142097D, matrix.getRowByNucleotide('C').getByIndex(1), delta);
    }

    @Test
    public void testElemG2() {
        assertEquals(1.0296194171811581D, matrix.getRowByNucleotide('G').getByIndex(2), delta);
    }

    @Test
    public void testElemT3() {
        assertEquals(Double.NEGATIVE_INFINITY, matrix.getRowByNucleotide('T').getByIndex(3), delta);
    }
}
