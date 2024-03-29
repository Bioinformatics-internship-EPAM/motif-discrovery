package ru.spbstu.pwmatrix;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import ru.spbstu.calculator.CalculationStrategy;
import ru.spbstu.calculator.PWCalculator;
import ru.spbstu.calculator.PWMatrix;
import ru.spbstu.fastafile.FastaFile;
import ru.spbstu.fastafile.FastaFileParser;
import ru.spbstu.fastafile.FastaRecord;
import ru.spbstu.fastafile.Parser;
import ru.spbstu.reader.DefaultReader;
import ru.spbstu.reader.Reader;

import java.util.List;

public class PWCalculatorTest {
    private FastaFile fastaFile;
    private final double delta = 0.00000000001;

    @Before
    public void setup() throws Exception {
        String fileName = "src/test/resources/PWTest";
        Parser fastaFileParse = new FastaFileParser();
        Reader defaultReader = new DefaultReader(fileName);
        List<FastaRecord> fastaRecords = fastaFileParse.parseData(defaultReader.readData());
        fastaFile = new FastaFile(fastaRecords);
    }

    @Test
    public void testPositionWeightMatrix() {
        int sequenceLength = 9;
        double frequencyOfNucleotides = 0.25;
        String loglikelihood = "loglikelihood";
        String relative = "relative";

        CalculationStrategy strategy = new CalculationStrategy(frequencyOfNucleotides, sequenceLength, loglikelihood);
        PWCalculator pwcalculator = new PWCalculator(strategy);
        PWMatrix matrix = pwcalculator.calculateMatrix(fastaFile, strategy);

        assertEquals(sequenceLength, matrix.getRowByNucleotide('A').getRow().length);
        assertEquals(sequenceLength, matrix.getRowByNucleotide('C').getRow().length);
        assertEquals(sequenceLength, matrix.getRowByNucleotide('G').getRow().length);
        assertEquals(sequenceLength, matrix.getRowByNucleotide('T').getRow().length);

        assertEquals(0.1823215567939546D, matrix.getRowByNucleotide('A').getByIndex(0), delta);
        assertEquals(-0.2231435513142097D, matrix.getRowByNucleotide('C').getByIndex(1), delta);
        assertEquals(1.0296194171811581D, matrix.getRowByNucleotide('G').getByIndex(2), delta);
        assertEquals(Double.NEGATIVE_INFINITY, matrix.getRowByNucleotide('T').getByIndex(3), delta);

        strategy = new CalculationStrategy(frequencyOfNucleotides, sequenceLength, relative);
        matrix = pwcalculator.calculateMatrix(fastaFile, strategy);

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
