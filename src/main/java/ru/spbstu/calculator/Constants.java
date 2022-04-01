package ru.spbstu.calculator;

import java.util.HashSet;
import java.util.Set;

public class Constants {
    public static final int DEFAULT_WINDOW_SIZE = 10;
    public static final Set<Character> NUCLEOTIDES = new HashSet<>(){{
        add('A');
        add('C');
        add('T');
        add('G');
    }};
    public static final double frequency = 0.25f;
    public enum calculationMethod {
        ABSOLUTE,
        RELATIVE,
        LOGLIKELIHOOD
    }
}

