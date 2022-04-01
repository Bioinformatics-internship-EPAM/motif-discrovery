package ru.spbstu.calculator;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;
import java.util.List;

import ru.spbstu.fastafile.FastaFile;
import ru.spbstu.calculator.Constants.calculationMethod;
import ru.spbstu.fastafile.FastaFileParser;
import ru.spbstu.fastafile.FastaRecord;
import ru.spbstu.fastafile.Parser;
import ru.spbstu.reader.DefaultReader;
import ru.spbstu.reader.Reader;

public class PWCalculatorTestRELATIVE {
    private final String fileName = "src/test/resources/PWTest";
    private final double delta = 0.00000000001;
    private int sequenceLength = 9;

    private PWMatrix matrix;

    @Before
    public void setup() throws Exception {
        Parser fastaFileParser = new FastaFileParser();
        Reader defaultReader = new DefaultReader(fileName);
        List<FastaRecord> fastaRecords = fastaFileParser.parseData(defaultReader.readData());
        FastaFile fastaFile = new FastaFile(fastaRecords);

        PWCalculator pwCalculator = new PWCalculator(CalculationStrategy.builder().sequenceLength(sequenceLength).resultFrequency(calculationMethod.RELATIVE).build());
        MotifSet motifSet = new MotifSet(fastaFile, MotifSet.Policy.FIRST, sequenceLength);
        matrix = pwCalculator.calculateMatrix(motifSet);
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
        assertEquals(0.3D, matrix.getRowByNucleotide('A').getByIndex(0), delta);
    }

    @Test
    public void testElemC1() {
        assertEquals(0.2D, matrix.getRowByNucleotide('C').getByIndex(1), delta);
    }

    @Test
    public void testElemG7() {
        assertEquals(0.5D, matrix.getRowByNucleotide('G').getByIndex(7), delta);
    }

    @Test
    public void testElemT8() {
        assertEquals(0.6D, matrix.getRowByNucleotide('T').getByIndex(8), delta);
    }
}
