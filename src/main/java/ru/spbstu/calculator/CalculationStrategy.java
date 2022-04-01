package ru.spbstu.calculator;

import lombok.Builder;
import lombok.Getter;
import ru.spbstu.calculator.Constants.calculationMethod;
/**
 * Represents parameters for calculation position weight matrix
 *
 * frequencyOfNucleotides - average frerquency of nucleotides
 * sequenceLength - length of sequence for one iteration
 * resultFrequency - format of output data
 */

@Getter
@Builder
public class CalculationStrategy {

    @Builder.Default
    private final double frequencyOfNucleotides = Constants.frequency;
    @Builder.Default
    private final int sequenceLength = Constants.DEFAULT_WINDOW_SIZE;
    @Builder.Default
    private final calculationMethod resultFrequency = calculationMethod.LOGLIKELIHOOD;
}
