package ru.spbstu.recognizer;

import ru.spbstu.calculator.*;
import ru.spbstu.fastafile.FastaFile;
import ru.spbstu.fastafile.FastaRecord;

import java.security.InvalidAlgorithmParameterException;
import java.util.List;
import java.util.Random;

public class GibbsMotifRecognizer implements MotifRecognizer {

    private static double desiredDelta = 0.00001;

    @Override
    public PWMatrix recognize(FastaFile fastaFile) throws InvalidAlgorithmParameterException {
        PWCalculator pwCalculator = new PWCalculator(new CalculationStrategy
                .Builder(Constants.DEFAULT_WINDOW_SIZE).build());
        MotifSet motifSet = new MotifSet(fastaFile, MotifSet.Policy.RANDOM, Constants.DEFAULT_WINDOW_SIZE);
        PWMatrix matrix = pwCalculator.calculateMatrix(motifSet);

        SimilarityScoreCalculator smCalculator = new SimilarityScoreCalculator(Constants.DEFAULT_WINDOW_SIZE, fastaFile);
        List<FastaRecord> records = fastaFile.getFastaRecords();

        Random random = new Random();
        int prevRand = -1;
        int nextRand = -1;

        double delta = Double.POSITIVE_INFINITY;

        while ((delta > desiredDelta) || Double.isInfinite(delta)) {
            // to not repeat random number in loop
            while (nextRand == prevRand) {
                nextRand = random.nextInt(records.size() - 1);
            }
            prevRand = nextRand;

            // random record
            FastaRecord nextRecord = records.get(nextRand);

            // find Motif with the highest score
            DatasetScore resultScore = smCalculator.calculateScoreForRecord(nextRecord, matrix);
            ScoredMotif maxScoredMotif = resultScore.getMax();

            // Change found Motif with one with same recordID
            motifSet.setMotif(maxScoredMotif);

            // update delta
            PWMatrix newMatrix = pwCalculator.calculateMatrix(motifSet);
            delta = matrix.countDelta(newMatrix);
            matrix = newMatrix;
        }

        // recount matrix cause relative format is more human-readable
        pwCalculator = new PWCalculator(new CalculationStrategy
                .Builder(Constants.DEFAULT_WINDOW_SIZE).calculationType(Constants.calculationMethod.RELATIVE).build());
        return pwCalculator.calculateMatrix(motifSet);
    }
}
