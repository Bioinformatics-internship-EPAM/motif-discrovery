package ru.spbstu.recognizer;

import ru.spbstu.calculator.*;
import ru.spbstu.fastafile.FastaFile;
import ru.spbstu.fastafile.FastaRecord;

import java.security.InvalidAlgorithmParameterException;
import java.util.List;
import java.util.Random;

public class GibbsMotifRecognizer implements MotifRecognizer {

    private static double desiredDelta = 0.00001;
    private static int maxIterations = 10000;

    private PWCalculator pwCalculator;
    private MotifSet motifSet;
    private PWMatrix matrix;
    private SimilarityScoreCalculator smCalculator;
    private List<FastaRecord> records;
    private Randomizer random;

    @Override
    public PWMatrix recognize(FastaFile fastaFile) throws InvalidAlgorithmParameterException {
        init(fastaFile);

        double delta = Double.POSITIVE_INFINITY;
        int i = 0;

        while ((delta > desiredDelta) || Double.isInfinite(delta) || i < maxIterations) {
            delta = countIteration();
            i++;
        }

        // recount matrix cause relative format is more human-readable
        pwCalculator = new PWCalculator(new CalculationStrategy
                .Builder(Constants.DEFAULT_WINDOW_SIZE).calculationType(Constants.calculationMethod.RELATIVE).build());
        return pwCalculator.calculateMatrix(motifSet);
    }

    public void init(FastaFile fastaFile) {
        pwCalculator = new PWCalculator(new CalculationStrategy
                .Builder(Constants.DEFAULT_WINDOW_SIZE).build());
        motifSet = new MotifSet(fastaFile, MotifSet.Policy.RANDOM, Constants.DEFAULT_WINDOW_SIZE);
        matrix = pwCalculator.calculateMatrix(motifSet);

        smCalculator = new SimilarityScoreCalculator(Constants.DEFAULT_WINDOW_SIZE, fastaFile);
        records = fastaFile.getFastaRecords();

        random = new Randomizer();
    }

    public double countIteration() throws InvalidAlgorithmParameterException {
        // select next record to work
        int nextRand = random.nextInt(records.size() - 1);
        FastaRecord nextRecord = records.get(nextRand);

        // find Motif with the highest score
        DatasetScore resultScore = smCalculator.calculateScoreForRecord(nextRecord, matrix);
        ScoredMotif maxScoredMotif = resultScore.getMax();

        // Change found Motif with one with same recordID
        motifSet.setMotif(maxScoredMotif);

        // update delta
        PWMatrix newMatrix = pwCalculator.calculateMatrix(motifSet);
        double delta = countDelta(matrix, newMatrix);
        matrix = newMatrix;

        return delta;
    }

    public double countDelta(PWMatrix first, PWMatrix second) throws InvalidAlgorithmParameterException {
        if (first.getSequenceLength() != second.getSequenceLength()) {
            throw new InvalidAlgorithmParameterException();
        }

        double delta = 0.0;

        for (char nucle: Constants.NUCLEOTIDES) {
            double[] firstRow = first.getRowByNucleotide(nucle).getRow();
            double[] secondRow = second.getRowByNucleotide(nucle).getRow();

            for (int i = 0; i < firstRow.length; i ++) {
                if (!((Double.isInfinite(firstRow[i]) && Double.isInfinite(secondRow[i])))) {
                    delta += Math.abs((firstRow[i] - secondRow[i]));
                }
            }
        }

        return delta;
    }


    private class Randomizer {

        private int prevInt = -1;
        private Random random = new Random();

        public int nextInt(int max) {
            int nextRand = prevInt;

            // to not repeat random number
            while (nextRand == prevInt) {
                nextRand = random.nextInt(max);
            }
            prevInt = nextRand;

            return nextRand;
        }
    }
}
