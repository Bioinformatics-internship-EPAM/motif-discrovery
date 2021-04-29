package ru.spbstu.recognizer;

import ru.spbstu.calculator.PWMatrix;
import ru.spbstu.fastafile.FastaFile;

import java.security.InvalidAlgorithmParameterException;

public interface MotifRecognizer {
    public PWMatrix recognize(FastaFile fastaFile) throws InvalidAlgorithmParameterException;
}
