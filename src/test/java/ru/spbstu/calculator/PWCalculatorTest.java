package ru.spbstu.calculator;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;

import ru.spbstu.fastafile.FastaFile;
import ru.spbstu.calculator.Constants.calculationMethod;

public class PWCalculatorTest {
    private FastaFile fastaFile;
    private final String fileName = "src/test/resources/PWTest";
    private final double delta = 0.00000000001;
    private int sequenceLength = 9;

    @Before
    public void setup() throws IOException {
        fastaFile = new FastaFile();
        fastaFile.readFastaFile(fileName);
    }

    @Test
    public void testLOGLIKELIHOOD() {
        PWCalculator pwCalculator = new PWCalculator();
        PWMatrix matrix = pwCalculator.calculateMatrix(fastaFile, new CalculationStrategy.Builder()
                .sequenceLength(sequenceLength)
                .build());

        assertEquals(sequenceLength, matrix.getRowByNucleotide('A').getRow().length);
        assertEquals(sequenceLength, matrix.getRowByNucleotide('C').getRow().length);
        assertEquals(sequenceLength, matrix.getRowByNucleotide('G').getRow().length);
        assertEquals(sequenceLength, matrix.getRowByNucleotide('T').getRow().length);

        assertEquals(0.1823215567939546D, matrix.getRowByNucleotide('A').getByIndex(0), delta);
        assertEquals(-0.2231435513142097D, matrix.getRowByNucleotide('C').getByIndex(1), delta);
        assertEquals(1.0296194171811581D, matrix.getRowByNucleotide('G').getByIndex(2), delta);
        assertEquals(Double.NEGATIVE_INFINITY, matrix.getRowByNucleotide('T').getByIndex(3), delta);
    }

    @Test
    public void testRELATIVE() {
        PWCalculator pwCalculator = new PWCalculator();
        PWMatrix matrix = pwCalculator.calculateMatrix(fastaFile, new CalculationStrategy.Builder()
                .sequenceLength(sequenceLength)
                .calculationType(calculationMethod.RELATIVE)
                .build());

        assertEquals(sequenceLength, matrix.getRowByNucleotide('A').getRow().length);
        assertEquals(sequenceLength, matrix.getRowByNucleotide('C').getRow().length);
        assertEquals(sequenceLength, matrix.getRowByNucleotide('G').getRow().length);
        assertEquals(sequenceLength, matrix.getRowByNucleotide('T').getRow().length);

        assertEquals(0.3D, matrix.getRowByNucleotide('A').getByIndex(0), delta);
        assertEquals(0.2D, matrix.getRowByNucleotide('C').getByIndex(1), delta);
        assertEquals(0.5D, matrix.getRowByNucleotide('G').getByIndex(7), delta);
        assertEquals(0.6D, matrix.getRowByNucleotide('T').getByIndex(8), delta);
    }
}
