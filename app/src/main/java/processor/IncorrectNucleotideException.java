package processor;

public class IncorrectNucleotideException extends IllegalArgumentException {
    public IncorrectNucleotideException(char incorrectArg) {
        super(incorrectArg + "is not nucleotide (A, C, T, G)");
    }
}
